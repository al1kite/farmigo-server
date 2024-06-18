package com.farmigo.server.domain.profile.model.response;

import com.farmigo.server.domain.profile.model.vo.ReserveDetailInfo;
import com.farmigo.server.global.model.response.Response;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReserveDetailResponse extends Response {
   List<ReserveDetailInfo> reserveDetailInfos;
}
