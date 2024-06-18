package com.farmigo.server.domain.reserve.model.response;

import com.farmigo.server.domain.reserve.model.vo.ActivityReserveSummary;
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
public class FarmerReserveListResponse extends Response {
    private List<ActivityReserveSummary> reserves;
    private String offset;
}
