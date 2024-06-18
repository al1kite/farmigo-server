package com.farmigo.server.domain.farm.model.vo;

import com.farmigo.server.global.model.vo.PriceInfo;
import lombok.*;

import java.util.List;

/**
 * @Description : 농장 체험 정보 요약 Vo
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityInfoSummary {
    private String activityId;
    private String activityName;
    private List<String> activityThumbnail;
    private PriceInfo priceInfo;
    private FarmInfoSummary farmInfo;
}
