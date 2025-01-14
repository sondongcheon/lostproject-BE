package com.lostark.root.auction.db.dto.req;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Slf4j
public class SelectOptionReq {

    private int tier;
    private int quality;
    private Integer upgradeLevel;
    private Integer tradeAllowCount;
    private int categoryCode;
    private String itemGrade;
    private List<EtcOption> etcOptionList;

    @Getter
    public static class EtcOption {
        private int option;
        private int value;
    }

    public int getOptionFromList(int i) {
        return etcOptionList.get(i).getOption();
    }

    static public int filterTier(int tier, String itemGrade) {
        if(tier == 4) {
            return 4;
        } else { // tier == 3
            switch (itemGrade) {
                case "전설" -> {
                    return 1;
                }
                case "유물" -> {
                    return 2;
                }
                case "고대" -> {
                    return 3;
                }
            }
        }
        log.error("tier, itemGrade Error tier : {}, itemGrade : {}", tier, itemGrade);
        return 0;   //Error
    }

}
