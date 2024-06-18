package com.farmigo.server.global.model.mongo.user;

import com.farmigo.server.domain.chat.constant.MessageType;
import com.farmigo.server.global.constant.MongoField;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @Description : 유저 채팅 메세지
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "USER_CHAT_MESSAGE")
public class USER_CHAT_MESSAGE {
    @Id
    @Field(MongoField._ID)
    private long seq;

    @Field(MongoField.CHAT_ROOM_ID)
    private String chatRoomId;
    private long sendId;
    private String message;
    private MessageType type;
    @Field(MongoField.CREATE_TIME)
    private long ct;
}
