package com.lostark.root.auction.controller;

import com.lostark.root.auction.db.dto.req.SelectOptionReq;
import com.lostark.root.auction.db.dto.res.SearchFinalRes;
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

    private final AuctionService auctionService;

    @PostMapping("/test5")
    public Response<SearchFinalRes> test(@RequestBody List<SelectOptionReq> selectOptionReqList, @RequestParam("type") int type, HttpServletRequest request) {
        return Response.of(HttpStatus.OK, "gd", auctionService.getAuctionResult(selectOptionReqList, type, request.getHeader("apiKey")));
    }
}
