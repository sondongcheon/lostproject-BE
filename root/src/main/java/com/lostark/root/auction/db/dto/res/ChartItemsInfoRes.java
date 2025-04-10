package com.lostark.root.auction.db.dto.res;


import com.lostark.root.auction.db.entity.ChartItemsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChartItemsInfoRes {

    private String name;
    private String iconUrl;
    private int currentMinPrice;
    private int recentPrice;
    private List<Stat> stats;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Stat {

        private LocalDate date;
        private double avgPrice;
        private int tradeCount;

    }
    public static ChartItemsInfoRes fromEntity(List<ChartItemsEntity> entityList, String name, int currentMinPrice, int recentPrice, String iconUrl) {
        List<Stat> list = new ArrayList<>();
        for ( ChartItemsEntity entity : entityList) {
            Stat stat = Stat.builder()
                    .date(entity.getDate())
                    .avgPrice(entity.getAvgPrice())
                    .tradeCount(entity.getTradeCount())
                    .build();
            list.add(stat);
        }
        return ChartItemsInfoRes.builder()
                .name(name)
                .iconUrl(iconUrl)
                .currentMinPrice(currentMinPrice)
                .recentPrice(recentPrice)
                .stats(list)
                .build();
    }
}
