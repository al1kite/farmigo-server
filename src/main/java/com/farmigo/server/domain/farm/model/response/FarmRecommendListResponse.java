package com.farmigo.server.domain.farm.model.response;

import com.farmigo.server.domain.farm.model.vo.ActivityInfoSummary;
import com.farmigo.server.global.model.response.Response;
import lombok.*;

import java.util.List;

/**
 * @Description : 농장 추천 목록 조회 response
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FarmRecommendListResponse extends Response {
    private List<ActivityInfoSummary> activityInfos;    // 체험 내역
}
