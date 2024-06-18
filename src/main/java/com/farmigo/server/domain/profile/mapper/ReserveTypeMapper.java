package com.farmigo.server.domain.profile.mapper;

import com.farmigo.server.domain.profile.model.vo.ReserveDetailInfo;
import com.farmigo.server.global.mapper.EntityMapper;
import com.farmigo.server.global.model.mongo.user.USER_ACTIVITY_RESERVE;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReserveTypeMapper extends EntityMapper<ReserveDetailInfo, USER_ACTIVITY_RESERVE> {
    ReserveTypeMapper INSTANCE = Mappers.getMapper(ReserveTypeMapper.class);
}