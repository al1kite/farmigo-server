package com.farmigo.server.domain.chat.model.response;

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
public class CreateChatRoomResponse extends Response {
    private String chatRoomId;
}
