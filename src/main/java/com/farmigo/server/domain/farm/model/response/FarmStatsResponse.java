package com.farmigo.server.domain.farm.model.response;

import com.farmigo.server.domain.reserve.model.vo.FarmReserveStats;
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
public class FarmStatsResponse extends Response {
    private List<FarmReserveStats> stats;
}
