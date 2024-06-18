package com.farmigo.server.global.model.mongo.user;

import com.farmigo.server.domain.auth.model.enumeration.Companion;
import com.farmigo.server.domain.auth.model.enumeration.LeisureInterest;
import com.farmigo.server.domain.auth.model.enumeration.LeisureStyle;
import com.farmigo.server.global.constant.MongoField;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "USER_ACTIVITY_STYLE")
public class USER_ACTIVITY_STYLE {
    @Id
    @Field(MongoField._ID)
    private String userId;

    // 체험 및 엑티비티(A)..
    @Field(MongoField.LEISURE_STYLE)
    private LeisureStyle leisureStyle;

    // 혼자(A), 친구와(B), 연인과(C)..
    private Companion companion;

    // 촌캉스(A), 작물수확체험(B)..
    @Field(MongoField.LEISURE_INTEREST)
    private List<LeisureInterest> leisureInterest;

    // 여가 최대 기간
    @Field(MongoField.LEISURE_MAX_PERIOD)
    private Long leisureMaxPeriod;

    // 여가 최소 기간
    @Field(MongoField.LEISURE_MIN_PERIOD)
    private Long leisureMinPeriod;

    // 여가 최대 예산
    @Field(MongoField.LEISURE_MAX_BUDGET)
    private Long leisureMaxBudget;

    // 여가 최소 예산
    @Field(MongoField.LEISURE_MIN_BUDGET)
    private Long leisureMinBudget;
}
