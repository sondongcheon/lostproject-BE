package com.lostark.root.board.controller;

import com.lostark.root.board.db.dto.req.MiniWriteReq;
import com.lostark.root.board.db.dto.res.MiniMainRes;
import com.lostark.root.board.service.MiniService;
import com.lostark.root.common.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board/mini")
@RequiredArgsConstructor
@Slf4j
public class MiniController {

    private final MiniService miniService;

    @GetMapping
    public Response<List<MiniMainRes>> getMini() {
        return Response.of(HttpStatus.OK, "gd", miniService.getMiniMain());
    }

    @PostMapping
    public Response<?> setMini(@RequestBody MiniWriteReq miniWriteReq) {
        miniService.setMiniContent(miniWriteReq);
        return Response.of(HttpStatus.OK, "Mini 작성 완료", null);
    }
}
