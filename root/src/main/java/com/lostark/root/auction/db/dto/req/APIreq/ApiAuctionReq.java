package com.lostark.root.auction.db.dto.req.APIreq;

import com.lostark.root.auction.db.dto.ChartSelectTypeDto;
import com.lostark.root.auction.db.dto.OptionValueEnum;
import com.lostark.root.auction.db.dto.req.SelectOptionReq;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@ToString
public class ApiAuctionReq {

    private int itemGradeQuality;
    private Integer itemUpgradeLevel;
    private Integer itemTradeAllowCount;
    @Setter
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
    @ToString
    @Setter
    @Builder
    public static class EtcOption {

        private Integer firstOption;
        private Integer secondOption;
        private Integer minValue;
        private Integer maxValue;

        static public EtcOption fromSelectEtcOption(SelectOptionReq.EtcOption etcOption, int tier) {
            OptionValueEnum option = OptionValueEnum.getByOptionTierValueLevel(etcOption.getOption(), tier, etcOption.getValue());
            return EtcOption.builder()
                    .firstOption(7)
                    .secondOption(etcOption.getOption() == 0 ? null : etcOption.getOption())
                    .minValue(option.getValue())
                    .maxValue(option.getValue()).build();
        }
    }

    static public ApiAuctionReq fromSelectOption(SelectOptionReq selectOptionReq) {
        List<EtcOption> etcOptionList = new ArrayList<>();
        for (int i = 0; i < selectOptionReq.getEtcOptionList().size(); i++) {
            etcOptionList.add(ApiAuctionReq.EtcOption.fromSelectEtcOption(selectOptionReq.getEtcOptionList().get(i), SelectOptionReq.filterTier(selectOptionReq.getTier(), selectOptionReq.getItemGrade())));
        }
//        if(selectOptionReq.getEtcOptionList().size() == 1) etcOptionList.add(new EtcOption());
        return ApiAuctionReq.builder()
                .itemGradeQuality(selectOptionReq.getQuality())
                .itemUpgradeLevel(selectOptionReq.getUpgradeLevel())
                .itemTradeAllowCount(selectOptionReq.getTradeAllowCount())
                .etcOptions(etcOptionList)
                .sort("BUY_PRICE")
                .categoryCode(selectOptionReq.getCategoryCode())
                .itemTier(selectOptionReq.getTier())
                .itemGrade(selectOptionReq.getItemGrade())
                .pageNo(0)
                .sortCondition("ASC").build();
    }

    static public ApiAuctionReq toChart(ChartSelectTypeDto dto) {
        List<EtcOption> etcOptionList = new ArrayList<>();
        etcOptionList.add(EtcOption.builder().firstOption(7).secondOption(dto.getEtcOption().getOption() == 0 ? null : dto.getEtcOption().getOption()).minValue(dto.getEtcOption().getValue()).maxValue(dto.getEtcOption().getValue()).build());
        etcOptionList.add(EtcOption.builder().firstOption(7).secondOption(dto.getEtcOption2().getOption() == 0 ? null : dto.getEtcOption2().getOption()).minValue(dto.getEtcOption2().getValue()).maxValue(dto.getEtcOption2().getValue()).build());

        return ApiAuctionReq.builder()
                .itemGradeQuality(dto.getQuality())
                .itemUpgradeLevel(null)
                .itemTradeAllowCount(null)
                .etcOptions(etcOptionList)
                .sort("BUY_PRICE")
                .categoryCode(dto.getCategoryCode())
                .itemTier(4)
                .itemGrade(dto.getGrade())
                .pageNo(0)
                .sortCondition("ASC").build();
    }

    public Integer getSecondOption(int index) {
        return etcOptions.get(index).getSecondOption();
    }

    public void setValue(int index, int value) {
        etcOptions.get(index).setFirstOption(7);
        etcOptions.get(index).setMinValue(value);
        etcOptions.get(index).setMaxValue(value);
    }

    public void setAccInfo(int index, int type) {
        setCategoryCode(200010 + (index * 10));
        etcOptions.getFirst().setSecondOption(41 + type + (index * 4));
        etcOptions.get(1).setSecondOption(42 + type + (index * 4));
    }
}
