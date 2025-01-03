package com.lostark.root.common.controller;

import com.lostark.root.common.Response;
import com.lostark.root.common.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
@Slf4j
public class LogController {

    private final LogService logService;

    @GetMapping("/{id}")
    public Response<long[]> getLog(@PathVariable ("id") long id) {
        return Response.of(HttpStatus.OK, "Search 횟수 조회", logService.getLog(id));
    }
}
