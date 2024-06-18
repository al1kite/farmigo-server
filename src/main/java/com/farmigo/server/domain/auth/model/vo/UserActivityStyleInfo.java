package com.farmigo.server.domain.auth.model.vo;

import com.farmigo.server.domain.auth.model.enumeration.Companion;
import com.farmigo.server.domain.auth.model.enumeration.LeisureInterest;
import com.farmigo.server.domain.auth.model.enumeration.LeisureStyle;
import com.farmigo.server.global.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityStyleInfo {
    private String userId;

    // 체험 및 엑티비티(A)..
    @EnumValue(enumClass = LeisureStyle.class, ignoreCase = true)
    private LeisureStyle leisureStyle;

    // 혼자(A), 친구와(B), 연인과(C)..
    @EnumValue(enumClass = Companion.class, ignoreCase = true)
    private Companion companion;

    // 촌캉스(A), 작물수확체험(B)..
    @EnumValue(enumClass = LeisureInterest.class, ignoreCase = true)
    private List<LeisureInterest> leisureInterest;

    // 여가 최대 기간
    private Long leisureMaxPeriod;

    // 여가 최소 기간
    private Long leisureMinPeriod;

    // 여가 최대 예산
    private Long leisureMaxBudget;

    // 여가 최소 예산
    private Long leisureMinBudget;
}
