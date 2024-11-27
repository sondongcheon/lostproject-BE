package com.lostark.root.action.db.dto.req.APIreq;

import com.lostark.root.action.db.dto.req.SelectOptionReq;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ApiAuctionReq {

    private int itemGradeQuality;
    private Integer itemUpgradeLevel;
    private Integer itemTradeAllowCount;
    private List<EtcOption> etcOptions;
    private String sort;
    @Setter
    private int categoryCode;
    private int itemTier;
    private String itemGrade;
    private int pageNo;
    private String sortCondition;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Builder
    public static class EtcOption {

        private int firstOption;
        private int secondOption;
        private int minValue;
        private int maxValue;

        static public EtcOption fromSelectEtcOption(SelectOptionReq.EtcOption etcOption) {
            return EtcOption.builder()
                    .firstOption(7)
                    .secondOption(etcOption.getOption())
                    .minValue(etcOption.getValue())
                    .maxValue(etcOption.getValue()).build();
        }
    }

    static public ApiAuctionReq fromSelectOption(SelectOptionReq selectOptionReq) {
        List<EtcOption> etcOptionList = new ArrayList<>();
        for (int i = 0; i < selectOptionReq.getEtcOptionList().size(); i++) {
            etcOptionList.add(ApiAuctionReq.EtcOption.fromSelectEtcOption(selectOptionReq.getEtcOptionList().get(i)));
        }
//        if(selectOptionReq.getEtcOptionList().size() == 1) etcOptionList.add(new EtcOption());
        return ApiAuctionReq.builder()
                .itemGradeQuality(selectOptionReq.getQuality())
                .itemUpgradeLevel(selectOptionReq.getUpgradeLevel())
                .itemTradeAllowCount(selectOptionReq.getTradeAllowCount())
                .etcOptions(etcOptionList)
                .sort("BUY_PRICE")
                .categoryCode(selectOptionReq.getCategoryCode())
                .itemTier(4)
                .itemGrade(selectOptionReq.getItemGrade())
                .pageNo(0)
                .sortCondition("ASC").build();
    }
}
