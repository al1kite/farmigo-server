package com.farmigo.server.domain.chat.model.response;

import com.farmigo.server.domain.chat.model.vo.ChatMessage;
import com.farmigo.server.global.model.response.Response;
import lombok.*;

/**
 * @Description :
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SendMessageResponse extends Response {
    private ChatMessage sendMessage;
    private ChatMessage receiveMessage;
}
