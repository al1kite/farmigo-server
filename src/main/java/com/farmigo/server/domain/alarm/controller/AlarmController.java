package com.farmigo.server.domain.alarm.controller;

import com.farmigo.server.domain.alarm.model.request.AlarmRequest;
import com.farmigo.server.domain.alarm.service.AlarmService;
import com.farmigo.server.global.model.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/alarm")
@Tag(name = "Alarm", description = "알람 관련 컨트롤러 (농가/청년 공통 사용)")
public class AlarmController {
    private final AlarmService alarmService;

    /**
     * 농가/청년 공통 사용
     * 로그인 한 유저 알람 sse 연결
     */
    @Operation(summary = "로그인 한 유저 알람 sse 연결", description = """
            로그인 한 유저 알람 sse 연결 한다.
            """)
    @GetMapping(value = "/subscribe", produces = "text/event-stream")
    public ResponseEntity<SseEmitter> subscribe(
            @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        return ResponseEntity.ok(alarmService.subscribe(lastEventId));
    }

    /**
     * 농가/청년 공통 사용
     * 알림 저장(발송) Controller
     */
    @Operation(summary = "알림 저장(발송)", description = """
            알림 저장 (발송) 한다. 예약 알람 발송은 추후 개발
            """)
    @PostMapping("/send")
    public Response sendAlarm(AlarmRequest alarmRequest){
        return alarmService.sendAlarm(alarmRequest);
    }

    /**
     * 농가/청년 공통 사용
     * 알림 조회 Controller
     */
    @Operation(summary = "알림 List 조회", description = """
            최근 3개월 간의 삭제 되지 않은 알림을 List 형태로 한다.
            """)
    @PostMapping("/list")
    public Response getAlarmList(){
        return alarmService.getAlarmList();
    }

    /**
     * 농가/청년 공통 사용
     * 알림 읽음 Controller
     */
    @Operation(summary = "알림 읽음", description = """
            alarmId에 해당 하는 알림을 읽는다.
            """)
    @PostMapping("/read")
    public Response readAlarm(@RequestParam("alarmId") Long alarmId){
        return alarmService.readAlarm(alarmId);
    }


    /**
     * 농가/청년 공통 사용
     * 알림 삭제 Controller
     */
    @Operation(summary = "알림 삭제", description = """
            alarmId에 해당 하는 알림을 삭제 한다.
            """)
    @PostMapping("/delete")
    public Response deleteAlarm(@RequestParam("alarmId") Long alarmId){
        return alarmService.deleteAlarm(alarmId);
    }

    /**
     * 농가/청년 공통 사용
     * 알림 삭제 Controller
     */
    @Operation(summary = "읽은 알림 삭제", description = """
             최근 3개월 간의 삭제 되지 않은 알림 List 중 읽은 알람을 전체 삭제 한다.
            """)
    @PostMapping("/delete-all")
    public Response deleteAllAlarm(){
        return alarmService.deleteAllReadAlarm();
    }

}
