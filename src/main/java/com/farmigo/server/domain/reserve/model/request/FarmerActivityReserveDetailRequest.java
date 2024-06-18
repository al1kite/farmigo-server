package com.farmigo.server.domain.reserve.model.request;

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
public class FarmerActivityReserveDetailRequest {
    @Schema(description = "조회할 예약 ID", example = "1717849609874_TESTFARM_ACTID01_0")
    private String reserveId;
}
