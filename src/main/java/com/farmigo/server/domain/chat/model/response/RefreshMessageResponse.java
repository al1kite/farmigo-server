package com.farmigo.server.domain.chat.model.response;

import com.farmigo.server.domain.chat.model.vo.ChatMessage;
import com.farmigo.server.global.model.response.Response;
import lombok.*;

import java.util.List;

/**
 * @Description :
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RefreshMessageResponse extends Response {
    private List<ChatMessage> messages;
}
