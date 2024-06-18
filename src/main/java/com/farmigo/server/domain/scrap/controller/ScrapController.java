package com.farmigo.server.domain.scrap.controller;

import com.farmigo.server.domain.scrap.model.request.ScrapListRequest;
import com.farmigo.server.domain.scrap.model.response.ScrapResponse;
import com.farmigo.server.domain.scrap.service.ScrapService;
import com.farmigo.server.global.model.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/scrap")
@Tag(name = "Scrap", description = "스크랩 관련 컨트롤러")
public class ScrapController {

    private final ScrapService scrapService;

    /**
     * 스크랩 Controller
     */
    @Operation(summary = "스크랩 하기", description = """
            activityId 에 해당 하는 체험을 스크랩 한다.
            """)
    @PostMapping("")
    public Response getProfile(@RequestParam("activityId") String activityId) {
        return scrapService.scrap(activityId);
    }

    /**
     * 스크랩 List 조회 Controller
     */
    @Operation(summary = "스크랩 List 조회", description = """
            스크랩 한 체험의 List 를 조회 한다.
            """)
    @PostMapping("/list")
    public ScrapResponse getProfile(@RequestBody ScrapListRequest request) {
        return scrapService.getScrapList(request);
    }

    /**
     * 스크랩 삭제 Controller
     */
    @Operation(summary = "스크랩 삭제", description = """
             activityId 에 해당 하는 스크랩을 삭제 한다.
            """)
    @PostMapping("/delete")
    public Response cancelScrap(@RequestParam("activityId") String activityId) {
        return scrapService.cancelScrap(activityId);
    }
}
