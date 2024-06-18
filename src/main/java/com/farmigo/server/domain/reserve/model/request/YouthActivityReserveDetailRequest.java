package com.farmigo.server.domain.reserve.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description : 청년 견적서 요청 Request
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class YouthActivityReserveDetailRequest {
    @Schema(description = "요청 청년 userId", example = "0")
    private long userId;
    @Schema(description = "조회할 체험 ID", example = "TESTFARM_ACTID01")
    private String activityId;
}
