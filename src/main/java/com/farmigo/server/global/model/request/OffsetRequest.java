package com.farmigo.server.global.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description : Offset Request
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OffsetRequest {
    @Schema(description = "검색 결과 마지막 ID, 디폴트 빈 스트링", example = " ")
    private String offset;
    @Schema(description = "개수", example = "10")
    private int limit;
}
