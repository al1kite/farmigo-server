package com.farmigo.server.domain.profile.service;

import com.farmigo.server.domain.auth.mapper.UserActivityStyleInfoMapper;
import com.farmigo.server.domain.auth.mapper.UserInfoMapper;
import com.farmigo.server.domain.auth.repository.UserRepository;
import com.farmigo.server.domain.profile.model.response.YouthOnboardingResponse;
import com.farmigo.server.domain.profile.repository.ReserveRepository;
import com.farmigo.server.domain.profile.repository.UserActivityStyleMongoRepository;
import com.farmigo.server.domain.reserve.delegate.ReserveDelegate;
import com.farmigo.server.global.constant.ErrorCode;
import com.farmigo.server.global.exception.ServerException;
import com.farmigo.server.global.model.mongo.user.USER_ACTIVITY_RESERVE;
import com.farmigo.server.global.model.mongo.user.USER_ACTIVITY_STYLE;
import com.farmigo.server.global.model.mongo.user.USER_INFO;
import com.farmigo.server.domain.auth.security.LoginManager;
import com.farmigo.server.domain.profile.model.response.ReserveDetailResponse;
import com.farmigo.server.domain.profile.model.response.YouthProfileResponse;
import com.farmigo.server.domain.profile.model.response.ProfileResponse;
import com.farmigo.server.domain.profile.model.request.ProfileRequest;
import com.farmigo.server.domain.profile.model.request.ReserveSearchRequest;
import com.farmigo.server.domain.profile.model.request.YouthOnboardingRequest;
import com.farmigo.server.global.model.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Slf4j
@Service
@RequiredArgsConstructor
public class YouthProfileService {

    private final ReserveRepository reserveRepository;
    private final ReserveDelegate reserveDelegate;
    private final UserActivityStyleMongoRepository activityStyleRepository;
    private final UserRepository userRepository;
    private final UserInfoMapper userInfoMapper;
    private final UserActivityStyleInfoMapper userActivityStyleInfoMapper;

    public ReserveDetailResponse getReserve(ReserveSearchRequest request) {
        String userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserId();

        List<USER_ACTIVITY_RESERVE> reserveTypeList =
                reserveRepository.findReserveByType(request, userId);

        return reserveDelegate.getReserve(reserveTypeList, request.getReserveType());
    }

    public ProfileResponse getProfile(){
        USER_INFO youth = LoginManager.getUserDetails();

        if(youth == null)
            throw new ServerException(ErrorCode.RESOURCE_NOT_FOUND);

        return ProfileResponse.builder()
                .profileUrl(youth.getProfileUrl())
                .description(youth.getPhoneNumber())
                .name(youth.getName())
                .build();
    }

    public YouthProfileResponse getProfileDetail(){
        USER_INFO userInfo = LoginManager.getUserDetails();

        if(userInfo == null)
            throw new ServerException(ErrorCode.RESOURCE_NOT_FOUND);

        USER_ACTIVITY_STYLE userActivityStyle = activityStyleRepository.findById(userInfo.getUserId());

        return YouthProfileResponse.builder()
                .userInfo(userInfoMapper.toDto(userInfo))
                .userActivityStyle(userActivityStyleInfoMapper.toDto(userActivityStyle))
                .build();
    }

    public Response updateProfile(ProfileRequest profileRequest){
        USER_INFO userInfo = LoginManager.getUserDetails();

        if(userInfo == null)
            throw new ServerException(ErrorCode.RESOURCE_NOT_FOUND);

        boolean success = userRepository.updateProfile(userInfo.getUserId(), profileRequest);
        if(success) return Response.success();
        else return Response.error(ErrorCode.UPDATE_FAIL);
    }

    public YouthOnboardingResponse updateOnboarding(YouthOnboardingRequest onboardingRequest){
        USER_ACTIVITY_STYLE userActivityStyle = activityStyleRepository.save(userActivityStyleInfoMapper.toEntity(onboardingRequest.getUserActivityStyle()));

        return YouthOnboardingResponse.builder()
                .userActivityStyle(userActivityStyleInfoMapper.toDto(userActivityStyle))
                .build();
    }


}
