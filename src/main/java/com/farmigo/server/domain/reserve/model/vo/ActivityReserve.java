package com.farmigo.server.domain.reserve.model.vo;

import com.farmigo.server.global.model.mongo.master.MST_ACTIVITY;
import com.farmigo.server.global.model.mongo.user.USER_ACTIVITY_RESERVE;
import com.farmigo.server.global.model.vo.PriceInfo;
import lombok.*;

/**
 * @Description :
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityReserve {
    private String reserveId;
    private String userId;
    private long createTime;
    private int status; // 0: 예약서 생성, 1: 예약 요청, 2: 예약 승인, 3: 예약 거절, 4: 사용자 예약 취소, 5: 체험 완료, 9: 에러
    private PriceInfo priceInfo;

    // 체험 정보
    private String activityId;
    private String activityName;
    private String activityDesc;

    public ActivityReserve(USER_ACTIVITY_RESERVE reserve, MST_ACTIVITY activity) {
        this.reserveId = reserve.getReserveId();
        this.activityId = reserve.getActivityId();
        this.userId = reserve.getUserId();
        this.createTime = reserve.getCreateTime();
        this.status = reserve.getStatus();
        this.priceInfo = reserve.getPriceInfo();

        this.activityName = activity.getName();
        this.activityDesc = activity.getDescription();
    }
}
