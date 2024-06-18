package com.farmigo.server.domain.reserve.model.vo;

import com.farmigo.server.global.model.mongo.master.MST_ACTIVITY;
import com.farmigo.server.global.model.mongo.user.USER_ACTIVITY_RESERVE;
import lombok.*;

/**
 * @Description :
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityReserveSummary {
    private String reserveId;
    private String userId;
    private long createTime;
    private int status;

    // 체험 정보
    private String activityId;
    private String activityName;
    private String activityDesc;

    public ActivityReserveSummary(USER_ACTIVITY_RESERVE reserve, MST_ACTIVITY activity) {
        this.reserveId = reserve.getReserveId();
        this.activityId = reserve.getActivityId();
        this.userId = reserve.getUserId();
        this.createTime = reserve.getCreateTime();
        this.status = reserve.getStatus();

        this.activityName = activity.getName();
        this.activityDesc = activity.getDescription();
    }
}
