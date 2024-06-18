package com.farmigo.server.global.model.common.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description : 유저 접속 기기 정보
 */
@Getter
@Setter
@Builder
public class UserAgent {
    public static final String user_agent = "User-Agent";
    public static final String prefix = "Farmigo";

    // user agent string
    // User-Agent: <client_name>/<client_version> (appid:<appid>; <device>; <OS_Type> <OS_Version>; )
    // User-Agent: Farmigo/1.0.0.1 (appid:20004001; iPhone13,2; ios 16.3.1; )
    private String clientName;         // db 저장하지 않음
    private String osType;
    private String clientVersion;
    private int appid;
    private String device;
    private String osVersion;

    private String ipv4;
    private boolean exist;  // 유저 에이전트가 제대로 파싱이 되었는지 플래그. true : 유저 에이전트가 있음.
}
