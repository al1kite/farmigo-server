package com.farmigo.server.domain.alarm.model.response;

import com.farmigo.server.domain.alarm.model.vo.UserAlarmInfo;
import com.farmigo.server.global.model.response.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmResponse extends Response {
    public List<UserAlarmInfo> userAlarmInfos;
}
