package com.farmigo.server.domain.chat.model.request;

import com.farmigo.server.domain.chat.model.vo.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description : 채팅 메시지 전송 요청
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageRequest {
    private String chatRoomId;
    private ChatMessage message;
}
