package com.farmigo.server.domain.reserve.model.request;

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
public class FarmerReserveListRequest extends OffsetRequest {
    @Schema(description = "농장 ID", example = "TESTFARM")
    private String farmId;
    @Schema(description = "상태//1(신규), 2(진행중), 3(완료)", example = "1")
    private int status;
}
