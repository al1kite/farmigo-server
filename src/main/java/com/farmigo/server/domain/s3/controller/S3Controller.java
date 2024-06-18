package com.farmigo.server.domain.s3.controller;

import com.farmigo.server.domain.s3.model.request.PreSignedUrlRequest;
import com.farmigo.server.domain.s3.model.response.PreSignedUrlResponse;
import com.farmigo.server.domain.s3.service.S3Service;
import com.farmigo.server.global.model.request.Request;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description : S3 업로드(이미지) 관련 컨트롤러
 */
@Tag(name = "S3", description = "S3 업로드(이미지) 관련 컨트롤러")
@RestController
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service s3Service;

    /**
     * S3 업로드할 presigned url 생성
     */
    @Operation(summary = "S3 업로드할 presigned url 생성", description = """
            S3 업로드할 presigned url 생성.
            type : S3 저장 타입//profile, farm, activity
            idMap : S3 저장 필요 ID MAP
            
            해당 presigned url로 파일 PUT으로 직접 업로드
            """)
    @PostMapping("/api/s3/pre-signed")
    public PreSignedUrlResponse preSign(Request param, @RequestBody PreSignedUrlRequest req) {
        // Todo : 델리게이트 구조로 변경?
        return s3Service.getPreSignedUrl(req);
    }

    /**
     * S3 이미지 업로드
     */
    @Operation(summary = "S3 업로드할이미지 업로드", description = """
            S3 이미지 업로드.
            s3path : s3에 저장될 경로(presigned url 아님) ex: /farmigo/profile/1.jpg
            """)
    @PostMapping(value = "/api/s3/upload/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PreSignedUrlResponse upload(Request param, @RequestParam("s3path") String s3path, @RequestPart("file") MultipartFile file) throws IOException {
        // Todo : 델리게이트 구조로 변경?
        String path = s3Service.uploadImage(file, s3path);
        return PreSignedUrlResponse.builder().url(path).build();
    }
}
