package com.lostark.root.auction.controller;


import com.lostark.root.auction.db.dto.res.ChartItemsInfoRes;
import com.lostark.root.auction.service.ChartItemsService;
import com.lostark.root.common.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chart/items")
@RequiredArgsConstructor
@Slf4j
public class ChartItemsController {

    private final ChartItemsService chartItemsService;

    @GetMapping("/info/{type}")
    public Response<List<ChartItemsInfoRes>> getInfo(HttpServletRequest request, @PathVariable int type,
                                                     @RequestParam("time") int time,
                                                     @RequestParam ("point") int point) {
        if(request.getHeader("apiKey") == null || request.getHeader("apiKey").length() < 10)  log.info("GetPublicUpgradeInfo");
        else log.info("GetUpgradeInfo");
        log.info("Get Chart Type {}", type);
        return Response.of(HttpStatus.OK, "ChartInfo get 성공", chartItemsService.getChartInfo(request.getHeader("apiKey"), type, time, point));
    }

}
