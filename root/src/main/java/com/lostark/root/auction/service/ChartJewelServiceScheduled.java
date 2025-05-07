package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.req.APIreq.ApiAuctionReq;
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
public class ChartJewelServiceScheduled {

    private final EntityManager entityManager;

    //@Scheduled(cron = "${schedule.jewel.cron}")
    @Transactional
    protected void saveResult() throws InterruptedException {
        log.info("start Jewel Info");
        List<String> ids = List.of("7레벨 작열", "8레벨 작열", "9레벨 작열", "10레벨 작열", "7레벨 겁화", "8레벨 겁화", "9레벨 겁화", "10레벨 겁화");
        List<String> names = List.of("jack_7", "jack_8", "jack_9", "jack_10", "geop_7", "geop_8", "geop_9", "geop_10");


        for (int i =0; i< ids.size(); i++) {
            Map<String, Object> result = (Map<String, Object>) ApiRequest.requestPostAPI("auctions/items", ApiAuctionReq.forJewel(ids.get(i)));
            if(result == null) continue;
            Map<String, Object> response = ((List<Map<String, Object>>) result.get("Items")).getFirst();
            int totalCount = (int) result.get("TotalCount");
            int buyPrice = (int) ((Map<String, Object>) response.get("AuctionInfo")).get("BuyPrice");

            StringBuilder sql = new StringBuilder("INSERT INTO chart_jewel_");
            sql.append(names.get(i)).append(" (total_count, price) VALUES (?, ?)");
            entityManager
                    .createNativeQuery(sql.toString())
                    .setParameter(1, totalCount)
                    .setParameter(2, buyPrice)
                    .executeUpdate();
        }

        log.info("end Jewel Info");
    }

}
