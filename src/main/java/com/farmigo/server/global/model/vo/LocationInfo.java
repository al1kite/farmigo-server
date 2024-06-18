package com.farmigo.server.global.model.vo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import com.farmigo.server.global.constant.MongoField;

/**
 * @Description : 위치 정보 Vo
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationInfo {
    @Field(MongoField.FULL_ADDRESS)
    private String fullAddress;

    private String state;       // 도 (강원도, 경기도, ...)
    private String city;        // 시
    private String district;    // 구
    private String explanation;  // 추가 설명
}
