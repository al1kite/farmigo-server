package com.farmigo.server.domain.profile.model.request;

import com.farmigo.server.domain.auth.model.vo.ActivityBriefInfo;
import com.farmigo.server.domain.auth.model.vo.FarmDetailInfo;
import com.farmigo.server.domain.auth.model.vo.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmerProfileRequest {
  private UserInfo userInfo;
  private FarmDetailInfo farmInfo;
  List<ActivityBriefInfo> activityInfo;
}
