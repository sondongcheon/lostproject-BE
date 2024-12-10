package com.lostark.root.board.controller;

import com.lostark.root.board.service.NoticeService;
import com.lostark.root.common.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boardNotice")
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public Response<?> test() {
        return Response.of(HttpStatus.OK, "gd", noticeService.test());
    }

}
