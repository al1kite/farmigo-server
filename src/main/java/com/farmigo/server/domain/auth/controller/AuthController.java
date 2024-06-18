package com.farmigo.server.domain.auth.controller;

import com.farmigo.server.domain.auth.model.request.FarmerRegisterRequest;
import com.farmigo.server.domain.auth.model.request.LoginRequest;
import com.farmigo.server.domain.auth.model.request.YouthRegisterRequest;
import com.farmigo.server.domain.auth.model.response.TokenResponse;
import com.farmigo.server.domain.auth.service.AuthService;
import com.farmigo.server.global.model.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
@Tag(name = "Auth", description = "회원가입, 로그인 관련 컨트롤러")
/**
 * 회원가입, 로그인 관련 Controller

 */
public class AuthController {

    private final AuthService authService;

    /**
     * 테스트용 - 로그인 Controller
     */
    @Operation(summary = "테스트용 - 로그인", description = """
             아이디, 비밀번호로 로그인 한다.
            """)
    @PostMapping("/login")
    public TokenResponse doLogin(HttpServletResponse response, LoginRequest loginRequest) {
        return authService.login(response, loginRequest);
    }

    /**
     * oAuth2 로그인 사용자 검증 Controller
     */
    @Operation(summary = "oAuth2 로그인 사용자 검증", description = """
             oAuth2 accessToken 으로 회원가입한 사용자인지 검증한다.
             회원가입한 사용자일 시 accessToken, refreshToken 발급
             아닐 시 "Resource Not Found(404) 에러 전송
             provider - google, naver
            """)
    @PostMapping("/verification/{provider}")
    public Response doVerification(HttpServletResponse response, @PathVariable("provider") String provider, @RequestParam(value = "accessToken") String accessToken){
        return authService.doVerification(response, provider, accessToken);
    }

    /**
     * 청년 회원 가입 요청 Controller
     */
    @Operation(summary = "청년 회원 가입 요청", description = """
             청년 회원 가입 한다.
            """)
    @PostMapping("/register/youth")
    public TokenResponse youthRegister(HttpServletResponse response, @RequestBody YouthRegisterRequest youthRegisterRequest) {
        return authService.youthRegister(response, youthRegisterRequest);
    }

    /**
     * 농가 회원 가입 요청 Controller
     */
    @Operation(summary = "농가 회원 가입 요청", description = """
             농가 회원 가입 한다.
            """)
    @PostMapping("/register/farmer")
    public TokenResponse farmerRegister(HttpServletResponse response, @RequestBody FarmerRegisterRequest farmerRegisterRequest) {
        return authService.farmerRegister(response, farmerRegisterRequest);

    }

}
