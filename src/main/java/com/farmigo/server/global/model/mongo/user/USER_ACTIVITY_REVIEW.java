package com.farmigo.server.global.model.mongo.user;

import com.farmigo.server.global.constant.MongoField;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @Description :
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "USER_ACTIVITY_REVIEW")
public class USER_ACTIVITY_REVIEW {
    @Id
    private String reviewId;
    @Field(MongoField.ACTIVITY_ID)
    private String activityId;
    @Field(MongoField.USER_ID)
    private String userId;
    private String review;
    @Field(MongoField.CREATE_TIME)
    private long createTime;
    private int score;  // 10점만점
}
