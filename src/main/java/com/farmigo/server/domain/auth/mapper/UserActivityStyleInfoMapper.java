package com.farmigo.server.domain.auth.mapper;

import com.farmigo.server.domain.auth.model.vo.UserActivityStyleInfo;
import com.farmigo.server.global.mapper.EntityMapper;
import com.farmigo.server.global.model.mongo.user.USER_ACTIVITY_STYLE;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserActivityStyleInfoMapper extends EntityMapper<UserActivityStyleInfo, USER_ACTIVITY_STYLE> {
    UserActivityStyleInfoMapper INSTANCE = Mappers.getMapper(UserActivityStyleInfoMapper.class);
}
