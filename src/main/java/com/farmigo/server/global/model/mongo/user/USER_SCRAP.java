package com.farmigo.server.global.model.mongo.user;

import com.farmigo.server.global.constant.MongoField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "USER_SCRAP")
@SuperBuilder
@NoArgsConstructor
public class USER_SCRAP {
    @Id
    @Field(MongoField._ID)
    private String scrapId;

    @Field(MongoField.USER_ID)
    private String userId;

    @Field(MongoField.ACTIVITY_ID)
    private String activityId;

    @Field(MongoField.CREATE_TIME)
    private long createTime;

    private Boolean isDeleted;

}
