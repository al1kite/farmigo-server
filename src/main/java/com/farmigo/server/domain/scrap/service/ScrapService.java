package com.farmigo.server.domain.scrap.service;

import com.farmigo.server.domain.auth.security.LoginManager;
import com.farmigo.server.domain.farm.repository.MstActivityMongoRepository;
import com.farmigo.server.domain.farm.repository.MstFarmMongoRepository;
import com.farmigo.server.domain.scrap.mapper.ScrapInfoMapper;
import com.farmigo.server.domain.scrap.model.request.ScrapListRequest;
import com.farmigo.server.domain.scrap.model.response.ScrapResponse;
import com.farmigo.server.domain.scrap.model.vo.ScrapInfo;
import com.farmigo.server.domain.scrap.repository.UserScrapMongoRepository;
import com.farmigo.server.global.model.mongo.master.MST_ACTIVITY;
import com.farmigo.server.global.model.mongo.master.MST_FARM;
import com.farmigo.server.global.model.mongo.user.USER_SCRAP;
import com.farmigo.server.global.model.response.Response;
import com.farmigo.server.global.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.farmigo.server.global.constant.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapService {

    private final UserScrapMongoRepository userScrapMongoRepository;
    private final MstActivityMongoRepository activityMongoRepository;
    private final MstFarmMongoRepository farmMongoRepository;
    private final ScrapInfoMapper scrapInfoMapper;

    public Response scrap(String activityId){
        String userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserId();
        long txTime = DateUtil.dateToEpochMilli(LocalDateTime.now());

        USER_SCRAP userScrap = USER_SCRAP.builder()
                .userId(userId)
                .activityId(activityId)
                .createTime(txTime)
                .build();

        userScrapMongoRepository.save(userScrap);
        return Response.success();
    }

    public Response cancelScrap(String activityId){
        String userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserId();
        boolean success = userScrapMongoRepository.delete(activityId, userId);

        if(success) return Response.success();
        else return Response.error(DELETE_FAIL);
    }

    public ScrapResponse getScrapList(ScrapListRequest request){
        String userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserId();
        List<USER_SCRAP> userScraps = userScrapMongoRepository.searchByOffset(userId, request.getOffset(), request.getLimit());

        List<String> activityIds = new ArrayList<>();
        for(USER_SCRAP userScrap : userScraps)
           activityIds.add(userScrap.getActivityId());

        List<MST_ACTIVITY> activityList = activityMongoRepository.findByActivityIdList(activityIds);

        List<String> farmIds = new ArrayList<>();
        for(MST_ACTIVITY activity : activityList)
            farmIds.add(activity.getFarmId());

        List<MST_FARM> farmList = farmMongoRepository.findByFarmIdList(farmIds);

        List<ScrapInfo> scrapInfos = scrapInfoMapper.toDto(userScraps);
        scrapInfos.forEach(scrapInfo -> activityList.stream().filter(
                mstActivity -> mstActivity.getActivityId().equals(scrapInfo.getActivityId())
        ).forEach(mstActivity -> {
            scrapInfo.setActivityTitle(mstActivity.getName());
            farmList.stream().filter(mstFarm -> mstFarm.getFarmId().equals(mstActivity.getFarmId()))
                    .forEach(mstFarm -> {
                        scrapInfo.setFarmName(mstFarm.getName());
                        scrapInfo.setLocation(mstFarm.getAddress().getFullAddress());
                    });
        }));

        return ScrapResponse.builder()
                .scrapInfos(scrapInfos)
                .build();
    }

}
