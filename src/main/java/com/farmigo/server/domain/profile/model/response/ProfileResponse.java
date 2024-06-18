package com.farmigo.server.domain.profile.model.response;

import com.farmigo.server.global.model.response.Response;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProfileResponse extends Response {
    // 청년은 전화번호, 농가는 농가 이름
    private String description;
    private String name;
    private String profileUrl;
}
