package com.lostark.root.auction.controller;

import com.lostark.root.auction.db.dto.req.SelectOptionReq;
import com.lostark.root.auction.db.dto.res.EquipmentRes;
import com.lostark.root.auction.db.dto.res.SearchFinalRes;
import com.lostark.root.auction.db.dto.res.SearchResultRes;
import com.lostark.root.auction.service.AuctionService;
import com.lostark.root.common.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auction")
@RequiredArgsConstructor
@Slf4j
public class AuctionController {
    /*
     * 옥션 페이지 요청 관련 API
     */

    private final AuctionService auctionService;

    // 물품 검색 요청 API
    @PostMapping("/test5")
    public Response<SearchFinalRes> test(@RequestBody List<SelectOptionReq> selectOptionReqList, //선택 옵션 리스트, 물품 5종류 모두 포함
                                         @RequestParam("type") int type,  //딜러 페이지인지 서포터 페이지인지 알려주는 변수
                                         HttpServletRequest request) //유저 개인 API KEY 전달을 Header로 함
    {
        log.info("Start Search");
        // 걺색결과 DTO를 return
        return Response.of(HttpStatus.OK, "OK", auctionService.getAuctionResult(selectOptionReqList, type, request.getHeader("apiKey")));
    }

    /* 물품 검색 결과 다음 페이지 요청 API (페이지네이션)
     * 선택 옵션을 동일하게 전달받음
     */
    @PostMapping("/page")
    public Response<List<SearchResultRes>> auctionNextPage(@RequestBody SelectOptionReq selectOptionReq, // 1품목 선택 옵션
                                                           @RequestParam("type") int type, //딜러 페이지인지 서포터 페이지인지 알려주는 변수
                                                           HttpServletRequest request) //유저 개인 API KEY 전달을 Header로 함
    {
        log.info("Get Next Page");
        // 걺색결과 DTO를 return
        return Response.of(HttpStatus.OK, "nextPage", auctionService.getAuctionNextPage(selectOptionReq, type, request.getHeader("apiKey")));
    }

    // 착용 아이템 검색 API
    @GetMapping("/equipment/{name}")
    public Response<EquipmentRes[]> getEquipment(@PathVariable (value = "name") String name, // 닉네임으로 검색
                                                 HttpServletRequest request) //유저 개인 API KEY 전달을 Header로 함
    {
        log.info("Search Character");
        // 착용 아이템 결과 DTO를 return
        return Response.of(HttpStatus.OK, "OK", auctionService.getEquipment(request.getHeader("apiKey"), name));
    }
}
