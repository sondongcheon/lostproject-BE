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

    /* 차트 정보 요청 API
     * 검색 타입 -> 주요아이템(거래소) type 통해 어떤 아이템인지 분류
     * 자체 DB에서 조회하여 가져옴
     */
    @GetMapping("/info/{type}")
    public Response<List<ChartItemsInfoRes>> getInfo(HttpServletRequest request,        // 유저 API
                                                     @PathVariable int type,            // 아이템 분류, 유각인지 젬인지 등등
                                                     @RequestParam("time") int time,    // SQL 조건 분기 , 날자 주기 몇으로 조회할지
                                                     @RequestParam ("point") int point) // SQL 조건 분기, Limit (몇개)
    {
        log.info("Get Chart Type {}", type);
        // 검색 결과 DTO return
        return Response.of(HttpStatus.OK, "ChartInfo get 성공", chartItemsService.getChartInfo(request.getHeader("apiKey"), type, time, point));
    }

}
