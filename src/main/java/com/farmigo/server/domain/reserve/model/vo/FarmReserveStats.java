package com.farmigo.server.domain.reserve.model.vo;

import lombok.*;
import org.springframework.data.annotation.Id;

/**
 * @Description : 농장 추이 통계 VO
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmReserveStats {
    @Id
    private String month;   // 202401
    private int count;
}
