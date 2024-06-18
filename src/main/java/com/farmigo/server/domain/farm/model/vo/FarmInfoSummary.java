package com.farmigo.server.domain.farm.model.vo;

import com.farmigo.server.global.model.mongo.master.MST_FARM;
import com.farmigo.server.global.model.vo.LocationInfo;
import lombok.*;

/**
 * @Description : 농장 정보 요약 Vo
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmInfoSummary {
    private String farmId;
    private String farmName;
    private LocationInfo farmAddress;

    public FarmInfoSummary(MST_FARM farm) {
        this.farmId = farm.getFarmId();
        this.farmName = farm.getName();
        this.farmAddress = farm.getAddress();
    }
}
