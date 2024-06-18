package com.farmigo.server.domain.farm.delegate;

import com.farmigo.server.domain.farm.model.request.*;
import com.farmigo.server.domain.farm.model.response.*;
import com.farmigo.server.domain.farm.model.vo.*;
import com.farmigo.server.domain.farm.service.FarmReviewService;
import com.farmigo.server.domain.farm.service.FarmSearchService;
import com.farmigo.server.global.model.response.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description :
 */
@Slf4j
@Service
@AllArgsConstructor
public class FarmYouthDelegate {
    private final FarmSearchService farmSearchService;
    private final FarmReviewService farmReviewService;

    public FarmRecommendListResponse getFarmRecommendList(long txTime, FarmRecommendListRequest req) {
        List<ActivityInfoSummary> activityInfoSummaries = farmSearchService.getActivityRecommendList(txTime, req.getLimit());
        return FarmRecommendListResponse.builder()
                .activityInfos(activityInfoSummaries)
                .build();
    }

    public FarmActivityListResponse getFarmActivitySearchList(FarmActivityListRequest req) {
        List<ActivityInfoSummary> activityInfoSummaries = farmSearchService.getActivitySearchList(
                req.getSearch(), req.getOffset(), req.getLimit());
        String offset = "";
        if (!activityInfoSummaries.isEmpty()) {
            offset = activityInfoSummaries.get(activityInfoSummaries.size() - 1).getActivityId();
        }

        return FarmActivityListResponse.builder()
                .activityInfos(activityInfoSummaries)
                .offset(offset)
                .build();
    }

    public FarmActivityDetailResponse getFarmActivityDetail(FarmActivityDetailRequest req) {
        ActivityInfo activityInfo = farmSearchService.getActivityDetail(req.getActivityId());
        FarmInfo farmInfo = farmSearchService.getFarmDetail(activityInfo.getFarmId());
        FarmerInfo farmerInfo = farmSearchService.getFarmerDetail(farmInfo.getFarmerId());

        return FarmActivityDetailResponse.builder()
                .activityInfo(activityInfo)
                .farmInfo(farmInfo)
                .farmerInfo(farmerInfo)
                .build();
    }

    public FarmActivityReviewResponse getFarmActivityReviewList(FarmActivityReviewListRequest req) {
        List<Review> reviews = farmReviewService.getReviewList(req.getActivityId(), req.getOffset(), req.getLimit());
        return FarmActivityReviewResponse.builder()
                .reviewList(reviews)
                .build();
    }

    public Response createFarmActivityReview(long txTime, SaveFarmActivityReviewRequest req) {
        farmReviewService.createReview(txTime, req);

        return Response.success();
    }
}
