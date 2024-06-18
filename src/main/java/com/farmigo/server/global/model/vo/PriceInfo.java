package com.farmigo.server.global.model.vo;

import com.farmigo.server.global.constant.MongoField;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

/**
 * @Description : 가격정보
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceInfo {
    @Field(MongoField.TOTAL_PRICE)
    private int totalPrice;
    @Field(MongoField.PRICE_MAP)
    private Map<String, Integer> priceMap;  // 견적 정보. 원화 기준
}
