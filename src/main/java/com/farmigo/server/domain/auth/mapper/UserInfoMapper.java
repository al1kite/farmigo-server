package com.farmigo.server.domain.auth.mapper;

import com.farmigo.server.domain.auth.model.vo.UserInfo;
import com.farmigo.server.global.mapper.EntityMapper;
import com.farmigo.server.global.model.mongo.user.USER_INFO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserInfoMapper extends EntityMapper<UserInfo, USER_INFO> {
    UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);
}
