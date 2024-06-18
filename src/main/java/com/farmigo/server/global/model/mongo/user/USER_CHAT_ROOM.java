package com.farmigo.server.global.model.mongo.user;

import com.farmigo.server.global.constant.MongoField;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @Description : 유저 채팅방 정보
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "USER_CHAT_ROOM")
public class USER_CHAT_ROOM {
    @Id
    private String chatRoomId;  // farm_id + user_id
    @Field(MongoField.USER_ID)
    private long userId;
    @Field(MongoField.FARM_ID)
    private String farmId;
    @Field(MongoField.CREATE_TIME)
    private long createTime;
    @Field(MongoField.UPDATE_TIME)
    private long updateTime;    // 채팅방 마지막 업데이트 시간
    private int status;  // 0: 채팅방 생성, 1: 채팅방 삭제
}
