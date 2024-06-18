package com.farmigo.server.domain.reserve.controller;

import com.farmigo.server.domain.farm.model.request.FarmStatsRequest;
import com.farmigo.server.domain.farm.model.response.FarmStatsResponse;
import com.farmigo.server.domain.reserve.delegate.ReserveFarmerDelegate;
import com.farmigo.server.domain.reserve.model.request.FarmerActivityReserveDetailRequest;
import com.farmigo.server.domain.reserve.model.request.FarmerReserveListRequest;
import com.farmigo.server.domain.reserve.model.response.FarmerActivityReserveDetailResponse;
import com.farmigo.server.domain.reserve.model.response.FarmerReserveListResponse;
import com.farmigo.server.global.model.request.Request;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description :
 */
@Tag(name = "ReserveFarmer", description = "농장주 예약 관련 컨트롤러")
@RestController
@RequiredArgsConstructor
public class ReserveFarmerController {

    private final ReserveFarmerDelegate reserveFarmerDelegate;

    @Operation(summary = "농장 예약 추이 조회", description = """
            농가 방문자수 추이를 조회한다.
            startMonth : 조회 시작 월(YYYYMMDD) 20240401
            endMonth : 조회 종료 월(YYYYMMDD) 20240531
            """)
    @PostMapping("/api/reserve/farmer/stats")
    public FarmStatsResponse getFarmStats(Request param, @RequestBody FarmStatsRequest req) {
        return reserveFarmerDelegate.getFarmStats(req);
    }

    @Operation(summary = "예약 건 조회", description = """
            새로들어온/진행중인/완료된 예약 건을 조회한다.
            상태 값(status) 필수!
            status : 1(신규), 2(진행중), 3(완료)
            """)
    @PostMapping("/api/reserve/farmer/list")
    public FarmerReserveListResponse getReserveList(Request param, @RequestBody FarmerReserveListRequest req) {
        return reserveFarmerDelegate.getReserveList(req);
    }

    @Operation(summary = "예약 건 상세 조회", description = """
            예약 내역을 상세 조회한다.
            """)
    @PostMapping("/api/reserve/farmer/detail")
    public FarmerActivityReserveDetailResponse getReserveDetail(Request param, @RequestBody FarmerActivityReserveDetailRequest req) {
        return reserveFarmerDelegate.getReserveDetail(req);
    }
}
