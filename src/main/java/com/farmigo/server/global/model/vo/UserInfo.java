package com.farmigo.server.global.model.vo;

import lombok.*;

/**
 * @Description :
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String name;
    private String type;    // farmer, youth
}
