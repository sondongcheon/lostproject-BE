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
        runGrade("고대");
        runGrade("유물");

        log.info("end Chart Info");
    }

    private void runGrade(String grade) {

        runCategory(grade, 200010);
        runCategory(grade, 200020);
        runCategory(grade, 200030);
    }

    private void runCategory(String grade, int category) {

        get4T(grade, category, 3, 3);
        get4T(grade, category, 2, 2);
        get4T(grade, category, 1, 1);
        get4T(grade, category, 3 ,2);
        get4T(grade, category, 2, 3);
        get4T(grade, category, 3 ,1);
        get4T(grade, category, 1, 3);
        get4T(grade, category, 2, 1);
        get4T(grade, category, 1, 2);
        get4T(grade, category, 3 ,0);
        get4T(grade, category, 2, 0);
        get4T(grade, category, 0, 3);
        get4T(grade, category, 0, 2);
    }

    private String toGradeTableName(String grade) {
        return switch (grade) {
            case "고대" -> "godae";
            case "유물" -> "umuoel";
            default -> null;
        };
    }

    private String toCategoryTableName(int category) {
        return switch (category) {
            case 200010 -> "neck";
            case 200020 -> "earing";
            case 200030 -> "ring";
            default -> null;
        };
    }

    private String toValue(int value) {
        return switch (value) {
            case 3 -> "h";
            case 2 -> "m";
            case 1 -> "l";
            case 0 -> "x";
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

    private void get4T (String grade, int category, int value, int value2) {
        ApiAuctionRes response = requestAuction(ApiAuctionReq.toChart(ChartSelectTypeDto.ofOption(category, 4, value, value2, 80, grade)));
        saveResult(response, "chart_4t_" + toCategoryTableName(category) + "_" + toGradeTableName(grade) + "_" + toValue(value) + toValue(value2));
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
