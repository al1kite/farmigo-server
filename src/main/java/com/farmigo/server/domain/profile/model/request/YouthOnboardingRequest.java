package com.farmigo.server.domain.profile.model.request;

import com.farmigo.server.domain.auth.model.vo.UserActivityStyleInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YouthOnboardingRequest {
   UserActivityStyleInfo userActivityStyle;
}
