package com.farmigo.server.domain.chat.controller;

import com.farmigo.server.domain.chat.delegate.ChatDelegate;
import com.farmigo.server.domain.chat.model.request.CreateChatRoomRequest;
import com.farmigo.server.domain.chat.model.request.GetChatRoomListRequest;
import com.farmigo.server.domain.chat.model.request.RefreshMessageRequest;
import com.farmigo.server.domain.chat.model.request.SendMessageRequest;
import com.farmigo.server.domain.chat.model.response.CreateChatRoomResponse;
import com.farmigo.server.domain.chat.model.response.GetChatRoomListResponse;
import com.farmigo.server.domain.chat.model.response.RefreshMessageResponse;
import com.farmigo.server.domain.chat.model.response.SendMessageResponse;
import com.farmigo.server.global.model.request.Request;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description : 채팅 관련 컨트롤러
 */
@Tag(name = "Chat", description = "채팅 관련 컨트롤러")
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatDelegate chatDelegate;

    @Operation(summary = "채팅방 생성", description = """
            채팅방을 생성한다
            """)
    @PostMapping("/api/chat/room/create")
    public CreateChatRoomResponse createChatRoom(Request request, @RequestBody CreateChatRoomRequest req) {
        return chatDelegate.createChatRoom(request.getTxTime(), req);
    }

    @Operation(summary = "채팅방 조회", description = """
            채팅방 목록을 조회한다
            """)
    @PostMapping("/api/chat/room/list")
    public GetChatRoomListResponse getChatRoomList(Request request, @RequestBody GetChatRoomListRequest req) {
        return chatDelegate.getChatRoomList(request.getTxTime(), req);
    }

    @Operation(summary = "메세지를 전송한다", description = """
            메세지를 전송한다
            """)
    @PostMapping("/api/chat/message/send")
    public SendMessageResponse sendMessage(Request request, @RequestBody SendMessageRequest req) {
        return chatDelegate.sendMessage(request.getTxTime(), req);
    }

    @Operation(summary = "메세지를 최신화한다", description = """
            메세지 리스트를 최신화한다
            """)
    @PostMapping("/api/chat/message/refresh")
    public RefreshMessageResponse refreshMessage(Request request, @RequestBody RefreshMessageRequest req) {
        return chatDelegate.refreshMessage(request.getTxTime(), req);
    }
}
