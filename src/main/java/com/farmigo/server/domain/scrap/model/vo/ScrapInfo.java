package com.farmigo.server.domain.scrap.model.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScrapInfo {
    private String scrapId;

    private String userId;

    private String activityId;

    private String farmName;

    private String activityTitle;

    private String location;
}
