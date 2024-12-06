package com.lostark.root.action.db.dto.req;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
public class SelectOptionReq {

    private int quality;
    private Integer upgradeLevel;
    private Integer tradeAllowCount;
    private int categoryCode;
    private String itemGrade;
    private List<EtcOption> etcOptionList;

    @Getter
    @ToString
    public static class EtcOption {
        private int option;
        private int value;
    }

}
