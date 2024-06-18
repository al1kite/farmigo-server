package com.farmigo.server.domain.profile.controller;

import com.farmigo.server.domain.profile.model.enumeration.ReserveType;
import com.farmigo.server.domain.profile.model.request.FarmInfoRequest;
import com.farmigo.server.domain.profile.model.request.FarmerProfileRequest;
import com.farmigo.server.domain.profile.model.request.ReserveSearchRequest;
import com.farmigo.server.domain.profile.model.response.FarmerProfileResponse;
import com.farmigo.server.domain.profile.model.response.ProfileResponse;
import com.farmigo.server.domain.profile.model.response.ReserveDetailResponse;
import com.farmigo.server.domain.profile.service.FarmerProfileService;
import com.farmigo.server.global.model.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/farmer/profile")
@Tag(name = "FarmerProfile", description = "농가 마이페이지 관련 컨트롤러")
/**
 * 농가 마이페이지 관련 Controller
 */
public class FarmerProfileController {

    private final FarmerProfileService farmerProfileService;

    /**
     * 농가 프로필 조회 Controller
     */
    @Operation(summary = "농가 프로필 조회", description = """
             MY 에서의 농가 프로필 조회 한다. (농부 이름, 농가 이름, 프로필 이미지)
            """)
    @PostMapping("")
    public ProfileResponse getProfile() {
        return farmerProfileService.getProfile();
    }

    /**
     * 청년 프로필 상세 조회 Controller
     */
    @Operation(summary = "농가 프로필 상세 조회", description = """
             MY 에서의 농가 프로필 상세 조회 한다.
            """)
    @PostMapping("/detail")
    public FarmerProfileResponse getProfileDetail() {
        return farmerProfileService.getProfileDetail();
    }

    /**
     * 청년 프로필 수정 Controller
     */
    @Operation(summary = "농가 프로필 수정", description = """
             MY 에서의 농가 프로필 수정 한다.
            """)
    @PostMapping("/update")
    public FarmerProfileResponse updateProfile(@RequestBody FarmerProfileRequest farmerProfileRequest) {
        return farmerProfileService.updateProfile(farmerProfileRequest);
    }

    /**
     * 예약 내역 - 체험 예약 형태 조회 Controller
     */
    @Operation(summary = "체험 예약 건수 조회", description = """
             체험 예약 형태 별 건수를 조회 한다.(ex. 예약된 방문(UC) : 2, 진행 중인 방문(PC) : 1, 지난 방문(PA) : 2, 전체 (AL) : 5)
            """)
    @PostMapping("/reserve")
    public Map<ReserveType, Long> getReserve() {
        return farmerProfileService.getReserve();
    }

    /**
     * 예약 내역 - 체험 예약 형태 상세 조회 Controller
     * Type : upcoming, proceeding, past
     */
    @Operation(summary = "체험 예약 형태 상세 조회", description = """
            Request 값으로 예약된 방문(UC), 진행 중인 방문(PC), 지난 방문(PA), 전체 (AL) 중 입력 시 해당 형태에 맞는 예약 List 를 조회 한다.
            """)
    @PostMapping("/reserve/detail")
    public ReserveDetailResponse getReserveDetail(@RequestBody ReserveSearchRequest reserveSearchRequest) {
        return farmerProfileService.getReserveDetail(reserveSearchRequest);
    }

    /**
     * 농가 소개글 저장 Controller
     */
    @Operation(summary = "농가 소개글 저장", description = """
            농가 소개글을 저장 한다.
            """)
    @PostMapping("/farm-info")
    public Response saveFarmInfo(@RequestBody FarmInfoRequest farmInfoRequest) {
        return farmerProfileService.saveFarmInfo(farmInfoRequest);
    }

    /**
     * 최대/최소 예약 금액 조회 Controller
     */
    @Operation(summary = "최대/최소 예약 금액 조회", description = """
            현재 체험 금액의 최소와 최대 금액을 알려 준다.
            """)
    @PostMapping("/price")
    public Response getAveragePrice() {
        return farmerProfileService.getAveragePrice();
    }

}

