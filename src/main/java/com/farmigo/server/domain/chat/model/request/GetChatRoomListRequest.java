package com.farmigo.server.domain.chat.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description : 채팅방 목록 조회 request
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetChatRoomListRequest {
    private long userId;
}
