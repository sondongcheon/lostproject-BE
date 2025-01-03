package com.lostark.root.auction.db.dto.res;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChartInfoRes {

    private String time;
    private int buyPrice;
}
