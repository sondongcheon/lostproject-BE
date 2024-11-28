package com.lostark.root.action.controller;

import com.lostark.root.action.db.dto.req.SearchOptionReq;
import com.lostark.root.action.db.dto.req.SelectOptionReq;
import com.lostark.root.action.db.dto.res.APIres.ApiAuctionRes;
import com.lostark.root.action.db.dto.res.SearchResultRes;
import com.lostark.root.action.service.ActionService;
import com.lostark.root.common.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/action")
@RequiredArgsConstructor
@Slf4j
public class ActionController {

    private final ActionService actionService;

    @PostMapping("/test")
    public Response<ApiAuctionRes> test(@RequestBody SearchOptionReq searchOptionReq) {
        System.out.println(searchOptionReq.toString());

        return Response.of(HttpStatus.OK, "gd", actionService.getActionResult(searchOptionReq));
    }

    @PostMapping("/test2")
    public Response<ApiAuctionRes> test2(@RequestBody List<SearchOptionReq> searchOptionReqList) {

        return Response.of(HttpStatus.OK, "gd", actionService.getActionResult2(searchOptionReqList));
    }

    @PostMapping("/test3")
    public Response<ApiAuctionRes> test3(@RequestBody List<SearchOptionReq> searchOptionReqList) {

        return Response.of(HttpStatus.OK, "gd", actionService.getActionResult3(searchOptionReqList));
    }

    @PostMapping("/test4")
    public Response<SearchResultRes[]> test4(@RequestBody List<SelectOptionReq> selectOptionReqList) {

        return Response.of(HttpStatus.OK, "gd", actionService.getActionResult4(selectOptionReqList));
    }

    @PostMapping("/test5")
    public Response<SearchResultRes[]> test5(@RequestBody List<SelectOptionReq> selectOptionReqList) {

        return Response.of(HttpStatus.OK, "gd", actionService.getActionResult5(selectOptionReqList));
    }
}
