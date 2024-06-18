package com.farmigo.server.domain.alarm.model.vo;

import com.farmigo.server.domain.alarm.model.enumeration.AlarmType;
import com.farmigo.server.global.annotation.EnumValue;
import com.farmigo.server.global.constant.MongoField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAlarmInfo {
    @Id
    @Field(MongoField._ID)
    public long _id;

    // 알람 타입 유형 (예약, 후기, 정산, 공지 등)
    @Field(MongoField.ALARM_TYPE)
    @EnumValue(enumClass = AlarmType.class, ignoreCase = true)
    AlarmType alarmType;

    // 알림 제목
    public String title;

    // 알림 내용
    public String content;

    // Redirect url
    @Field(MongoField.REDIRECT_URL)
    public String redirectUrl;

    // 생성 시간
    @Field(MongoField.CREATE_TIME)
    private long createTime;

    // 전송 시간
    @Field(MongoField.SEND_TIME)
    private long sendTime;

    @Field(MongoField.IS_READ)
    private Boolean isRead;

    @Field(MongoField.IS_DELETED)
    private Boolean isDeleted;

    public void setDefaultStatus(){
        this.setIsDeleted(false);
        this.setIsRead(false);
    }
}
