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
public class FarmStatsRequest {
    @Schema(description = "농장 ID", example = "TESTFARM")
    private String farmId;
    @Schema(description = "조회 시작월", example = "20240101")
    private String startMonth;
    @Schema(description = "조회 시작월", example = "20241231")
    private String endMonth;
}
