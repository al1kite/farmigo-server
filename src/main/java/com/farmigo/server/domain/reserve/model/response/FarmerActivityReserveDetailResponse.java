package com.farmigo.server.domain.reserve.model.response;

import com.farmigo.server.domain.reserve.model.vo.ActivityReserve;
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
public class FarmerActivityReserveDetailResponse extends Response {
    private ActivityReserve activityReserve;
}
