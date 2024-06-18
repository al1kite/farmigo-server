package com.farmigo.server.domain.auth.model.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;

    private String pw;
}
