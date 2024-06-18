package com.farmigo.server.domain.farm.model.vo;

import com.farmigo.server.global.model.mongo.master.MST_FARM;
import lombok.*;

/**
 * @Description : 농장 정보 Vo
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmInfo {

    private String farmId;
    private String farmerId;
    private String name;
    private String description;

    public FarmInfo(MST_FARM farm) {
        this.farmId = farm.getFarmId();
        this.farmerId = farm.getFarmerId();
        this.name = farm.getName();
        this.description = farm.getDescription();
    }
}
