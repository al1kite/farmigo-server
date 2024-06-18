package com.farmigo.server.global.model.mongo.master;

import com.farmigo.server.global.constant.MongoField;
import com.farmigo.server.global.model.vo.AccountInfo;
import com.farmigo.server.global.model.vo.LocationInfo;
import com.farmigo.server.global.model.vo.SnsInfo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @Description : 농장 정보
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "MST_FARM")
public class MST_FARM {
    @Id
    @Field(MongoField._ID)
    private String farmId;

    @Field(MongoField.FARMER_ID)
    private String farmerId;

    // 운영 중인 SNS
    private List<SnsInfo> sns;

    // 농가 이름
    private String name;

    // 농가 설명
    private String description;

    // 농가 주소
    private LocationInfo address;

    // 은행 (국민, 신한 등..)
    private AccountInfo accountInfo;

}
