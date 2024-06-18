package com.farmigo.server.domain.alarm.service;

import com.farmigo.server.domain.auth.security.LoginManager;
import com.farmigo.server.global.model.mongo.user.USER_INFO;
import com.farmigo.server.global.model.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.ArrayList;
import java.util.Collection;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class AlarmServiceTest {
    @InjectMocks
    private AlarmService alarmService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Bean
    public Authentication authentication() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_FARM"));
        return new UsernamePasswordAuthenticationToken("string", "string", authorities);
    }


    @Test
    @WithMockUser(username = "string", password = "string")
    @WithUserDetails("string")
    void getAlarmList() throws Exception {
        // arrange
        USER_INFO userInfo = mock(USER_INFO.class);
        when(userInfo.getUserId()).thenReturn("6657d783cc06fd3b9d2d855e");

        // convert LoginManager to mock entity
        mockStatic(LoginManager.class);
        when(LoginManager.getUserDetails()).thenReturn(userInfo);

        // when
        Response alarmResponses = alarmService.getAlarmList();

        // then
        assertNotNull(alarmResponses);
        assertDoesNotThrow(() -> Exception.class);
    }

}
