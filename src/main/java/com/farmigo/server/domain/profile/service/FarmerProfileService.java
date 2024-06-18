package com.farmigo.server.domain.profile.service;

import com.farmigo.server.domain.auth.mapper.FarmInfoMapper;
import com.farmigo.server.domain.auth.mapper.MstActivityMapper;
import com.farmigo.server.domain.auth.mapper.UserInfoMapper;
import com.farmigo.server.domain.auth.repository.UserRepository;
import com.farmigo.server.domain.farm.repository.MstActivityMongoRepository;
import com.farmigo.server.domain.farm.repository.MstFarmMongoRepository;
import com.farmigo.server.domain.profile.mapper.ActivityInfoMapper;
import com.farmigo.server.domain.profile.model.enumeration.ReserveType;
import com.farmigo.server.domain.profile.model.request.FarmInfoRequest;
import com.farmigo.server.domain.profile.model.response.PriceResponse;
import com.farmigo.server.domain.profile.model.response.ReserveDetailResponse;
import com.farmigo.server.domain.profile.repository.ReserveRepository;
import com.farmigo.server.domain.reserve.delegate.ReserveDelegate;
import com.farmigo.server.global.model.mongo.master.MST_ACTIVITY;
import com.farmigo.server.global.model.mongo.master.MST_FARM;
import com.farmigo.server.global.model.mongo.user.USER_ACTIVITY_RESERVE;
import com.farmigo.server.global.model.mongo.user.USER_INFO;
import com.farmigo.server.domain.auth.security.LoginManager;
import com.farmigo.server.domain.profile.model.response.FarmerProfileResponse;
import com.farmigo.server.domain.profile.model.response.ProfileResponse;
import com.farmigo.server.domain.profile.model.request.FarmerProfileRequest;
import com.farmigo.server.domain.profile.model.request.ReserveSearchRequest;
import com.farmigo.server.global.model.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class FarmerProfileService {

    private final ReserveRepository reserveRepository;
    private final MstFarmMongoRepository farmRepository;
    private final MstActivityMongoRepository activityRepository;
    private final UserRepository userRepository;
    private final ReserveDelegate reserveDelegate;
    private final FarmInfoMapper farmInfoMapper;
    private final UserInfoMapper userInfoMapper;
    private final ActivityInfoMapper activityInfoMapper;
    private final MstActivityMapper activityMapper;

    public Map<ReserveType, Long> getReserve() {
        String userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserId();
        String farmId = farmRepository.findByFarmerId(userId).getFarmId();

        return reserveRepository.findReserveCountByType(farmId);
    }

    public ReserveDetailResponse getReserveDetail(ReserveSearchRequest request) {
        String userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserId();
        String farmId = farmRepository.findByFarmerId(userId).getFarmId();

        List<USER_ACTIVITY_RESERVE> reserveTypeList =
                reserveRepository.findReserveByTypeAndActivity(request, farmId, request.getOffset(), request.getLimit());

        return reserveDelegate.getReserve(reserveTypeList, request.getReserveType());
    }

    public ProfileResponse getProfile(){
        USER_INFO userInfo = LoginManager.getUserDetails();

        assert userInfo != null;
        String description = farmRepository.findByFarmerId(userInfo.getUserId()).getName();

        return ProfileResponse.builder()
                .profileUrl(userInfo.getProfileUrl())
                .description(description) // 농가 이름 운영 중
                .name(userInfo.getName())
                .build();
    }

    public FarmerProfileResponse getProfileDetail(){
        USER_INFO userInfo =LoginManager.getUserDetails();

        assert userInfo != null;
        MST_FARM farm = farmRepository.findByFarmerId(userInfo.getUserId());
        List<MST_ACTIVITY> activity = activityRepository.findByFarmId(farm.getFarmId());

        return FarmerProfileResponse.builder()
                .userInfo(userInfoMapper.toDto(userInfo))
                .farmInfo(farmInfoMapper.toDto(farm))
                .activityInfo(activityMapper.toDto(activity))
                .build();
    }

    public FarmerProfileResponse updateProfile(FarmerProfileRequest farmerProfileRequest){
        farmerProfileRequest.getUserInfo().setUserId(Objects.requireNonNull(LoginManager.getUserDetails()).getUserId());
        USER_INFO userInfo = userRepository.save(userInfoMapper.toEntity(farmerProfileRequest.getUserInfo()));

        if(farmerProfileRequest.getFarmInfo() != null) farmerProfileRequest.getFarmInfo().setFarmerId(userInfo.getUserId());
        MST_FARM farm = farmRepository.save(farmInfoMapper.toEntity(farmerProfileRequest.getFarmInfo()));

        List<MST_ACTIVITY> activityList = new ArrayList<>();
        farmerProfileRequest.getActivityInfo().forEach(activityInfo -> activityList.add(activityRepository.save(activityMapper.toEntity(activityInfo))));

        return FarmerProfileResponse.builder()
                .userInfo(userInfoMapper.toDto(userInfo))
                .farmInfo(farmInfoMapper.toDto(farm))
                .activityInfo(activityMapper.toDto(activityList))
                .build();
    }

    public Response saveFarmInfo(FarmInfoRequest farmInfoRequest){
        farmRepository.save(farmInfoMapper.toEntity(farmInfoRequest.getFarm()));
        activityRepository.save(activityInfoMapper.toEntity(farmInfoRequest.getActivityInfo()));
        return Response.success();
    }

    public PriceResponse getAveragePrice(){
        return PriceResponse.builder()
                .priceInfo(activityRepository.getMaxAndMinPrice())
                .build();
    }
}
