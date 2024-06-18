package com.farmigo.server.domain.chat.model.request;

import com.farmigo.server.global.model.request.OffsetRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description :
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshMessageRequest extends OffsetRequest {
    private String chatRoomId;
}
