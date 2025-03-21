package com.lostark.root.auction.service;

import com.lostark.root.common.staticMethod.ApiRequest;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class ChartBookServiceScheduled {

    private final EntityManager entityManager;

    @Scheduled(cron = "${schedule.book.cron}")
    @Transactional
    protected void saveResult() throws InterruptedException {

        List<String> names = List.of("adrenaline", "onehan", "yeahdun", "doldae", "jebat", "gisop", "jiljeng", "tadae", "galdae", "mahee");
        List<String> ids = List.of("65203905", "65200505", "65201005", "65203305", "65202805", "65203005", "65203505", "65203705", "65201505", "65203105");


        for (int i =0; i< ids.size(); i++) {
            List<Map<String, Object>> responseList = (List<Map<String, Object>>) ApiRequest.requestGetAPI("markets/items", ids.get(i));
            List<Map<String, Object>> list = (List<Map<String, Object>>) responseList.get(1).get("Stats");

            StringBuilder sql = new StringBuilder("INSERT INTO chart_book_");
            sql.append(names.get(i)).append(" (date, avg_price, trade_count) VALUES (?, ?, ?)");
            entityManager
                    .createNativeQuery(sql.toString())
                    .setParameter(1, list.getFirst().get("Date"))
                    .setParameter(2, list.getFirst().get("AvgPrice"))
                    .setParameter(3, list.getFirst().get("TradeCount"))
                    .executeUpdate();
            System.out.println("실행");
        }
    }
}
