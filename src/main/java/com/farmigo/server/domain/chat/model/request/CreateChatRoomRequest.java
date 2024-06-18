package com.farmigo.server.domain.chat.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description : 채팅방 생성 request
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateChatRoomRequest {
    private long userId;
    private String farmId;
}
