package com.farmigo.server.domain.scrap.model.response;

import com.farmigo.server.domain.scrap.model.vo.ScrapInfo;
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
public class ScrapResponse extends Response {
    List<ScrapInfo> scrapInfos;
}
