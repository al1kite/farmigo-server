package com.farmigo.server.domain.profile.model.response;

import com.farmigo.server.domain.profile.model.vo.MinMaxPriceInfo;
import com.farmigo.server.global.model.response.Response;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PriceResponse extends Response {
    private MinMaxPriceInfo priceInfo;
}
