package com.farmigo.server.domain.farm.model.request;

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
public class FarmActivityDetailRequest {
    @Schema(description = "조회할 체험ID", example = "TESTFARM_ACTID01")
    private String activityId;
}
