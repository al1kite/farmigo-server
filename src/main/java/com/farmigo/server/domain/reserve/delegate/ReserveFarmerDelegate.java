package com.farmigo.server.domain.reserve.delegate;

import com.farmigo.server.domain.farm.model.request.FarmStatsRequest;
import com.farmigo.server.domain.farm.model.response.FarmStatsResponse;
import com.farmigo.server.domain.reserve.model.request.FarmerActivityReserveDetailRequest;
import com.farmigo.server.domain.reserve.model.request.FarmerReserveListRequest;
import com.farmigo.server.domain.reserve.model.response.FarmerActivityReserveDetailResponse;
import com.farmigo.server.domain.reserve.model.response.FarmerReserveListResponse;
import com.farmigo.server.domain.reserve.model.vo.ActivityReserve;
import com.farmigo.server.domain.reserve.model.vo.ActivityReserveSummary;
import com.farmigo.server.domain.reserve.model.vo.FarmReserveStats;
import com.farmigo.server.domain.reserve.service.FarmActivityReserveService;
import com.farmigo.server.domain.reserve.service.FarmReserveSearchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description : 농장 예약 관련 Delegate
 */
@Slf4j
@Service
@AllArgsConstructor
public class ReserveFarmerDelegate {

    private final FarmActivityReserveService farmActivityReserveService;
    private final FarmReserveSearchService farmReserveSearchService;

    public FarmStatsResponse getFarmStats(FarmStatsRequest req) {
        List<FarmReserveStats> stats = farmReserveSearchService.getFarmStats(req.getFarmId(), req.getStartMonth(), req.getEndMonth());

        return FarmStatsResponse.builder()
                .stats(stats)
                .build();
    }

    public FarmerReserveListResponse getReserveList(FarmerReserveListRequest req) {
        List<ActivityReserveSummary> reserveSummaries = farmReserveSearchService.getReserveList(
                req.getFarmId(), req.getStatus(), req.getOffset(), req.getLimit());

        return FarmerReserveListResponse.builder()
                .reserves(reserveSummaries)
                .build();
    }

    public FarmerActivityReserveDetailResponse getReserveDetail(FarmerActivityReserveDetailRequest req) {
        ActivityReserve reserve = farmReserveSearchService.getReserveDetail(req.getReserveId());

        return FarmerActivityReserveDetailResponse.builder()
                .activityReserve(reserve)
                .build();
    }
}
