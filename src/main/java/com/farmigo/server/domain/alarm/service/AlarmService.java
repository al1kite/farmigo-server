package com.farmigo.server.domain.alarm.service;

import com.farmigo.server.domain.alarm.mapper.AlarmInfoMapper;
import com.farmigo.server.domain.alarm.model.request.AlarmRequest;
import com.farmigo.server.domain.alarm.model.response.AlarmResponse;
import com.farmigo.server.domain.alarm.model.response.AlarmUrlResponse;
import com.farmigo.server.domain.alarm.model.vo.UserAlarmInfo;
import com.farmigo.server.domain.alarm.repository.AlarmRepository;
import com.farmigo.server.domain.alarm.repository.EmitterRepository;
import com.farmigo.server.domain.auth.security.LoginManager;
import com.farmigo.server.global.model.mongo.user.USER_ALARM;
import com.farmigo.server.global.model.response.Response;
import com.farmigo.server.global.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.farmigo.server.global.constant.ErrorCode.*;
import static com.farmigo.server.global.util.DateUtil.DEFAULT_SSE_TIMEOUT;

/**
 * 청년/농가 알림 기능 공통 사용
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmService {

    private final EmitterRepository emitterRepository;
    private final AlarmRepository alarmRepository;
    private final AlarmInfoMapper alarmInfoMapper;

    /**
     * SSE
     */

    public SseEmitter subscribe(String lastEventId) {
        String userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserId();

        String emitterId = userId + "_" + System.currentTimeMillis();

        SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_SSE_TIMEOUT));
        emitter.onCompletion(() -> emitterRepository.deleteById(emitterId));
        emitter.onTimeout(() -> emitterRepository.deleteById(emitterId));
        emitter.onError((e) -> emitterRepository.deleteById(emitterId));

        this.sendAlarm(emitter, emitterId,
                emitterId, "EventStream Created. [userId=%s]".formatted(userId));

        if (!lastEventId.isEmpty()) {
            Map<String, SseEmitter> events = emitterRepository.findAllStartByUserId(String.valueOf(userId));
            events.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sendAlarm(emitter, emitterId, entry.getKey(), entry.getValue()));
        }

        return emitter;
    }

    public Response sendAlarm(AlarmRequest request) {

        String userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserId();
        String eventId = userId + "_" + System.currentTimeMillis(); // 데이터 유실 시점 파악 위함

        UserAlarmInfo alarmInfo = alarmInfoMapper.toEntity(request);
        createAlarm(userId, alarmInfo);

        // 유저의 모든 SseEmitter 가져옴
        Map<String, SseEmitter> emitters = emitterRepository.findAllStartByUserId(userId + "_");
        emitters.forEach(
                (key, emitter) -> {
                    emitterRepository.saveEventCache(key, alarmInfo);
                    this.sendAlarm(emitter, eventId, key, alarmInfo);
                }
        );

        return Response.success();
    }

    private void sendAlarm(SseEmitter emitter, String eventId, String emitterId, Object data){
        try {
            emitter.send(SseEmitter.event()
                    .id(eventId)
                    .name("message")
                    .data(data));
            log.info("SSE connection successful.");
        } catch (IOException exception) {
            emitterRepository.deleteById(emitterId);
            log.error("SSE connection failed. target : {}", eventId);
        }
    }

    private void createAlarm(String userId, UserAlarmInfo alarmInfo){
        alarmInfo.setDefaultStatus();
        USER_ALARM alarm = alarmRepository.getRecentAlarmByUserId(userId);

        long txTime = DateUtil.dateToEpochMilli(LocalDateTime.now());

        if(alarm == null) {
            alarmInfo.set_id(txTime);
            alarm = USER_ALARM.builder()
                    ._id(userId + "_" + txTime + "_" + "ALARM")
                    .userId(userId)
                    .createTime(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1))
                    .alarmInfos(new ArrayList<>(List.of(alarmInfo)))
                    .build();
        } else {
            alarmInfo.set_id(txTime + alarm.getAlarmInfos().size());
            alarm.getAlarmInfos().add(alarmInfo);
        }

        alarmRepository.save(alarm);
    }

    /**
     * Alarm
     */
    public Response getAlarmList(){
        String userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserId();
        List<USER_ALARM> userRecentAlarms = alarmRepository.getUserAlarmFromRecentThreeMonthsAgo(userId);

        return this.userAlarmInfos(userRecentAlarms);
    }

    public Response deleteAlarm(Long alarmId){
        String userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserId();
        USER_ALARM userAlarm = alarmRepository.getUserAlarmByUserIdAndAlarmId(userId, alarmId);

        if(userAlarm == null)
            return Response.error(RESOURCE_NOT_FOUND);

        for(UserAlarmInfo userAlarmInfo : userAlarm.getAlarmInfos()){
            if(userAlarmInfo.get_id() == alarmId)
                userAlarmInfo.setIsDeleted(true);
        }

        alarmRepository.save(userAlarm);
        return Response.success();
    }

    public Response readAlarm(Long alarmId){
        String userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserId();
        USER_ALARM userAlarm = alarmRepository.getUserAlarmByUserIdAndAlarmId(userId, alarmId);

        if(userAlarm == null)
            return Response.error(RESOURCE_NOT_FOUND);

        AlarmUrlResponse response = new AlarmUrlResponse();
        for(UserAlarmInfo userAlarmInfo : userAlarm.getAlarmInfos()){
            if(userAlarmInfo.get_id() == alarmId) {
                userAlarmInfo.setIsRead(true);
                response = AlarmUrlResponse.builder()
                        .redirectUrl(userAlarmInfo.getRedirectUrl())
                        .build();
            }
        }

        alarmRepository.save(userAlarm);

        return response;
    }

    public Response deleteAllReadAlarm(){
        String userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserId();
        List<USER_ALARM> userRecentAlarms = alarmRepository.getUserAlarmFromRecentThreeMonthsAgo(userId);

        userRecentAlarms.forEach(userAlarm -> userAlarm.getAlarmInfos().stream().filter(UserAlarmInfo::getIsRead).forEach(alarmInfo -> alarmInfo.setIsDeleted(true)));

        userRecentAlarms = userRecentAlarms.stream().map(alarmRepository::save).collect(Collectors.toList()); // saveAll
        return this.userAlarmInfos(userRecentAlarms);
    }

    private AlarmResponse userAlarmInfos(List<USER_ALARM> userRecentAlarms){
        List<UserAlarmInfo> userAlarmInfos = new ArrayList<>();
        for(USER_ALARM recentAlarm : userRecentAlarms){
            userAlarmInfos.addAll(recentAlarm.getAlarmInfos());
        }
        return AlarmResponse.builder()
                .userAlarmInfos(userAlarmInfos)
                .build();
    }

}
