package com.farmigo.server.domain.auth.model.request;

import com.farmigo.server.domain.auth.model.vo.UserActivityStyleInfo;
import com.farmigo.server.domain.auth.model.vo.UserInfo;
import com.farmigo.server.global.model.mongo.user.USER_ACTIVITY_STYLE;
import com.farmigo.server.global.model.mongo.user.USER_INFO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YouthRegisterRequest {
    UserInfo userInfo;
    UserActivityStyleInfo userActivityStyle;
}
