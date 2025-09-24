package com.lostark.root.simulator.controller;

import com.lostark.root.common.Response;
import com.lostark.root.common.db.repository.LogCountRepository;
import com.lostark.root.simulator.db.dto.GemStateDto;
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

    @GetMapping("/equipment/{name}")
    public Response<EquipmentRes[]> getEquipment(@PathVariable (value = "name") String name, HttpServletRequest request) {
        log.info("Search Character");
        return Response.of(HttpStatus.OK, "gd", auctionService.getEquipment(request.getHeader("apiKey"), name));
    }
     */

    private final GemService gemService;
    private final LogCountRepository logCountRepository;

    @PostMapping("/{type}")
    public Response<?> getGemMain(@PathVariable(value = "type") int type, @RequestBody GemProcessReq gemProcessReq, @RequestParam (value = "grade") int grade) {
        log.info("Gem Simulator");
        logCountRepository.incrementCountByName("gemSimulator");
        return Response.of(HttpStatus.OK, "gd", gemService.getBasicInfo(gemProcessReq, type, grade));
    }

    @PostMapping("/process/{type}")
    public Response<?> getProcessResult(@PathVariable(value = "type") int type, @RequestBody GemProcessReq gemProcessReq) {
        log.info("Gem Process");
        logCountRepository.incrementCountByName("useGemSimulator");
        return Response.of(HttpStatus.OK, "gd", gemService.processGem(gemProcessReq, type));
    }

    @PostMapping("/reroll")
    public Response<?> reRollChoiceList(@RequestBody GemProcessReq gemProcessReq) {
        log.info("Gem reRoll");
        return Response.of(HttpStatus.OK, "gd", gemService.reRollChoiceList(gemProcessReq));
    }

    @PostMapping("/recheck")
    public Response<?> checkWeight(@RequestBody GemProcessReq gemProcessReq) {
        return Response.of(HttpStatus.OK, "gd", gemService.checkWeight(gemProcessReq));
    }
}
