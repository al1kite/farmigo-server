package com.farmigo.server.global.model.vo;

import com.farmigo.server.global.constant.MongoField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HarvestInfo {
    @Field(MongoField.HARVEST_CROP)
    private String harvestCrop;

    @Field(MongoField.HARVEST_MONTH)
    private List<Integer> harvestMonth;
}
