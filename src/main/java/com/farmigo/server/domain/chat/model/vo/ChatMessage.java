package com.farmigo.server.domain.chat.model.vo;

import com.farmigo.server.domain.chat.constant.MessageType;
import com.farmigo.server.global.model.mongo.user.USER_CHAT_MESSAGE;
import com.farmigo.server.global.model.mongo.user.USER_CHAT_ROOM;
import lombok.*;

/**
 * @Description :
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private String chatRoomId;
    private long senderId;  // 메세지 전송항 사람의 ID
    private long userId;
    private String farmId;
    private String message;
    private MessageType messageType;

    public ChatMessage(USER_CHAT_MESSAGE userChatMessage, USER_CHAT_ROOM userChatRoom) {
        this.chatRoomId = userChatMessage.getChatRoomId();
        this.senderId = userChatMessage.getSendId();
        this.message = userChatMessage.getMessage();
        this.messageType = userChatMessage.getType();

        this.userId = userChatRoom.getUserId();
        this.farmId = userChatRoom.getFarmId();
    }
}
