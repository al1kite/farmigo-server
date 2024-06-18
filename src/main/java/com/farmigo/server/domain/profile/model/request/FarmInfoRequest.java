package com.farmigo.server.domain.profile.model.request;

import com.farmigo.server.domain.farm.model.vo.ActivityInfo;
import com.farmigo.server.domain.auth.model.vo.FarmDetailInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmInfoRequest {
    private FarmDetailInfo farm;
    private ActivityInfo activityInfo;

}
