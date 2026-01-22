package com.lostark.root.auction.db.dto.req;

import lombok.Getter;

@Getter
public class CustomChartReq {

    /* CustomChartEnum 과 연관이 있음
     * 여기에서 Request 받은 번호로 해당 Enum 의 number 로 대응
     */
    private int box1;
    private int box2;
    private int box3;
    private int box4;
    private int box5;
}
