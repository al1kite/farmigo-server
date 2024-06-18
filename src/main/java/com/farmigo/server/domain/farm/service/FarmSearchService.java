package com.farmigo.server.domain.farm.service;

import com.farmigo.server.domain.farm.model.request.FarmActivityListRequest;
import com.farmigo.server.domain.farm.model.vo.*;
import com.farmigo.server.domain.farm.repository.MstActivityMongoRepository;
import com.farmigo.server.domain.farm.repository.MstFarmMongoRepository;
import com.farmigo.server.global.model.mongo.master.MST_ACTIVITY;
import com.farmigo.server.global.model.mongo.master.MST_FARM;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 농장 검색 관련 서비스
 */
@Slf4j
@Service
@AllArgsConstructor
public class FarmSearchService {

    private final MstFarmMongoRepository mstFarmMongoRepository;
    private final MstActivityMongoRepository mstActivityMongoRepository;

    public List<ActivityInfoSummary> getActivityRecommendList(long txTime, int limit) {
        List<MST_ACTIVITY> activities = mstActivityMongoRepository.findByActivityByEtAndLimit(txTime, limit);

        return getActivitySummary(activities);
    }

    public List<ActivityInfoSummary> getActivitySearchList(Search search, String offset, int limit) {
        List<MST_ACTIVITY> activities = mstActivityMongoRepository.searchByOffset(search, offset, limit);

        return getActivitySummary(activities);
    }

    public ActivityInfo getActivityDetail(String activityId) {
        MST_ACTIVITY activity = mstActivityMongoRepository.findByActivityId(activityId);
        return new ActivityInfo(activity);
    }

    public FarmInfo getFarmDetail(String farmId) {
        MST_FARM farm = mstFarmMongoRepository.findByFarmId(farmId);
        return new FarmInfo(farm);
    }

    public FarmerInfo getFarmerDetail(String farmerId) {
        // TODO : 농장주 정보 추가
//        USER_INFO farmer = mstFarmerMongoRepository.findByFarmerId(farmerId);
        return new FarmerInfo();
    }

    /////////// private method ///////////
    private List<ActivityInfoSummary> getActivitySummary(List<MST_ACTIVITY> activities) {
        List<String> farmIds = activities.stream().map(MST_ACTIVITY::getFarmId).toList();
        List<MST_FARM> farms = mstFarmMongoRepository.findByFarmIdList(farmIds);

        List<ActivityInfoSummary> res = new ArrayList<>();
        for (MST_ACTIVITY activity : activities) {
            FarmInfoSummary farm = farms.stream().filter(f -> f.getFarmId().equals(activity.getFarmId())).findFirst().map(FarmInfoSummary::new).orElse(null);

            res.add(ActivityInfoSummary.builder()
                    .activityId(activity.getActivityId())
                    .activityName(activity.getName())
                    .activityThumbnail(activity.getThumbnail())
                    .priceInfo(activity.getPriceInfo())
                    .farmInfo(farm)
                    .build());
        }
        return res;
    }
}
