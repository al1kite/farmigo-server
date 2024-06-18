package com.farmigo.server.domain.alarm.model.request;

import com.farmigo.server.domain.alarm.model.enumeration.AlarmType;
import com.farmigo.server.global.annotation.EnumValue;
import lombok.Data;

@Data
public class AlarmRequest {
    @EnumValue(enumClass = AlarmType.class, ignoreCase = true)
    AlarmType alarmType;

    public String title;

    public String content;

    public String redirectUrl;

}
