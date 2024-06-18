package com.farmigo.server.domain.farm.model.request;

import com.farmigo.server.global.model.request.OffsetRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description : 농장 추천 목록 조회 request
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FarmRecommendListRequest {
    @Schema(description = "조회할 데이터 수", example = "5")
    private int limit;
}
