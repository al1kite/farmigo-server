package com.farmigo.server.domain.auth.mapper;

import com.farmigo.server.domain.auth.model.vo.FarmDetailInfo;
import com.farmigo.server.global.mapper.EntityMapper;
import com.farmigo.server.global.model.mongo.master.MST_FARM;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FarmInfoMapper extends EntityMapper<FarmDetailInfo, MST_FARM> {
    FarmInfoMapper INSTANCE = Mappers.getMapper(FarmInfoMapper.class);
}