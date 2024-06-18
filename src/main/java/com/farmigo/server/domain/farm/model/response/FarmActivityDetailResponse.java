package com.farmigo.server.domain.farm.model.response;

import com.farmigo.server.domain.farm.model.vo.ActivityInfo;
import com.farmigo.server.domain.farm.model.vo.FarmInfo;
import com.farmigo.server.domain.farm.model.vo.FarmerInfo;
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
public class FarmActivityDetailResponse extends Response {
    private ActivityInfo activityInfo;
    private FarmInfo farmInfo;
    private FarmerInfo farmerInfo;
}
