package com.lostark.root.auction.controller;

import com.lostark.root.auction.db.dto.res.ChartInfoRes;
import com.lostark.root.auction.service.ChartService;
import com.lostark.root.common.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chart")
@RequiredArgsConstructor
@Slf4j
public class ChartController {

    private final ChartService chartService;

    @GetMapping("/info")
    public Response<List<ChartInfoRes>> getInfo(@RequestParam ("tier") int tier,
                                                @RequestParam ("category") String category,
                                                @RequestParam ("grade") String grade,
                                                @RequestParam ("value") String value,
                                                @RequestParam ("value2") String value2) {
        return Response.of(HttpStatus.OK, "ChartInfo get 성공", chartService.getChartInfo(tier, category, grade, value, value2));
    }
}
