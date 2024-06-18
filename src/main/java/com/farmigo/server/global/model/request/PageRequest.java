package com.farmigo.server.global.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

/**
 * @Description : 페이지네이션 Request
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {

    private PageVo page;    // Page 정보

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PageVo {

        @Schema(description = "가져올 페이지 수", example = "0")
        private Integer currentPage;    // 가져올 페이지 수

        @Schema(description = "가져올 데이터 개수", example = "10")
        private Integer limit;  // 가져올 데이터 개수

        @Schema(description = "오름차순=True, 내림차순=False", example = "True")
        private boolean asc;    // 오름차순/내림차순

        public int getCurrentPage() {
            return this.currentPage != null ? this.currentPage : 1;
        }

        public int getLimit() {
            return this.limit != null ? this.limit : 20;
        }

        public int getSkip() {
            return this.getLimit() * (this.getCurrentPage() - 1);
        }

        public Sort.Direction getDirection() {
            return asc ? Sort.Direction.ASC : Sort.Direction.DESC;
        }
    }
}
