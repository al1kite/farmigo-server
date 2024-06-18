package com.farmigo.server.domain.profile.model.request;

import com.farmigo.server.domain.profile.model.enumeration.ReserveType;
import com.farmigo.server.global.model.request.OffsetRequest;
import com.farmigo.server.global.model.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveSearchRequest extends OffsetRequest {
    private ReserveType reserveType;
}
