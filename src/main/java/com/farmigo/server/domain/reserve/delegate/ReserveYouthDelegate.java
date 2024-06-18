package com.farmigo.server.domain.reserve.delegate;

import com.farmigo.server.domain.reserve.model.request.YouthActivityReserveDetailRequest;
import com.farmigo.server.domain.reserve.model.request.FarmActivityReserveRequest;
import com.farmigo.server.domain.reserve.model.response.YouthActivityReserveDetailResponse;
import com.farmigo.server.domain.reserve.model.vo.ActivityReserve;
import com.farmigo.server.domain.reserve.service.FarmActivityReserveService;
import com.farmigo.server.global.model.response.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description :
 */
@Slf4j
@Service
@AllArgsConstructor
public class ReserveYouthDelegate {

    private final FarmActivityReserveService farmReserveService;

    public YouthActivityReserveDetailResponse getActivityReserveDetail(YouthActivityReserveDetailRequest req) {
        ActivityReserve activityOrder = farmReserveService.getActivityReserveDetail(req.getUserId(), req.getActivityId());
        return YouthActivityReserveDetailResponse.builder()
                .activityOrder(activityOrder)
                .build();
    }

    public Response activityReserve(long txTime, FarmActivityReserveRequest req) {
        farmReserveService.activityReserve(txTime, req.getActivityId(), req.getUserId(), req.getStartTime(), req.getEndTime());

        return Response.success();
    }
}
