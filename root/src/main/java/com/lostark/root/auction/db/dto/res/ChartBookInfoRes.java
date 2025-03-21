package com.lostark.root.auction.db.dto.res;


import com.lostark.root.auction.db.entity.ChartBookEntity;
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
public class ChartBookInfoRes {

    private String name;
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
    public static ChartBookInfoRes fromEntity(List<ChartBookEntity> entityList, String name, int currentMinPrice, int recentPrice) {
        List<Stat> list = new ArrayList<>();
        for ( ChartBookEntity entity : entityList) {
            Stat stat = Stat.builder()
                    .date(entity.getDate())
                    .avgPrice(entity.getAvgPrice())
                    .tradeCount(entity.getTradeCount())
                    .build();
            list.add(stat);
        }
        return ChartBookInfoRes.builder()
                .name(name)
                .currentMinPrice(currentMinPrice)
                .recentPrice(recentPrice)
                .stats(list)
                .build();
    }
}
