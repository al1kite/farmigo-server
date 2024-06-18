package com.farmigo.server.domain.reserve.service;

import com.farmigo.server.domain.farm.repository.MstActivityMongoRepository;
import com.farmigo.server.domain.reserve.model.vo.ActivityReserve;
import com.farmigo.server.domain.reserve.model.vo.ActivityReserveSummary;
import com.farmigo.server.domain.reserve.model.vo.FarmReserveStats;
import com.farmigo.server.domain.reserve.repository.UserActivityReserveMongoRepository;
import com.farmigo.server.global.model.mongo.master.MST_ACTIVITY;
import com.farmigo.server.global.model.mongo.user.USER_ACTIVITY_RESERVE;
import com.farmigo.server.global.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description : 농장 전체 예약 관련 서비스
 */
@Slf4j
@Service
@AllArgsConstructor
public class FarmReserveSearchService {

    private final MstActivityMongoRepository mstActivityMongoRepository;
    private final UserActivityReserveMongoRepository userActivityReserveMongoRepository;

    public List<FarmReserveStats> getFarmStats(String farmId, String startMonth, String endMonth) {
        LocalDateTime startMonthDate = DateUtil.stringToDate(startMonth);
        LocalDateTime endMonthDate = DateUtil.stringToDate(endMonth);

        return userActivityReserveMongoRepository.aggregateByMonth(farmId, startMonthDate, endMonthDate);
    }

    public List<ActivityReserveSummary> getReserveList(String farmId, int status, String offset, int limit) {
        List<USER_ACTIVITY_RESERVE> reserves = userActivityReserveMongoRepository.findByFarmIdAndStatus(farmId, status, offset, limit);
        List<MST_ACTIVITY> farmActivities = mstActivityMongoRepository.findByFarmId(farmId);

        return reserves.stream().map(reserve -> {
            MST_ACTIVITY activity = farmActivities.stream()
                    .filter(farmActivity -> farmActivity.getActivityId().equals(reserve.getActivityId()))
                    .findFirst()
                    .orElse(new MST_ACTIVITY());

            return new ActivityReserveSummary(reserve, activity);
        }).toList();
    }

    public ActivityReserve getReserveDetail(String reserveId) {
        USER_ACTIVITY_RESERVE reserve = userActivityReserveMongoRepository.findById(reserveId);
        MST_ACTIVITY activity = mstActivityMongoRepository.findByActivityId(reserve.getActivityId());

        return new ActivityReserve(reserve, activity);
    }
}
