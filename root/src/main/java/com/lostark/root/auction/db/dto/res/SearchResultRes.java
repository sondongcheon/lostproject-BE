package com.lostark.root.auction.db.dto.res;

import com.lostark.root.auction.db.dto.OptionDisplayDel;
import com.lostark.root.auction.db.dto.OptionDisplaySup;
import com.lostark.root.auction.db.dto.OptionValueEnum;
import com.lostark.root.auction.db.dto.req.SelectOptionReq;
import com.lostark.root.auction.db.dto.res.APIres.ApiAuctionRes;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class SearchResultRes {

    public String name;
    public String grade;
    public String icon;
    public int gradeQuality;
    public AuctionInfo auctionInfo;
    public List<Option> options;

    @Getter
    @Builder
    public static class AuctionInfo {
        public int startPrice;
        public int buyPrice;
        public int bidPrice;
        public LocalDateTime endDate;
        public int bidCount;
        public int bidStartPrice;
        public boolean isCompetitive;
        public int tradeAllowCount;
        public int upgradeLevel;

        static public AuctionInfo fromApiResAuctionInfo(ApiAuctionRes.Item.AuctionInfo auctionInfo) {
            return AuctionInfo.builder()
                    .startPrice(auctionInfo.getStartPrice())
                    .buyPrice(auctionInfo.getBuyPrice())
                    .bidPrice(auctionInfo.getBidPrice())
                    .endDate(auctionInfo.getEndDate())
                    .bidCount(auctionInfo.getBidCount())
                    .bidStartPrice(auctionInfo.getBidStartPrice())
                    .isCompetitive(auctionInfo.isCompetitive())
                    .tradeAllowCount(auctionInfo.getTradeAllowCount())
                    .upgradeLevel(auctionInfo.getUpgradeLevel())
                    .build();
        }

        static public AuctionInfo NoneResult() {
            return AuctionInfo.builder()
                    .buyPrice(0)
                    .endDate(LocalDateTime.now())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class Option {
        public String optionName;
        public String value;
        public String valueGrade;

        static public Option fromApiResOption(ApiAuctionRes.Item.Option option, int type, int tier, String itemGrade) {

            int val = type == 0 ? OptionDisplayDel.getByName(option.getOptionName()).getOption() : OptionDisplaySup.getByName(option.getOptionName()).getOption();
            OptionValueEnum optionValueEnum = OptionValueEnum.getByDisplayValue(val, option.getValue() + (option.IsValuePercentage ? "%" : null), SelectOptionReq.filterTier(tier, itemGrade));
            String grade = null;
            switch (optionValueEnum.getValueLevel()) {
                case 3 -> grade = "상";
                case 2 -> grade = "중";
                case 1 -> grade = "하";
            }
            return Option.builder()
                    .optionName(option.getOptionName())
                    .value(option.IsValuePercentage ? option.getValue() + "%" : option.getValue() + "")
                    .valueGrade(grade == null ? "" : grade + " -" + tier + "T" + itemGrade)
                    .build();
        }

    }

    static public SearchResultRes fromApiRes(ApiAuctionRes apiAuctionRes, int duplication, int type) {
        if(apiAuctionRes.getItems().size() <= duplication) return NoneResult();
        List<Option> optionList = new ArrayList<>();
        for (int i = 0; i < apiAuctionRes.getItems().get(duplication).getOptions().size(); i++) {
            ApiAuctionRes.Item target = apiAuctionRes.getItems().get(duplication);
            optionList.add(Option.fromApiResOption(target.getOptions().get(i), type, target.getTier() , target.getGrade()));
        }

        return SearchResultRes.builder()
                .name(apiAuctionRes.getItems().get(duplication).getName())
                .grade(apiAuctionRes.getItems().get(duplication).getGrade())
                .icon(apiAuctionRes.getItems().get(duplication).getIcon())
                .gradeQuality(apiAuctionRes.getItems().get(duplication).getGradeQuality())
                .auctionInfo(AuctionInfo.fromApiResAuctionInfo(apiAuctionRes.getItems().get(duplication).getAuctionInfo()))
                .options(optionList).build();
    }

    static public SearchResultRes NoneResult() {
        return SearchResultRes.builder()
                .name("검색 결과 없음")
                .grade("없음")
                .auctionInfo(AuctionInfo.NoneResult())
                .options(new ArrayList<>())
                .build();

    }
}
