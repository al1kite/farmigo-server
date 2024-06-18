package com.farmigo.server.domain.auth.service;

import com.farmigo.server.domain.auth.mapper.FarmInfoMapper;
import com.farmigo.server.domain.auth.mapper.MstActivityMapper;
import com.farmigo.server.domain.auth.mapper.UserActivityStyleInfoMapper;
import com.farmigo.server.domain.auth.mapper.UserInfoMapper;
import com.farmigo.server.domain.auth.model.request.LoginRequest;
import com.farmigo.server.domain.auth.model.response.SocialOAuthResponse;
import com.farmigo.server.domain.auth.model.response.TokenResponse;
import com.farmigo.server.domain.auth.repository.UserTokenRepository;
import com.farmigo.server.domain.auth.security.JwtTokenProvider;
import com.farmigo.server.domain.farm.repository.MstActivityMongoRepository;
import com.farmigo.server.domain.farm.repository.MstFarmMongoRepository;
import com.farmigo.server.domain.profile.repository.UserActivityStyleMongoRepository;
import com.farmigo.server.global.model.mongo.master.MST_FARM;
import com.farmigo.server.global.model.mongo.user.USER_INFO;
import com.farmigo.server.domain.auth.model.request.FarmerRegisterRequest;
import com.farmigo.server.domain.auth.model.request.YouthRegisterRequest;
import com.farmigo.server.domain.auth.repository.UserRepository;
import com.farmigo.server.global.model.mongo.user.USER_TOKEN;
import com.farmigo.server.global.model.response.Response;
import com.farmigo.server.global.util.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import static com.farmigo.server.domain.auth.model.constant.AuthConstants.*;
import static com.farmigo.server.global.constant.ErrorCode.*;
import static com.farmigo.server.global.constant.MongoField.*;
import static com.farmigo.server.global.util.DateUtil.ONE_MONTH_IN_MILLISECONDS;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService  {

    private final UserRepository userRepository;
    private final MstFarmMongoRepository farmRepository;
    private final UserTokenRepository userTokenRepository;
    private final UserActivityStyleMongoRepository userActivityStyleRepository;
    private final MstActivityMongoRepository activityRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final FarmInfoMapper farmInfoMapper;
    private final UserInfoMapper userInfoMapper;
    private final MstActivityMapper activityMapper;
    private final UserActivityStyleInfoMapper userActivityStyleInfoMapper;

    public TokenResponse login(HttpServletResponse response, LoginRequest request){
        USER_INFO userInfo = userRepository.findUserByEmail(request.getEmail());
        return this.makeToken(response, userInfo);
    }

    public Response doVerification(HttpServletResponse response, String provider, String accessToken){

        String baseUrl;
        switch (provider) {
            case GOOGLE -> baseUrl = GOOGLE_VALIDATE_URL;
            case NAVER -> baseUrl = NAVER_VALIDATE_URL;
            default -> throw new RuntimeException();
        }

        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        AtomicReference<Response> errorResponse = new AtomicReference<>(new Response());
        SocialOAuthResponse oAuthResponse =
                webClient
                        .get()
                        .header(AUTHORIZATION, BEARER + accessToken)
                        .retrieve()
                        .bodyToMono(SocialOAuthResponse.class)
                        .onErrorResume(error -> {
                            if (error instanceof WebClientResponseException ex) {
                                errorResponse.set(Response.error(ex.getStatusCode().is4xxClientError() ? RESOURCE_NOT_FOUND : ERR_UNKNOWN));
                            }
                            return Mono.empty();
                        })
                        .block();

        if(!errorResponse.get().isSuccess())
            return errorResponse.get();

        USER_INFO userInfo;
        assert oAuthResponse != null;
        switch (provider) {
            case GOOGLE -> userInfo = userRepository.findUserByEmail(oAuthResponse.getEmail());
            case NAVER -> {
                assert oAuthResponse.getResponse() != null;
                userInfo = userRepository.findUserByEmail(oAuthResponse.getResponse().getEmail());
            }
            default -> throw new RuntimeException();
        }

        if (userInfo == null)
            return Response.error(RESOURCE_NOT_FOUND);

        return this.makeToken(response, userInfo);
    }

    public TokenResponse youthRegister(HttpServletResponse response, YouthRegisterRequest request) {
        long txTime = DateUtil.dateToEpochMilli(LocalDateTime.now());

        request.getUserInfo().setUserId(txTime + "_" +  this.makeRandomValue() +"_"+"USER");
        USER_INFO userInfo = userRepository.save(userInfoMapper.toEntity(request.getUserInfo()));

        request.getUserActivityStyle().setUserId(userInfo.getUserId());
        userActivityStyleRepository.save(userActivityStyleInfoMapper.toEntity(request.getUserActivityStyle()));

        return this.makeToken(response, userInfo);
    }

    public TokenResponse farmerRegister(HttpServletResponse response, FarmerRegisterRequest request) {
        long txTime = DateUtil.dateToEpochMilli(LocalDateTime.now());
        request.getUserInfo().setUserId(txTime + "_" + this.makeRandomValue() +"_"+"USER");

        USER_INFO userInfo = userRepository.save(userInfoMapper.toEntity(request.getUserInfo()));

        request.getFarm().setFarmerId(userInfo.getUserId());
        request.getFarm().setFarmId(txTime + "_" + this.makeRandomValue() +"_"+"FARM");
        MST_FARM farm = farmRepository.save(farmInfoMapper.toEntity(request.getFarm()));

        request.getActivityInfo().forEach(activityInfo -> {
            activityInfo.setFarmId(farm.getFarmId());
            activityInfo.setActivityId(txTime + "_" + this.makeRandomValue() +"_"+"ACTIVITY");
            activityRepository.save(activityMapper.toEntity(activityInfo));
        });

        return this.makeToken(response, userInfo);
    }

    private TokenResponse makeToken(HttpServletResponse response, USER_INFO userInfo){
        Claims claims = Jwts.claims().setSubject(userInfo.getEmail());
        claims.put(NAME, userInfo.getName());
        claims.put(ROLE, userInfo.getRole());

        String accessToken = jwtTokenProvider.createAccessToken(claims);
        String refreshToken = jwtTokenProvider.createRefreshToken(claims);

        USER_TOKEN userToken = USER_TOKEN.builder()
                .id(userInfo.getEmail())
                .token(accessToken)
                .build();

        userTokenRepository.save(userToken);

        Cookie cookie = new Cookie(REFRESH_TOKEN_STR, refreshToken);
        cookie.setMaxAge((int) ONE_MONTH_IN_MILLISECONDS);
        response.addCookie(cookie);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    private String makeRandomValue(){
        int length = (int) (Math.floor(Math.random() * 3) + 2);
        int leftLimit = 48;
        int rightLimit = 122;

        Random random = new Random();
         return random.ints(leftLimit, rightLimit + 1)
                .filter(j -> (j <= 57 || j >= 65) && (j <= 90 || j >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     * deprecated
     */
    public boolean checkPassword(String email, String password) {
        USER_INFO userEntity = userRepository.findUserByEmail(email);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, userEntity.getPassword());
    }

}
