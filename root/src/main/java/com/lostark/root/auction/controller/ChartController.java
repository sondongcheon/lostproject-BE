package com.lostark.root.auction.controller;

import com.lostark.root.auction.db.dto.req.CustomChartReq;
import com.lostark.root.auction.db.dto.res.ChartInfoRes;
import com.lostark.root.auction.service.ChartService;
import com.lostark.root.common.Response;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chart")
@RequiredArgsConstructor
@Slf4j
public class ChartController {

    private final ChartService chartService;

    @GetMapping("/info")
    public Response<ChartInfoRes> getInfo(@RequestParam ("tier") int tier,
                                                @RequestParam ("category") String category,
                                                @RequestParam ("grade") String grade,
                                                @RequestParam ("value") String value,
                                                @RequestParam ("value2") String value2) {
        return Response.of(HttpStatus.OK, "ChartInfo get 성공", chartService.getChartInfo(tier, category, grade, value, value2));
    }

    @PostMapping("/custom")
    public Response<ChartInfoRes> getCustomInfo(@RequestBody CustomChartReq customChartReq) {
        return Response.of(HttpStatus.OK, "ChartInfo get 성공", chartService.getCustomChartInfo(customChartReq));
    }

    @GetMapping("/uplog")
    public Response<?> upLog(HttpServletRequest request, HttpServletResponse response) {
        chartService.loadChartPage(request.getCookies(), response);
        return Response.of(HttpStatus.OK, "차트 조회수 갱신", null);
    }
}
