package com.farmigo.server.domain.reserve.model.request;

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
public class FarmActivityReserveRequest {
    @Schema(description = "요청 청년 userId", example = "0")
    private String userId;
    @Schema(description = "요청 체험 ID", example = "TESTFARM_ACTID01")
    private String activityId;
    @Schema(description = "시작 시간(timestdamp MilliSecond) yyyymmdd 00:00:00", example = "1711897200000")
    private long startTime;
    @Schema(description = "종료 시간(timestamp MilliSecond) yyyymmdd 00:00:00", example = "1711983600000")
    private long endTime;
}
