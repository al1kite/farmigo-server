package com.farmigo.server.domain.farm.model.request;

import com.farmigo.server.domain.farm.model.vo.Search;
import com.farmigo.server.global.model.request.OffsetRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description : 농장 활동 목록 조회 request
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FarmActivityListRequest extends OffsetRequest {
    @Schema(description = "검색 조건")
    private Search search;
}
