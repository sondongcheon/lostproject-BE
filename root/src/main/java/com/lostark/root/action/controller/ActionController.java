package com.lostark.root.action.controller;

import com.lostark.root.action.db.dto.req.SearchOptionReq;
import com.lostark.root.action.db.dto.res.APIres.ApiAuctionResponse;
import com.lostark.root.action.service.ActionService;
import com.lostark.root.common.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/action")
@RequiredArgsConstructor
@Slf4j
public class ActionController {

    private final ActionService actionService;

    @PostMapping("/test")
    public Response<ApiAuctionResponse> test(@RequestBody SearchOptionReq searchOptionReq) {
        System.out.println(searchOptionReq.toString());

        return Response.of(HttpStatus.OK, "gd", actionService.getActionResult(searchOptionReq));
    }
}
