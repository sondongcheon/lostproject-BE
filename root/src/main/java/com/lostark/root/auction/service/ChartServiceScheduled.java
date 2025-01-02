package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.ChartSelectTypeDto;
import com.lostark.root.auction.db.dto.req.APIreq.ApiAuctionReq;
import com.lostark.root.auction.db.dto.res.APIres.ApiAuctionRes;
import com.lostark.root.exception.CustomException;
import com.lostark.root.exception.ErrorCode;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Component
public class ChartServiceScheduled {

    private final EntityManager entityManager;

    @Value("${api.key}")
    private String apikey;

//    @Scheduled(cron = "${schedule.chart.cron}")
    @Transactional
    protected void run() {

        log.info("start Chart Info");
        //4T 고대 목걸이
        get4TGodaeNeckHH();
        get4TGodaeNeckMM();
        get4TGodaeNeckLL();
        get4TGodaeNeckHX();
        get4TGodaeNeckMX();
        get4TGodaeNeckXH();
        get4TGodaeNeckXM();
        //4T 고대 귀걸이
        get4TGodaeEaringHH();
        get4TGodaeEaringMM();
        get4TGodaeEaringLL();
        get4TGodaeEaringHX();
        get4TGodaeEaringMX();
        get4TGodaeEaringXH();
        get4TGodaeEaringXM();
        //4T 고대 반지
        get4TGodaeRingHH();
        get4TGodaeRingMM();
        get4TGodaeRingLL();
        get4TGodaeRingHX();
        get4TGodaeRingMX();
        get4TGodaeRingXH();
        get4TGodaeRingXM();

        log.info("end Chart Info");
    }

    private ApiAuctionRes requestAuction(ApiAuctionReq apiAuctionReq) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("accept", "application/json");
            headers.set("Authorization", "bearer " + apikey);
            String baseUrl = "https://developer-lostark.game.onstove.com/auctions/items";
            HttpEntity<ApiAuctionReq> requestEntity = new HttpEntity<>(apiAuctionReq, headers);

            return restTemplate.postForEntity(baseUrl, requestEntity, ApiAuctionRes.class).getBody();

        } catch (HttpStatusCodeException exception) {
            throw ApiErrorHandle(exception);
        }
    }

    private CustomException ApiErrorHandle(HttpStatusCodeException exception) {
        int statusCode = exception.getStatusCode().value();
        log.error(exception.getMessage());
        log.error("statusCode : {}", statusCode);
        return switch (statusCode) {
            case 400 -> new CustomException(ErrorCode.NO_PARAMETER);
            case 401 -> new CustomException(ErrorCode.API_KEY_ERROR);
            case 429 -> new CustomException(ErrorCode.TOO_MANY_API_REQUEST);
            case 503 -> new CustomException(ErrorCode.SERVICE_UNAVAILABLE);

            default -> throw new RuntimeException(exception);
        };
    }


    //4T고대 목걸이 상상
    private void get4TGodaeNeckHH () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200010, 4, 3, 3, 80)));
        saveResult(response, "chart_4t_neck_godae_hh");
    }

    //4T고대 목걸이 중중
    private void get4TGodaeNeckMM () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200010, 4, 2, 2, 80)));
        saveResult(response, "chart_4t_neck_godae_mm");
    }

    //4T고대 목걸이 하하
    private void get4TGodaeNeckLL () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200010, 4, 1, 1, 80)));
        saveResult(response, "chart_4t_neck_godae_ll");
    }

    //4T고대 목걸이 상X
    private void get4TGodaeNeckHX () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200010, 4, 3, 0, 80)));
        saveResult(response, "chart_4t_neck_godae_hx");
    }

    //4T고대 목걸이 중X
    private void get4TGodaeNeckMX () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200010, 4, 2, 0, 80)));
        saveResult(response, "chart_4t_neck_godae_mx");
    }

    //4T고대 목걸이 X상
    private void get4TGodaeNeckXH () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200010, 4, 0, 3, 80)));
        saveResult(response, "chart_4t_neck_godae_xh");
    }

    //4T고대 목걸이 X중
    private void get4TGodaeNeckXM () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200010, 4, 0, 2, 80)));
        saveResult(response, "chart_4t_neck_godae_xm");
    }

    //4T고대 귀걸이 상상
    private void get4TGodaeEaringHH () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200020, 4, 3, 3, 80)));
        saveResult(response, "chart_4t_earing_godae_hh");
    }

    //4T고대 귀걸이 중중
    private void get4TGodaeEaringMM () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200020, 4, 2, 2, 80)));
        saveResult(response, "chart_4t_earing_godae_mm");
    }

    //4T고대 귀걸이 하하
    private void get4TGodaeEaringLL () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200020, 4, 1, 1, 80)));
        saveResult(response, "chart_4t_earing_godae_ll");
    }

    //4T고대 귀걸이 상X
    private void get4TGodaeEaringHX () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200020, 4, 3, 0, 80)));
        saveResult(response, "chart_4t_earing_godae_hx");
    }

    //4T고대 귀걸이 중X
    private void get4TGodaeEaringMX () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200020, 4, 2, 0, 80)));
        saveResult(response, "chart_4t_earing_godae_mx");
    }

    //4T고대 귀걸이 X상
    private void get4TGodaeEaringXH () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200020, 4, 0, 3, 80)));
        saveResult(response, "chart_4t_earing_godae_xh");
    }

    //4T고대 귀걸이 X중
    private void get4TGodaeEaringXM () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200020, 4, 0, 2, 80)));
        saveResult(response, "chart_4t_earing_godae_xm");
    }

    //4T고대 반지 상상
    private void get4TGodaeRingHH () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200030, 4, 3, 3, 80)));
        saveResult(response, "chart_4t_ring_godae_hh");
    }

    //4T고대 반지 중중
    private void get4TGodaeRingMM () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200030, 4, 2, 2, 80)));
        saveResult(response, "chart_4t_ring_godae_mm");
    }

    //4T고대 반지 하하
    private void get4TGodaeRingLL () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200030, 4, 1, 1, 80)));
        saveResult(response, "chart_4t_ring_godae_ll");
    }

    //4T고대 반지 상X
    private void get4TGodaeRingHX () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200030, 4, 3, 0, 80)));
        saveResult(response, "chart_4t_ring_godae_hx");
    }

    //4T고대 반지 중X
    private void get4TGodaeRingMX () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200030, 4, 2, 0, 80)));
        saveResult(response, "chart_4t_ring_godae_mx");
    }

    //4T고대 반지 X상
    private void get4TGodaeRingXH () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200030, 4, 0, 3, 80)));
        saveResult(response, "chart_4t_ring_godae_xh");
    }

    //4T고대 반지 X중
    private void get4TGodaeRingXM () {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200030, 4, 0, 2, 80)));
        saveResult(response, "chart_4t_ring_godae_xm");
    }


    private void saveResult (ApiAuctionRes response, String tableName) {
        ApiAuctionRes.Item item = response.getItems().getFirst();
        String sql = "INSERT INTO " + tableName + " (tier, quality, upgrade, trade, price) VALUES (?, ?, ?, ?, ?)";
        entityManager
                .createNativeQuery(sql)
                .setParameter(1, item.getTier())
                .setParameter(2, item.getGradeQuality())
                .setParameter(3, item.getAuctionInfo().getUpgradeLevel())
                .setParameter(4, item.getAuctionInfo().getTradeAllowCount())
                .setParameter(5, item.getAuctionInfo().getBuyPrice())
                .executeUpdate();
    }

}
