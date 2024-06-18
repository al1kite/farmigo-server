package com.farmigo.server.domain.auth.mapper;

import com.farmigo.server.domain.auth.model.vo.ActivityBriefInfo;
import com.farmigo.server.global.mapper.EntityMapper;
import com.farmigo.server.global.model.mongo.master.MST_ACTIVITY;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MstActivityMapper extends EntityMapper<ActivityBriefInfo, MST_ACTIVITY> {
    MstActivityMapper INSTANCE = Mappers.getMapper(MstActivityMapper.class);
}

