package com.lostark.root.auction.controller;


import com.lostark.root.auction.db.dto.res.ChartBookInfoRes;
import com.lostark.root.auction.db.dto.res.ChartInfoRes;
import com.lostark.root.auction.service.ChartBookService;
import com.lostark.root.common.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chart/book")
@RequiredArgsConstructor
@Slf4j
public class ChartBookController {

    private final ChartBookService chartBookService;

    @GetMapping("/info")
    public Response<List<ChartBookInfoRes>> getInfo(HttpServletRequest request) {
        log.info("GetBookInfo");
        return Response.of(HttpStatus.OK, "ChartInfo get 성공", chartBookService.getChartBookInfo(request.getHeader("apiKey")));
    }
}
