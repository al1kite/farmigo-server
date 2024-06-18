package com.farmigo.server.domain.auth.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    public List<String> loginEssential = List.of("/**");
    public List<String> loginInessential = List.of("/api/auth");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }

    private boolean checkIsLoginInessential(String uri) {
        for (String inessentialUrl : loginInessential) {
            if (new AntPathMatcher().match(inessentialUrl, uri)) {
                return true;
            }
        }
        return false;
    }

}
