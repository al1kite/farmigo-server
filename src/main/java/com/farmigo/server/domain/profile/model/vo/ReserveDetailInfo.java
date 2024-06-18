package com.farmigo.server.domain.profile.model.vo;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReserveDetailInfo {
    private long dDay;
    private String reserveId;
    private String activityId;
    private String activityName;
    private List<String> activityThumbnail;
}
