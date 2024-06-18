package com.farmigo.server.domain.reserve.service;

import com.farmigo.server.domain.farm.repository.MstActivityMongoRepository;
import com.farmigo.server.domain.reserve.model.vo.ActivityReserve;
import com.farmigo.server.domain.reserve.repository.UserActivityReserveMongoRepository;
import com.farmigo.server.global.constant.ErrorCode;
import com.farmigo.server.global.constant.ReserveStatus;
import com.farmigo.server.global.exception.ServerException;
import com.farmigo.server.global.model.mongo.master.MST_ACTIVITY;
import com.farmigo.server.global.model.mongo.user.USER_ACTIVITY_RESERVE;
import com.farmigo.server.global.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Description : 농장 체험 활동 예약 관련 서비스
 */
@Slf4j
@Service
@AllArgsConstructor
public class FarmActivityReserveService {

    private final MstActivityMongoRepository mstActivityMongoRepository;
    private final UserActivityReserveMongoRepository userActivityReserveMongoRepository;

    public ActivityReserve getActivityReserveDetail(long userId, String activityId) {
        USER_ACTIVITY_RESERVE reserve = userActivityReserveMongoRepository.findByUserIdAndActivityId(userId, activityId);
        MST_ACTIVITY activity = mstActivityMongoRepository.findByActivityId(activityId);
        return new ActivityReserve(reserve, activity);
    }

    public void activityReserve(long txTime, String activityId, String userId, long st, long et) {
        MST_ACTIVITY activity = mstActivityMongoRepository.findByActivityId(activityId);
        String reserveId = this.generateReserveId(activityId, userId, txTime);

        LocalDateTime startTime = DateUtil.epochToDate(st);
        LocalDateTime endTime = DateUtil.epochToDate(et);

        // 견적서 생성
        USER_ACTIVITY_RESERVE userActivityReserve = USER_ACTIVITY_RESERVE.builder()
                .reserveId(reserveId)
                .activityId(activityId)
                .farmId(activity.getFarmId())
                .userId(userId)
                .startTime(startTime)
                .endTime(endTime)
                .priceInfo(activity.getPriceInfo())
                .status(ReserveStatus.CREATE_RESERVE.getValue())
                .createTime(txTime)
                .build();
        boolean success = userActivityReserveMongoRepository.save(userActivityReserve)
                .getReserveId().equals(reserveId);

        if (!success) {
            log.error("Failed to save reserve info. reserveId: {}", reserveId);
            throw new ServerException(ErrorCode.RESERVE_CREATE_FAIL);
        }

        // TODO : 예약 요청 Push 전송
        // push 전송 후 상태 변경
        success = userActivityReserveMongoRepository.updateStatus(reserveId, ReserveStatus.REQUEST_RESERVE.getValue());
        if (!success) {
            log.error("Failed to update reserve info 1. reserveId: {}", reserveId);
            throw new ServerException(ErrorCode.RESERVE_REQUEST_FAIL);
        }
    }

    private String generateReserveId(String activityId, String userId, long txTime) {
        return txTime + "_" + activityId + "_" + userId;
    }
}
