package com.farmigo.server.domain.farm.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description :
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveFarmActivityReviewRequest {
    @Schema(description = "리뷰 작성하는 유저의 userID", example = "0")
    private String userId;
    @Schema(description = "리뷰 작성할 체험ID", example = "TESTFARM_ACTID01")
    private String activityId;
    @Schema(description = "리뷰", example = "리뷰test")
    private String review;
    @Schema(description = "별점(10점만점)", example = "10")
    private int score;
}
