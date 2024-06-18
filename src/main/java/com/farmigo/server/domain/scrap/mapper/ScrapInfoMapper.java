package com.farmigo.server.domain.scrap.mapper;

import com.farmigo.server.domain.scrap.model.vo.ScrapInfo;
import com.farmigo.server.global.mapper.EntityMapper;
import com.farmigo.server.global.model.mongo.user.USER_SCRAP;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScrapInfoMapper extends EntityMapper<ScrapInfo, USER_SCRAP> {
    ScrapInfoMapper INSTANCE = Mappers.getMapper(ScrapInfoMapper.class);
}
