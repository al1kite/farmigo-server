package com.farmigo.server.domain.chat.service;

import com.farmigo.server.domain.chat.model.vo.ChatMessage;
import com.farmigo.server.domain.chat.repository.UserChatMessageMongoRepository;
import com.farmigo.server.domain.chat.repository.UserChatRoomMongoRepository;
import com.farmigo.server.global.model.mongo.user.USER_CHAT_MESSAGE;
import com.farmigo.server.global.model.mongo.user.USER_CHAT_ROOM;
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
public class ChatService {

    private final UserChatRoomMongoRepository userChatRoomMongoRepository;
    private final UserChatMessageMongoRepository userChatMessageMongoRepository;

    public String createChatRoom(long txTime, long userId, String farmId) {
        String chatRoomId = makeRoomId(userId, farmId);

        USER_CHAT_ROOM userChatRoom = USER_CHAT_ROOM.builder()
                .chatRoomId(chatRoomId)
                .userId(userId)
                .farmId(farmId)
                .createTime(txTime)
                .status(0)
                .build();
        userChatRoomMongoRepository.save(userChatRoom);

        return chatRoomId;
    }

    public List<USER_CHAT_ROOM> getChatRoomList(long txTime, long userId) {
        return userChatRoomMongoRepository.findByUserId(userId);
    }

    public ChatMessage sendMessage(long txTime, ChatMessage message) {
        long messageSeq = userChatMessageMongoRepository.getLastSequence() + 1;
        USER_CHAT_MESSAGE userChatMessage = USER_CHAT_MESSAGE.builder()
                .seq(messageSeq)
                .chatRoomId(message.getChatRoomId())
                .sendId(message.getSenderId())
                .message(message.getMessage())
                .type(message.getMessageType())
                .ct(txTime)
                .build();
        userChatMessageMongoRepository.save(userChatMessage);

        // TODO : push 전송
        return message;
    }

    public List<ChatMessage> refreshMessage(long txTime, String chatRoomId, String offset, int limit) {
        List<USER_CHAT_MESSAGE> userChatMessages = userChatMessageMongoRepository.findByChatRoomIdWithOffset(chatRoomId, offset, limit);
        USER_CHAT_ROOM userChatRoom = userChatRoomMongoRepository.findByChatRoomId(chatRoomId);

        return userChatMessages.stream().map(userChatMessage -> {
            return new ChatMessage(userChatMessage, userChatRoom);
        }).toList();
    }

    private String makeRoomId(long userId, String farmId) {
        return userId + "_" + farmId;
    }
}
