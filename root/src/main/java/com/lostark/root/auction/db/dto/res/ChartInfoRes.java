package com.lostark.root.auction.db.dto.res;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChartInfoRes {

    private List<ChartInfo> chartInfoList;

    private int monthMin;
    private int monthMinP;
    private int monthAvg;
    private int monthAvgP;

    private int weeklyMin;
    private int weeklyMinP;
    private int weeklyAvg;
    private int weeklyAvgP;

    private String name;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChartInfo {
        private String time;
        private int buyPrice;
    }
}
