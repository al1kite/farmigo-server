package com.farmigo.server.domain.s3.model.response;

import com.farmigo.server.global.model.response.Response;
import lombok.*;

/**
 * @Description :
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PreSignedUrlResponse extends Response {
    private String url;
    private String s3Path;
}
