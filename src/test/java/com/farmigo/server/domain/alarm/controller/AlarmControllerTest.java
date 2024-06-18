package com.farmigo.server.domain.alarm.controller;

import com.farmigo.server.config.ControllerTestConfig;
import com.farmigo.server.domain.alarm.model.response.AlarmResponse;
import com.farmigo.server.domain.alarm.service.AlarmService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Slf4j
@WebMvcTest(AlarmController.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("alpha")
public class AlarmControllerTest extends ControllerTestConfig {
    @MockBean
    private AlarmService alarmService;

    @Test
    @DisplayName("send_alarm_test")
    public void sendAlarm() throws Exception {
        MvcResult result = this.mockMvc
                .perform(post("/api/alarm/send")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("alarmType", "RESERVE")
                        .param("title", "string")
                        .param("content", "string")
                        .param("redirectUrl", "string"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = result.getResponse().toString();
        log.info(content);
    }

    @Test
    @DisplayName("alarm_list_test")
    public void getAlarmList() throws Exception {
        given(alarmService.getAlarmList()).willReturn(new AlarmResponse());

        this.mockMvc
                .perform(post("/api/alarm/list"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
