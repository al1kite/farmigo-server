package com.farmigo.server.domain.farm.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

/**
 * @Description : 검색 필드
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Search {
    @Schema(description = "날짜")
    private DateVo date;
    @Schema(description = "자치구", example = "[\"강원도\"]")
    private List<String> state;
    @Schema(description = "도시", example = "[\"원주시\"]")
    private List<String> city;
//    private List<String> district;
    @Schema(description = "가격정보")
    private BudgetVo budget;
    @Schema(description = "체험 카테고리, 빈 리스트 전송 시 검색 X", example = "[]")
    private List<String> category;
    @Schema(description = "체험 별점, 빈 리스트 전송 시 검색 X", example = "[1, 2, 3, 4, 5]")
    private List<Integer> star; // 난이도

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DateVo {
        @Schema(description = "시작일 epoch date, 디폴트 0(검색X)", example = "0")
        private long start = 0L; // default 0
        @Schema(description = "종료일 epoch date, 디폴트 0(검색X)", example = "0")
        private long end = 0L;   // default 0
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BudgetVo {
        @Schema(description = "가걱 최솟값, 디폴트 0(검색X)", example = "0")
        private int min = 0;    // default 0
        @Schema(description = "가걱 최댓값, 디폴트 0(검색X)", example = "0")
        private int max = 0;    // default 0
    }
}
