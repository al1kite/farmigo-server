package com.farmigo.server.domain.reserve.controller;

import com.farmigo.server.domain.reserve.delegate.ReserveYouthDelegate;
import com.farmigo.server.domain.reserve.model.request.YouthActivityReserveDetailRequest;
import com.farmigo.server.domain.reserve.model.request.FarmActivityReserveRequest;
import com.farmigo.server.domain.reserve.model.response.YouthActivityReserveDetailResponse;
import com.farmigo.server.global.model.request.Request;
import com.farmigo.server.global.model.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description : 청년 예약 관련 컨트롤러
 */
@Tag(name = "ReserveYouth", description = "청년 예약 관련 컨트롤러")
@RestController
@RequiredArgsConstructor
public class ReserveYouthController {

    private final ReserveYouthDelegate reserveYouthDelegate;

    @Operation(summary = "견적서 정보 가져오기", description = """
            견적서 정보를 가져온다
            """)
    @PostMapping("/api/reserve/activity/detail")
    public YouthActivityReserveDetailResponse getActivityReserveDetail(Request request, @RequestBody YouthActivityReserveDetailRequest req) {
        return reserveYouthDelegate.getActivityReserveDetail(req);
    }

    @Operation(summary = "견적서 요청", description = """
            견적서를 요청한다
            startTime : 시작 시간(timestdamp MilliSecond) yyyymmdd 00:00:00
                - ex) 20240401 00:00:00(KST) -> 1711897200000
            endTime : 종료 시간(timestamp MilliSecond) yyyymmdd 00:00:00
                - ex) 20240402 00:00:00(KST) -> 1711983600000
            => 2024.04.01 ~ 2024.04.01 의 당일치기 체험이다 하면 아래와 같이 입력
                - st : 1711897200000
                - et : 1711983600000
            """)
    @PostMapping("/api/reserve/activity")
    public Response activityReserve(Request request, @RequestBody FarmActivityReserveRequest req) {
        return reserveYouthDelegate.activityReserve(request.getTxTime(), req);
    }
}
