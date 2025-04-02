package com.lostark.root.board.controller;

import com.lostark.root.board.db.dto.req.NoticeWriteReq;
import com.lostark.root.board.db.dto.res.NoticeContentRes;
import com.lostark.root.board.db.dto.res.NoticeMainRes;
import com.lostark.root.board.service.NoticeService;
import com.lostark.root.common.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board/notice")
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public Response<?> test() {
        return Response.of(HttpStatus.OK, "gd", noticeService.test());
    }

    @GetMapping("/main")
    public Response<NoticeMainRes> getNoticeMain(@RequestParam ("page") int page) {
        return Response.of(HttpStatus.OK, "Notice Page Main 조회 완료", noticeService.getNoticeMain(page));
    }

    @GetMapping("/content")
    public Response<NoticeContentRes> getNoticeContent(@RequestParam ("id") long id) {
        return Response.of(HttpStatus.OK, "Notice Content 조회 완료", noticeService.getNoticeContent(id));
    }

    @GetMapping("/last")
    public Response<NoticeContentRes> getNoticeContentLast() {
        return Response.of(HttpStatus.OK, "Notice Last Content 조회 완료", noticeService.getNoticeContentDesc());
    }

    @PostMapping
    public Response<Long> setNotice(@RequestBody NoticeWriteReq noticeWriteReq) {
        return Response.of(HttpStatus.OK, "Notice Content 저장 완료", noticeService.setNoticeContent(noticeWriteReq));
    }

}
