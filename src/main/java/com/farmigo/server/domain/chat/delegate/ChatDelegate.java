package com.farmigo.server.domain.chat.delegate;

import com.farmigo.server.domain.chat.model.request.CreateChatRoomRequest;
import com.farmigo.server.domain.chat.model.request.GetChatRoomListRequest;
import com.farmigo.server.domain.chat.model.request.RefreshMessageRequest;
import com.farmigo.server.domain.chat.model.request.SendMessageRequest;
import com.farmigo.server.domain.chat.model.response.CreateChatRoomResponse;
import com.farmigo.server.domain.chat.model.response.GetChatRoomListResponse;
import com.farmigo.server.domain.chat.model.response.RefreshMessageResponse;
import com.farmigo.server.domain.chat.model.response.SendMessageResponse;
import com.farmigo.server.domain.chat.model.vo.ChatMessage;
import com.farmigo.server.domain.chat.service.ChatService;
import com.farmigo.server.global.model.mongo.user.USER_CHAT_ROOM;
import com.farmigo.server.global.model.response.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description :
 */
@Slf4j
@Service
@AllArgsConstructor
public class ChatDelegate {

    private final ChatService chatService;

    public CreateChatRoomResponse createChatRoom(long txTime, CreateChatRoomRequest req) {
        String chatRoomId = chatService.createChatRoom(txTime, req.getUserId(), req.getFarmId());

        return CreateChatRoomResponse.builder()
                .chatRoomId(chatRoomId)
                .build();
    }

    public GetChatRoomListResponse getChatRoomList(long txTime, GetChatRoomListRequest req) {
        List<USER_CHAT_ROOM> roomList = chatService.getChatRoomList(txTime, req.getUserId());

        return GetChatRoomListResponse.builder()
                .roomList(roomList)
                .build();
    }

    public SendMessageResponse sendMessage(long txTime, SendMessageRequest req) {
        ChatMessage sendMessage = chatService.sendMessage(txTime, req.getMessage());

        return SendMessageResponse.builder()
                .sendMessage(sendMessage)
                .build();
    }

    public RefreshMessageResponse refreshMessage(long txTime, RefreshMessageRequest req) {
        List<ChatMessage> messageList = chatService.refreshMessage(txTime, req.getChatRoomId(), req.getOffset(), req.getLimit());

        return RefreshMessageResponse.builder()
                .messages(messageList)
                .build();
    }
}
