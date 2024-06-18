package com.farmigo.server.global.model.mongo.master;

import com.farmigo.server.domain.profile.model.enumeration.ActivityType;
import com.farmigo.server.global.constant.MongoField;
import com.farmigo.server.global.model.vo.HarvestInfo;
import com.farmigo.server.global.model.vo.PriceInfo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @Description :
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "MST_ACTIVITY")
public class MST_ACTIVITY {
    @Id
    @Field(MongoField._ID)
    private String activityId;

    @Field(MongoField.FARM_ID)
    private String farmId;

    private String name;
    private List<String> thumbnail;
    private String description;

    @Field(MongoField.PRICE_INFO)
    private PriceInfo priceInfo;

    @Field(MongoField.ACTIVITY_TYPE)
    private ActivityType activityType;

    private Integer difficulty;

    @Field(MongoField.HARVEST_INFO)
    private HarvestInfo harvestInfo;

    @Field(MongoField.START_TIME)
    private long startTime;
    @Field(MongoField.END_TIME)
    private long endTime;
}
