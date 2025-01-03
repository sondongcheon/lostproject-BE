package com.lostark.root.auction.db.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ChartInfoRes {

    private String time;
    private int buyPrice;
}
