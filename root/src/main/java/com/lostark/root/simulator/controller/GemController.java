package com.lostark.root.simulator.controller;

import com.lostark.root.common.Response;
import com.lostark.root.simulator.db.dto.req.GemProcessReq;
import com.lostark.root.simulator.service.GemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simulator/gem")
@RequiredArgsConstructor
@Slf4j
public class GemController {

    /*
        @PostMapping("/test5")
    public Response<SearchFinalRes> test(@RequestBody List<SelectOptionReq> selectOptionReqList, @RequestParam("type") int type, HttpServletRequest request) {
        log.info("Start Search");
        return Response.of(HttpStatus.OK, "gd", auctionService.getAuctionResult(selectOptionReqList, type, request.getHeader("apiKey")));
    }

    //다음 페이지 요청
    @PostMapping("/page")
    public Response<List<SearchResultRes>> auctionNextPage(@RequestBody SelectOptionReq selectOptionReq, @RequestParam("type") int type, HttpServletRequest request) {
        log.info("Get Next Page");
        return Response.of(HttpStatus.OK, "nextPage", auctionService.getAuctionNextPage(selectOptionReq, type, request.getHeader("apiKey")));
    }

    @GetMapping("/equipment/{name}")
    public Response<EquipmentRes[]> getEquipment(@PathVariable (value = "name") String name, HttpServletRequest request) {
        log.info("Search Character");
        return Response.of(HttpStatus.OK, "gd", auctionService.getEquipment(request.getHeader("apiKey"), name));
    }
     */

    private final GemService gemService;

    @PostMapping("/{type}")
    public Response<?> getGemMain(@PathVariable(value = "type") int type, @RequestBody GemProcessReq gemProcessReq) {
        log.info("Gem Simulator");
        return Response.of(HttpStatus.OK, "gd", gemService.getBasicInfo(gemProcessReq, type));
    }

    @PostMapping("/process/{type}")
    public Response<?> getProcessResult(@PathVariable(value = "type") int type, @RequestBody GemProcessReq gemProcessReq) {
        log.info("Gem Process");
        return Response.of(HttpStatus.OK, "gd", gemService.processResult(gemProcessReq));
    }
}
