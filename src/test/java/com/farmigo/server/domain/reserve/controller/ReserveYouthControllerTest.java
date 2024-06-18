package com.farmigo.server.domain.reserve.controller;

import com.farmigo.server.config.ControllerTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @Description : 청년 예약 관련 컨트롤러 테스트
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReserveYouthControllerTest extends ControllerTestConfig {

    @Test
    @DisplayName("견적서_생성")
    public void 견적서_생성() throws Exception {
        this.mockMvc
                .perform(post("/api/reserve/activity/detail"))
                .andDo(print());
    }
}