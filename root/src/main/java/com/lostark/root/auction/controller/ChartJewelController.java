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

    /* 차트 정보 요청 API
     * 검색 타입 -> 보석(경매장)
     * 자체 DB에서 조회하여 가져옴
     */
    @GetMapping("/info")
    public Response<ChartInfoRes> getInfo(@RequestParam ("name") String name,   // 보석 이름( 겁화, 작열 )
                                          @RequestParam ("level") int level,    // 보석 레벨
                                          @RequestParam ("time") int time,      // SQL 조건 분기 , 날자 주기 몇으로 조회할지
                                          @RequestParam ("point") int point,    // SQL 조건 분기, Limit (몇개)
                                          HttpServletRequest request)           // 유저 API KEY 확인
    {
        // 반복 요청 방식 중 1회 로그 출력
        if(level == 10 && name.equals("geop")) log.info("GetJewelInfo");
        // 검색 결과 DTO return
        return Response.of(HttpStatus.OK, "ChartJewelInfo get 성공", chartJewelService.getChartInfo(name, level, time, point));
    }


}
