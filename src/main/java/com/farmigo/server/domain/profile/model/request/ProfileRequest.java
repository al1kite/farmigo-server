package com.farmigo.server.domain.profile.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {
    private String phoneNumber;
    private String name;
    private String profileUrl;
}
