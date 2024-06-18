package com.farmigo.server.global.model.mongo.user;

import com.farmigo.server.global.constant.MongoField;
import com.farmigo.server.global.model.vo.PriceInfo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * @Description : 유저 체험 예약 정보
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "USER_ACTIVITY_RESERVE")
public class USER_ACTIVITY_RESERVE {
    @Id
    private String reserveId;   // createTime_activityId_userId
    @Field(MongoField.ACTIVITY_ID)
    private String activityId;
    @Field(MongoField.FARM_ID)
    private String farmId;
    @Field(MongoField.USER_ID)
    private String userId;
    @Field(MongoField.START_TIME)
    private LocalDateTime startTime; // 체험 시작 시간 KST
    @Field(MongoField.END_TIME)
    private LocalDateTime endTime;   // 체험 종료 시간 KST
    @Field(MongoField.CREATE_TIME)
    private long createTime;
    @Field(MongoField.PRICE_INFO)
    private PriceInfo priceInfo;  // 가격 정보
    private int status;  // 0: 예약서 생성, 1: 예약 요청, 2: 예약 완료, 3: 체험 완료, 4: 예약 취소, 9: 에러
}
