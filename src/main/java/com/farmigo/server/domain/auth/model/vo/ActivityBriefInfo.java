package com.farmigo.server.domain.auth.model.vo;

import com.farmigo.server.global.model.vo.HarvestInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityBriefInfo {
    private String activityId;
    private String farmId;
    private String name;
    private HarvestInfo harvestInfo;
}
