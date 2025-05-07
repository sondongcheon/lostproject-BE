package com.lostark.root.auction.service;


import com.lostark.root.common.staticMethod.ApiRequest;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class ChartItemsServiceScheduled {

    private final EntityManager entityManager;

    //@Scheduled(cron = "${schedule.book.cron}")
    @Transactional
    protected void saveBookResult() throws InterruptedException {

        List<String> names = List.of("adrenaline",
                "onehan", "yeahdun", "doldae", "jebat", "gisop",
                "jiljeng", "tadae", "galdae", "mahee", "supercharge",
                "junmon", "mahwojung", "gacksung", "gudong", "socksock",
                "bari", "ansang", "junggap", "jangdan", "gpta",
                "attar", "maxmana", "junggi");
        List<String> ids = List.of("65203905",
                "65200505", "65201005", "65203305", "65202805", "65203005",
                "65203505", "65203705", "65201505", "65203105", "65200605",
                "65204105", "65201305", "65203405", "65200805", "65204005",
                "65203205", "65200405", "65202105", "65204305", "65201105",
                "65200305", "65201205", "65200205");


        for (int i =0; i< ids.size(); i++) {
            List<Map<String, Object>> responseList = (List<Map<String, Object>>) ApiRequest.requestGetAPI("markets/items", ids.get(i));
            List<Map<String, Object>> list = (List<Map<String, Object>>) responseList.get(1).get("Stats");

            StringBuilder sql = new StringBuilder("INSERT INTO chart_book_");
            sql.append(names.get(i)).append(" (date, avg_price, trade_count) VALUES (?, ?, ?)");
            entityManager
                    .createNativeQuery(sql.toString())
                    .setParameter(1, list.get(1).get("Date"))
                    .setParameter(2, list.get(1).get("AvgPrice"))
                    .setParameter(3, list.get(1).get("TradeCount"))
                    .executeUpdate();
        }
    }

    //@Scheduled(cron = "${schedule.upgrade.cron}")
    @Transactional
    protected void saveUpgradeResult() throws InterruptedException {


        List<String> names = List.of("yagumbook_14",
                "yagumscroll_2", "jaebongbook_14", "jaebongscroll_2", "yagumscroll_1", "jaebongscroll_1",
                "unpa_l", "unpa_m", "yongseom", "unpa_s", "bingseom",
                "avibos", "undol", "unpasuk", "unsusuk");
        List<String> ids = List.of("66112543",
                "66112713", "66112546", "66112714", "66112711", "66112712",
                "66130143", "66130142", "66111131", "66130141", "66111132",
                "6861012", "66110225", "66102006", "66102106");


        for (int i =0; i< ids.size(); i++) {
            List<Map<String, Object>> responseList = (List<Map<String, Object>>) ApiRequest.requestGetAPI("markets/items", ids.get(i));
            List<Map<String, Object>> list = (List<Map<String, Object>>) responseList.get(0).get("Stats");

            StringBuilder sql = new StringBuilder("INSERT INTO chart_upgrade_");
            sql.append(names.get(i)).append(" (date, avg_price, trade_count) VALUES (?, ?, ?)");
            entityManager
                    .createNativeQuery(sql.toString())
                    .setParameter(1, list.get(1).get("Date"))
                    .setParameter(2, list.get(1).get("AvgPrice"))
                    .setParameter(3, list.get(1).get("TradeCount"))
                    .executeUpdate();


        }
    }
}
