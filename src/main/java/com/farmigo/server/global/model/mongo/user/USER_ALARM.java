package com.farmigo.server.global.model.mongo.user;

import com.farmigo.server.domain.alarm.model.vo.UserAlarmInfo;
import com.farmigo.server.global.constant.MongoField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "USER_ALARM")
public class USER_ALARM {
    @Id
    @Field(MongoField._ID)
    String _id;
    @Field(MongoField.USER_ID)
    String userId;
    @Field(MongoField.ALARM_INFOS)
    List<UserAlarmInfo> alarmInfos;
    @Field(MongoField.CREATE_TIME)
    private LocalDate createTime;

}
