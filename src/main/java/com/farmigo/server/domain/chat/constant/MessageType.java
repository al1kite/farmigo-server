package com.farmigo.server.domain.chat.constant;

import lombok.Getter;

/**
 * @Description : 채팅 타입 Enum
 */
@Getter
public enum MessageType {
    DEFAULT,
    RECIPE,
    INFO,
    QUESTION,
    ANSWER;

}
