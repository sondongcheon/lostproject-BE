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

    @Scheduled(cron = "${schedule.chart.cron}")
    @Transactional
    protected void run() {

        log.info("start Chart Info");
        runDetail("고대");
        runDetail("유물");

        log.info("end Chart Info");
    }

    private void runDetail(String grade) {
        //4T 고대 목걸이
        get4TNeckHH(grade);
        get4TNeckMM(grade);
        get4TNeckLL(grade);
        get4TNeckHX(grade);
        get4TNeckMX(grade);
        get4TNeckXH(grade);
        get4TNeckXM(grade);
        //4T 고대 귀걸이
        get4TEaringHH(grade);
        get4TEaringMM(grade);
        get4TEaringLL(grade);
        get4TEaringHX(grade);
        get4TEaringMX(grade);
        get4TEaringXH(grade);
        get4TEaringXM(grade);
        //4T 고대 반지
        get4TRingHH(grade);
        get4TRingMM(grade);
        get4TRingLL(grade);
        get4TRingHX(grade);
        get4TRingMX(grade);
        get4TRingXH(grade);
        get4TRingXM(grade);
    }

    private String toGradeTableName(String grade) {
        return switch (grade) {
            case "고대" -> "godae";
            case "유물" -> "umuoel";
            default -> null;
        };
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
    private void get4TNeckHH (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200010, 4, 3, 3, 80, grade)));
        saveResult(response, "chart_4t_neck_" + toGradeTableName(grade) + "_hh");
    }

    //4T고대 목걸이 중중
    private void get4TNeckMM (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200010, 4, 2, 2, 80, grade)));
        saveResult(response, "chart_4t_neck_" + toGradeTableName(grade) + "_mm");
    }

    //4T고대 목걸이 하하
    private void get4TNeckLL (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200010, 4, 1, 1, 80, grade)));
        saveResult(response, "chart_4t_neck_" + toGradeTableName(grade) + "_ll");
    }

    //4T고대 목걸이 상X
    private void get4TNeckHX (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200010, 4, 3, 0, 80, grade)));
        saveResult(response, "chart_4t_neck_" + toGradeTableName(grade) + "_hx");
    }

    //4T고대 목걸이 중X
    private void get4TNeckMX (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200010, 4, 2, 0, 80, grade)));
        saveResult(response, "chart_4t_neck_" + toGradeTableName(grade) + "_mx");
    }

    //4T고대 목걸이 X상
    private void get4TNeckXH (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200010, 4, 0, 3, 80, grade)));
        saveResult(response, "chart_4t_neck_" + toGradeTableName(grade) + "_xh");
    }

    //4T고대 목걸이 X중
    private void get4TNeckXM (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200010, 4, 0, 2, 80, grade)));
        saveResult(response, "chart_4t_neck_" + toGradeTableName(grade) + "_xm");
    }

    //4T고대 귀걸이 상상
    private void get4TEaringHH (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200020, 4, 3, 3, 80, grade)));
        saveResult(response, "chart_4t_earing_" + toGradeTableName(grade) + "_hh");
    }

    //4T고대 귀걸이 중중
    private void get4TEaringMM (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200020, 4, 2, 2, 80, grade)));
        saveResult(response, "chart_4t_earing_" + toGradeTableName(grade) + "_mm");
    }

    //4T고대 귀걸이 하하
    private void get4TEaringLL (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200020, 4, 1, 1, 80, grade)));
        saveResult(response, "chart_4t_earing_" + toGradeTableName(grade) + "_ll");
    }

    //4T고대 귀걸이 상X
    private void get4TEaringHX (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200020, 4, 3, 0, 80, grade)));
        saveResult(response, "chart_4t_earing_" + toGradeTableName(grade) + "_hx");
    }

    //4T고대 귀걸이 중X
    private void get4TEaringMX (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200020, 4, 2, 0, 80, grade)));
        saveResult(response, "chart_4t_earing_" + toGradeTableName(grade) + "_mx");
    }

    //4T고대 귀걸이 X상
    private void get4TEaringXH (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200020, 4, 0, 3, 80, grade)));
        saveResult(response, "chart_4t_earing_" + toGradeTableName(grade) + "_xh");
    }

    //4T고대 귀걸이 X중
    private void get4TEaringXM (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200020, 4, 0, 2, 80, grade)));
        saveResult(response, "chart_4t_earing_" + toGradeTableName(grade) + "_xm");
    }

    //4T고대 반지 상상
    private void get4TRingHH (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200030, 4, 3, 3, 80, grade)));
        saveResult(response, "chart_4t_ring_" + toGradeTableName(grade) + "_hh");
    }

    //4T고대 반지 중중
    private void get4TRingMM (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200030, 4, 2, 2, 80, grade)));
        saveResult(response, "chart_4t_ring_" + toGradeTableName(grade) + "_mm");
    }

    //4T고대 반지 하하
    private void get4TRingLL (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200030, 4, 1, 1, 80, grade)));
        saveResult(response, "chart_4t_ring_" + toGradeTableName(grade) + "_ll");
    }

    //4T고대 반지 상X
    private void get4TRingHX (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200030, 4, 3, 0, 80, grade)));
        saveResult(response, "chart_4t_ring_" + toGradeTableName(grade) + "_hx");
    }

    //4T고대 반지 중X
    private void get4TRingMX (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200030, 4, 2, 0, 80, grade)));
        saveResult(response, "chart_4t_ring_" + toGradeTableName(grade) + "_mx");
    }

    //4T고대 반지 X상
    private void get4TRingXH (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200030, 4, 0, 3, 80, grade)));
        saveResult(response, "chart_4t_ring_" + toGradeTableName(grade) + "_xh");
    }

    //4T고대 반지 X중
    private void get4TRingXM (String grade) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(200030, 4, 0, 2, 80, grade)));
        saveResult(response, "chart_4t_ring_" + toGradeTableName(grade) + "_xm");
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
