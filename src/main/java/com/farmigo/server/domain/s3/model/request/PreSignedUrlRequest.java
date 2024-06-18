package com.farmigo.server.domain.s3.model.request;

import com.farmigo.server.domain.s3.constant.S3Path;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description : S3 업로드할 presigned url 생성 요청
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PreSignedUrlRequest {
    @Schema(description = "S3 저장 타입//PROFILE, FARM, ACTIVITY", example = "PROFILE")
    private S3Path type;
    @Schema(description = "이미지명", example = "img.png")
    private String fileName;
    @Schema(description = "S3 저장 필요 ID MAP")
    private IdMap idMap;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IdMap{
        @Schema(description = "profile 에 필요", example = "0")
        private String userId;
        @Schema(description = "farm, activity 에 필요", example = "TESTFARM")
        private String farmId;
        @Schema(description = "activity 에 필요", example = "TESTFARM_ACTID01")
        private String activityId;
    }
}
