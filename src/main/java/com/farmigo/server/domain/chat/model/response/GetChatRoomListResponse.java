package com.farmigo.server.domain.chat.model.response;

import com.farmigo.server.global.model.mongo.user.USER_CHAT_ROOM;
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
public class GetChatRoomListResponse extends Response {
    private List<USER_CHAT_ROOM> roomList;
}
