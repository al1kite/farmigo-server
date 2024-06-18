package com.farmigo.server.domain.auth.model.response;

import com.farmigo.server.global.model.response.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse extends Response {
    private String accessToken;
}
