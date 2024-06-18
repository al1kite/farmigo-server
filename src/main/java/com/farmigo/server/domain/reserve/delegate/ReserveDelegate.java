package com.farmigo.server.domain.reserve.delegate;

import com.farmigo.server.domain.farm.repository.MstActivityMongoRepository;
import com.farmigo.server.domain.profile.mapper.ReserveTypeMapper;
import com.farmigo.server.domain.profile.model.response.ReserveDetailResponse;
import com.farmigo.server.domain.profile.model.enumeration.ReserveType;
import com.farmigo.server.domain.profile.model.vo.ReserveDetailInfo;
import com.farmigo.server.global.model.mongo.master.MST_ACTIVITY;
import com.farmigo.server.global.model.mongo.user.USER_ACTIVITY_RESERVE;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReserveDelegate {
    private final ReserveTypeMapper reserveTypeMapper;
    private final MstActivityMongoRepository activityMongoRepository;
    public ReserveDetailResponse getReserve(List<USER_ACTIVITY_RESERVE> reserveTypeList, ReserveType type){
        List<String> activityIds = new ArrayList<>();
        for(USER_ACTIVITY_RESERVE userActivityReserve : reserveTypeList)
            activityIds.add(userActivityReserve.getActivityId());
        List<MST_ACTIVITY> activityList = activityMongoRepository.findByActivityIdList(activityIds);
        List<ReserveDetailInfo> reserveDetailInfos = reserveTypeMapper.toDto(reserveTypeList);

        reserveDetailInfos.forEach(reserveDetailInfo -> activityList.stream().filter(mstActivity -> mstActivity.getActivityId().equals(reserveDetailInfo.getActivityId()))
                .forEach(mstActivity -> {
                    reserveDetailInfo.setActivityName(mstActivity.getName());
                    reserveDetailInfo.setActivityThumbnail(mstActivity.getThumbnail());
                }));

        if(type.equals(ReserveType.UC))
            reserveDetailInfos.forEach(reserveResponse -> reserveTypeList.stream().filter(reserve -> Objects.equals(reserve.getReserveId(), reserveResponse.getReserveId()))
                    .forEach(reserve -> reserveResponse.setDDay(LocalDate.now().until(reserve.getStartTime().toLocalDate(), ChronoUnit.DAYS))));

        return ReserveDetailResponse.builder()
                .reserveDetailInfos(reserveDetailInfos)
                .build();
    }
}
