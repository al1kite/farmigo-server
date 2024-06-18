package com.farmigo.server.domain.profile.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MinMaxPriceInfo {
    private Integer maxPrice;
    private Integer minPrice;
}
