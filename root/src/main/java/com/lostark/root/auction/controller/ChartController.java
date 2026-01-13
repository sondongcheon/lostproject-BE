package com.lostark.root.auction.controller;

import com.lostark.root.auction.db.dto.req.CustomChartReq;
import com.lostark.root.auction.db.dto.res.ChartInfoRes;
import com.lostark.root.auction.service.ChartService;
import com.lostark.root.common.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chart")
@RequiredArgsConstructor
@Slf4j
public class ChartController {

    private final ChartService chartService;

    /* 차트 정보 요청 API
     * 검색 타입 -> 악세사리(경매장)
     * 자체 DB에서 조회하여 가져옴
     * Param -> Body 변환 필요, 너무 많은 Param
     */
    @GetMapping("/info")
    public Response<ChartInfoRes> getInfo(@RequestParam ("tier") int tier,              // 검색 티어 (현재 사용 X, 4 고정 예상)
                                          @RequestParam ("category") String category,   // 종류 (목걸이, 귀걸이, 반지)
                                          @RequestParam ("grade") String grade,         // 등급 (유물, 고대)
                                          @RequestParam ("value") String value,         // 종류 ( ex, 추피 적추피)
                                          @RequestParam ("value2") String value2,
                                          @RequestParam ("type") String type,           // 딜러, 서포터
                                          @RequestParam ("time") int time,              // SQL 조건 분기 , 날자 주기 몇으로 조회할지
                                          @RequestParam ("point") int point)            // SQL 조건 분기, Limit (몇개)
    {
        // 다중 요청 방식으로 1로그 반환을 위한 조건
        if(value.equals("h") && value2.equals("h"))log.info("GetChartInfo");
        // 차트 그리기 위한 정보 DTO return
        return Response.of(HttpStatus.OK, "ChartInfo get 성공", chartService.getChartInfo(tier, category, grade, value, value2, type, time, point));
    }

    /* 악세서리 차트 정보 요청 API
     * 가격 정보를 합산하여 반환하는 API
     * 자체 DB에서 테이블을 합산하여(Union) 반환
     */
    @PostMapping("/custom")
    public Response<ChartInfoRes> getCustomInfo(@RequestBody CustomChartReq customChartReq) // 선택한 품목의 번호들 Body
    {
        log.info("GetCustomChartInfo");
        // 합산 결과 DTO return
        return Response.of(HttpStatus.OK, "ChartInfo get 성공", chartService.getCustomChartInfo(customChartReq));
    }

    /* 차트 방문정보 수집용 API
     * 24시간 유효기간으로 된 쿠키를 활용한 방식
     */
    @GetMapping("/uplog")
    public Response<?> upLog(HttpServletRequest request, HttpServletResponse response) {
        chartService.loadChartPage(request.getCookies(), response);
        return Response.of(HttpStatus.OK, "차트 조회수 갱신", null);
    }
}
