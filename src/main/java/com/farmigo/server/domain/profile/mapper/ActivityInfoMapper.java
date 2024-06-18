package com.farmigo.server.domain.profile.mapper;

import com.farmigo.server.domain.farm.model.vo.ActivityInfo;
import com.farmigo.server.global.mapper.EntityMapper;
import com.farmigo.server.global.model.mongo.master.MST_ACTIVITY;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActivityInfoMapper extends EntityMapper<ActivityInfo, MST_ACTIVITY> {
    ActivityInfoMapper INSTANCE = Mappers.getMapper(ActivityInfoMapper.class);

}
