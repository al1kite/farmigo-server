package com.farmigo.server.global.model.common.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description : 서비스 유저 정보
 */
@Getter
@Setter
@Builder
public class UserContext {
    private long id;
    private String token;
}
