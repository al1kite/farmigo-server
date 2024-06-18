package com.farmigo.server.domain.profile.model.response;

import com.farmigo.server.domain.auth.model.vo.UserActivityStyleInfo;
import com.farmigo.server.domain.auth.model.vo.UserInfo;
import com.farmigo.server.global.model.response.Response;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class YouthProfileResponse extends Response {
    UserInfo userInfo;
    UserActivityStyleInfo userActivityStyle;
}
