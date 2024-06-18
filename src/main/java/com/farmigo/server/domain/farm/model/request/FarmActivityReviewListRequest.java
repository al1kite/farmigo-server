package com.farmigo.server.domain.farm.model.request;

import com.farmigo.server.global.model.request.OffsetRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description :
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FarmActivityReviewListRequest extends OffsetRequest {
    @Schema(description = "조회할 리뷰의 체험ID", example = "TESTFARM_ACTID01")
    private String activityId;
}
