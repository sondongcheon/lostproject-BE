package com.lostark.root.auction.controller;

import com.lostark.root.auction.db.dto.res.ChartInfoRes;
import com.lostark.root.auction.service.ChartJewelService;
import com.lostark.root.common.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chart/jewel")
@RequiredArgsConstructor
@Slf4j
public class ChartJewelController {

    private final ChartJewelService chartJewelService;

    @GetMapping("/info")
    public Response<ChartInfoRes> getInfo(@RequestParam ("name") String name,
                                          @RequestParam ("level") int level,
                                          @RequestParam ("time") int time,
                                          @RequestParam ("point") int point,
                                          HttpServletRequest request) {
        if(level == 10 && name.equals("geop")) log.info("GetJewelInfo");
        return Response.of(HttpStatus.OK, "ChartJewelInfo get 성공", chartJewelService.getChartInfo(name, level, time, point));
    }


}
