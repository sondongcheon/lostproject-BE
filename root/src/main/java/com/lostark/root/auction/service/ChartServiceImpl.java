package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.req.APIreq.ApiAuctionReq;
import com.lostark.root.auction.db.dto.res.APIres.ApiAuctionRes;
import com.lostark.root.auction.db.entity.Chart4tNeckGodaeSangsangEntity;
import com.lostark.root.auction.db.repository.Chart4tNeckGodaeSangsangRepository;
import com.lostark.root.exception.CustomException;
import com.lostark.root.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChartServiceImpl implements ChartService{

    private final Chart4tNeckGodaeSangsangRepository chart4tNeckGodaeSangsangRepository;

    @Value("${api.key}")
    private String apikey;

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

    @Scheduled(cron = "${schedule.chart.cron}")
    private void get4TGodaeNeckSangsang () {
        log.info("start charting");

        ApiAuctionRes result = requestAuction(ApiAuctionReq.toChart());
        Chart4tNeckGodaeSangsangEntity entity = Chart4tNeckGodaeSangsangEntity.fromApiRes(result.getItems().getFirst());

        chart4tNeckGodaeSangsangRepository.save(entity);

        log.info("end charting");
    }

}
