package com.farmigo.server.domain.auth.model.vo;

import com.farmigo.server.global.model.vo.AccountInfo;
import com.farmigo.server.global.model.vo.LocationInfo;
import com.farmigo.server.global.model.vo.SnsInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmDetailInfo {
    private String farmId;
    private String farmerId;

    // 운영 중인 SNS
    private List<SnsInfo> sns;
    private String name;
    private String description;
    private LocationInfo address;
    private AccountInfo accountInfo;
}
