package com.lostark.root.auction.db.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChartBookRes {

    private LocalDate date;
    private double avgPrice;
    private int tradeCount;
}
