package com.farmigo.server.domain.farm.model.vo;

import com.farmigo.server.domain.profile.model.enumeration.ActivityType;
import com.farmigo.server.global.model.mongo.master.MST_ACTIVITY;
import com.farmigo.server.global.model.vo.PriceInfo;
import lombok.*;

import java.util.List;

/**
 * @Description : 농장 체험 정보 Vo
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityInfo {

    private String activityId;
    private String farmId;
    private String name;
    private List<String> thumbnail;
    private String description;
    private Integer difficulty;
    private PriceInfo priceInfo;
    private ActivityType activityType;

    public ActivityInfo(MST_ACTIVITY activity) {
        this.activityId = activity.getActivityId();
        this.farmId = activity.getFarmId();
        this.name = activity.getName();
        this.thumbnail = activity.getThumbnail();
        this.description = activity.getDescription();
    }
}
