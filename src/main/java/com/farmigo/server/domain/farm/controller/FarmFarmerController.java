package com.farmigo.server.domain.farm.controller;

import com.farmigo.server.domain.farm.delegate.FarmFarmerDelegate;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description : 농장주 농장 관련 컨트롤러
 */
@Tag(name = "FarmFarmer", description = "농장주 농장 관련 컨트롤러")
@RestController
@RequiredArgsConstructor
public class FarmFarmerController {

    private final FarmFarmerDelegate farmFarmerDelegate;


}
