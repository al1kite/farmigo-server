package com.farmigo.server.domain.auth.model.vo;

import com.farmigo.server.domain.auth.model.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String userId;
    // 이메일
    private String email;

    // 이름
    private String name;

    // 성별
    private String sex;

    // 생일
    private Date birth;

    // 핸드폰 번호
    private String phoneNumber;

    // 추후 확장을 위한 password
    private String password;

    // 프로필 이미지
    private String profileUrl;

    // 로그인 서비스 종류 - kakao, naver, google..
    private String provider;

    // 청년, 농가
    private Role role;

}
