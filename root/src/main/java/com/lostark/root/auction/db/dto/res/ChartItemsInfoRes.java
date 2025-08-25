package com.lostark.root.auction.db.dto.res;


import com.lostark.root.auction.db.entity.ChartItemsEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    //골두꺼비 이벤트 까지만 사용
    //private GoldoEvent goldoEvent;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Stat {

        private LocalDate date;
        private double avgPrice;
        private int tradeCount;

    }
    //골두꺼비 이벤트 까지만 사용
    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class GoldoEvent {

        private double avgPrice;
    }

    public static ChartItemsInfoRes fromEntity(List<ChartItemsEntity> entityList, String name, int currentMinPrice, int recentPrice, String iconUrl) {
        List<Stat> list = entityList.stream()
                .filter(Objects::nonNull)
                .map(entity -> Stat.builder()
                        .date(entity.getDate())
                        .avgPrice(entity.getAvgPrice())
                        .tradeCount(entity.getTradeCount())
                        .build())
                .collect(Collectors.toList());
        return ChartItemsInfoRes.builder()
                .name(name)
                .iconUrl(iconUrl)
                .currentMinPrice(currentMinPrice)
                .recentPrice(recentPrice)
                .stats(list)
                //.goldoEvent(goldoEvent)
                .build();
    }
}
