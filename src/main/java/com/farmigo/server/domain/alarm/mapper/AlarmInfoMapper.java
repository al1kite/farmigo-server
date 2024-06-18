package com.farmigo.server.domain.alarm.mapper;

import com.farmigo.server.domain.alarm.model.request.AlarmRequest;
import com.farmigo.server.domain.alarm.model.vo.UserAlarmInfo;
import com.farmigo.server.global.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlarmInfoMapper extends EntityMapper<AlarmRequest, UserAlarmInfo> {
    AlarmInfoMapper INSTANCE = Mappers.getMapper(AlarmInfoMapper.class);
}
