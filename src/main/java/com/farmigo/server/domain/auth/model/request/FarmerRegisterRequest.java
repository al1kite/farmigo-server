package com.farmigo.server.domain.auth.model.request;

import com.farmigo.server.domain.auth.model.vo.ActivityBriefInfo;
import com.farmigo.server.domain.auth.model.vo.FarmDetailInfo;
import com.farmigo.server.domain.auth.model.vo.UserInfo;
import com.farmigo.server.global.model.request.Request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmerRegisterRequest extends Request {
    UserInfo userInfo;
    FarmDetailInfo farm;
    List<ActivityBriefInfo> activityInfo;
}
