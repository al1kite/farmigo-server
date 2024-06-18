package com.farmigo.server.domain.profile.model.response;

import com.farmigo.server.domain.auth.model.vo.ActivityBriefInfo;
import com.farmigo.server.domain.auth.model.vo.FarmDetailInfo;
import com.farmigo.server.domain.auth.model.vo.UserInfo;
import com.farmigo.server.global.model.response.Response;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class FarmerProfileResponse extends Response {
   UserInfo userInfo;
   FarmDetailInfo farmInfo;
   List<ActivityBriefInfo> activityInfo;
}
