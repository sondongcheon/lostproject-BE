package com.lostark.root.auction.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/auction")
@RequiredArgsConstructor
@Slf4j
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping("/test5")
    public Response<SearchFinalRes> test(@RequestBody List<SelectOptionReq> selectOptionReqList, @RequestParam("type") int type, HttpServletRequest request) {
        return Response.of(HttpStatus.OK, "gd", auctionService.getAuctionResult(selectOptionReqList, type, request.getHeader("apiKey")));
    }

    //다음 페이지 요청
    @PostMapping("/page")
    public Response<List<SearchResultRes>> auctionNextPage(@RequestBody SelectOptionReq selectOptionReq, @RequestParam("type") int type, HttpServletRequest request) {
        return Response.of(HttpStatus.OK, "nextPage", auctionService.getAuctionNextPage(selectOptionReq, type, request.getHeader("apiKey")));
    }

    @GetMapping("/equipment/{name}")
    public Response<EquipmentRes[]> getEquipment(@PathVariable (value = "name") String name, HttpServletRequest request) {
        return Response.of(HttpStatus.OK, "gd", auctionService.getEquipment(request.getHeader("apiKey"), name));
    }
}
