package com.farmigo.server.domain.farm.controller;

import com.farmigo.server.domain.farm.delegate.FarmYouthDelegate;
import com.farmigo.server.domain.farm.model.request.*;
import com.farmigo.server.domain.farm.model.response.*;
import com.farmigo.server.global.model.request.Request;
import com.farmigo.server.global.model.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description : 청년 농장 관련 컨트롤러
 */
@Tag(name = "FarmYouth", description = "청년 농장 관련 컨트롤러")
@RestController
@RequiredArgsConstructor
public class FarmYouthController {

    private final FarmYouthDelegate farmYouthDelegate;

    @Operation(summary = "홈 화면의 농장 추천 목록 조회", description = """
            농장 추천 목록을 조회한다.
            """)
    @PostMapping("/api/farm/recommend/list")
    public FarmRecommendListResponse getFarmRecommendList(Request request, @RequestBody FarmRecommendListRequest req) {
        return farmYouthDelegate.getFarmRecommendList(request.getTxTime(), req);
    }

    @Operation(summary = "체험 목록 검색 및 조회", description = """
            체험 목록을 검색 및 조회한다
            """)
    @PostMapping("/api/farm/activity/search/list")
    public FarmActivityListResponse getFarmActivitySearchList(Request request, @RequestBody FarmActivityListRequest req) {
        return farmYouthDelegate.getFarmActivitySearchList(req);
    }

    @Operation(summary = "체험 상세 페이지 조회", description = """
            체험 상세 페이지를 조회한다
            """)
    @PostMapping("/api/farm/activity/detail")
    public FarmActivityDetailResponse getFarmActivityDetail(Request request, @RequestBody FarmActivityDetailRequest req) {
        return farmYouthDelegate.getFarmActivityDetail(req);
    }

    @Operation(summary = "체험 리뷰 조회", description = """
            체험 리뷰 리스트를 조회한다
            """)
    @PostMapping("/api/farm/activity/review/list")
    public FarmActivityReviewResponse getFarmActivityReviewList(Request request, @RequestBody FarmActivityReviewListRequest req) {
        return farmYouthDelegate.getFarmActivityReviewList(req);
    }

    @Operation(summary = "체험 리뷰 작성", description = """
            체험 리뷰를 작성한다
            """)
    @PostMapping("/api/farm/activity/review/create")
    public Response createFarmActivityReview(Request request, @RequestBody SaveFarmActivityReviewRequest req) {
        return farmYouthDelegate.createFarmActivityReview(request.getTxTime(), req);
    }
}
