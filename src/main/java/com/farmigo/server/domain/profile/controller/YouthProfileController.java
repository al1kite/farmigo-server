package com.farmigo.server.domain.profile.controller;

import com.farmigo.server.domain.profile.model.response.ProfileResponse;
import com.farmigo.server.domain.profile.model.response.ReserveDetailResponse;
import com.farmigo.server.domain.profile.model.request.ReserveSearchRequest;
import com.farmigo.server.domain.profile.model.request.YouthOnboardingRequest;
import com.farmigo.server.domain.profile.model.request.ProfileRequest;
import com.farmigo.server.domain.profile.model.response.YouthOnboardingResponse;
import com.farmigo.server.domain.profile.model.response.YouthProfileResponse;
import com.farmigo.server.domain.profile.service.YouthProfileService;
import com.farmigo.server.global.model.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/youth/profile")
@Tag(name = "YouthProfile", description = "청년 마이페이지 관련 컨트롤러")
/**
 * 청년 마이페이지 관련 Controller
 */
public class YouthProfileController {

    private final YouthProfileService youthProfileService;

    /**
     * 청년 프로필 조회 Controller
     */
    @Operation(summary = "청년 프로필 조회", description = """
             MY 에서의 청년 프로필 조회 한다. (청년 이름, 전화번호 (description), 프로필 이미지)
            """)
    @PostMapping("")
    public ProfileResponse getProfile() {
        return youthProfileService.getProfile();
    }

    /**
     * 청년 프로필 상세 조회 Controller
     */
    @Operation(summary = "청년 프로필 상세 조회", description = """
              청년 프로필 상세 조회 한다.
            """)
    @PostMapping("/detail")
    public YouthProfileResponse getProfileDetail() {
        return youthProfileService.getProfileDetail();
    }

    /**
     * 청년 프로필 수정 Controller
     */
    @Operation(summary = "청년 프로필 수정", description = """
              청년 프로필 수정 한다. (청년 이름, 전화번호 , 프로필 이미지)
            """)
    @PostMapping("/update")
    public Response updateProfile(@RequestBody ProfileRequest profileRequest) {
        return youthProfileService.updateProfile(profileRequest);
    }

    /**
     * 청년 프로필 상세 수정 Controller
     */
    @Operation(summary = "청년 온보딩 다시 하기", description = """
              청년 온보딩을 다시 진행 한다.
            """)
    @PostMapping("/update-detail")
    public YouthOnboardingResponse updateOnboarding(@RequestBody YouthOnboardingRequest onboardingRequest) {
        return youthProfileService.updateOnboarding(onboardingRequest);
    }

    /**
     * 예약 내역 - 방문 조회 Controller
     * Type : upcoming, proceeding, past
     */
    @Operation(summary = "체험 예약 형태 상세 조회", description = """
            Request 값으로 예약된 방문(UC), 진행 중인 방문(PC), 지난 방문(PA), 전체 (AL) 중 입력 시 해당 형태에 맞는 예약 List 를 조회 한다.
            """)
    @PostMapping("/reserve")
    public ReserveDetailResponse getPastReserve(@RequestBody ReserveSearchRequest reserveSearchRequest) {
        return youthProfileService.getReserve(reserveSearchRequest);
    }

}
