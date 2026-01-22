package com.lostark.root.auction.db.dto.req;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Slf4j
public class SelectOptionReq {

    /* 클라이언트에서 선택한 옵션들을 받을 Request DTO
     *
     */

    private int tier;
    private int quality;
    private int statPer;
    private Integer upgradeLevel;
    private Integer tradeAllowCount;
    private int categoryCode;
    private String itemGrade;
    private int pageNo;
    // 자체 필터 시 OpenAPI Response 의 몇페이지 까지 사용했는지 기록용
    // 해당 페이지의 필터했던 매물이 어디까지인지
    private int numberCount;
    private List<EtcOption> etcOptionList;

    @Getter
    public static class EtcOption { //연마 옵션 종류, 값
        private int option;
        private int value;
    }

    public int getOptionFromList(int i) {
        return etcOptionList.get(i).getOption();
    }

    // 3티어 검색 있을 시절에 사용한 기능, 지금은 무조건 tier 4일 수 밖에 없음
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

    public int getValue(int index) {
        return etcOptionList.get(index).getValue();
    }

}
