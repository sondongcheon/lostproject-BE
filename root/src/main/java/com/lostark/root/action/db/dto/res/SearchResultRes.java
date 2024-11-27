package com.lostark.root.action.db.dto.res;

import com.lostark.root.action.db.dto.res.APIres.ApiAuctionRes;
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
    }

    @Getter
    @Builder
    public static class Option {
        public String optionName;
        public double value;

        static public Option fromApiResOption(ApiAuctionRes.Item.Option option) {
            return Option.builder()
                    .optionName(option.getOptionName())
                    .value(option.getValue())
                    .build();
        }
    }

    static public SearchResultRes fromApiRes(ApiAuctionRes apiAuctionRes) {
        List<Option> optionList = new ArrayList<>();
        for (int i = 0; i < apiAuctionRes.getItems().getFirst().getOptions().size(); i++) {
            optionList.add(Option.fromApiResOption(apiAuctionRes.getItems().getFirst().getOptions().get(i)));
        }

        return SearchResultRes.builder()
                .name(apiAuctionRes.getItems().getFirst().getName())
                .grade(apiAuctionRes.getItems().getFirst().getGrade())
                .icon(apiAuctionRes.getItems().getFirst().getIcon())
                .gradeQuality(apiAuctionRes.getItems().getFirst().getGradeQuality())
                .auctionInfo(AuctionInfo.fromApiResAuctionInfo(apiAuctionRes.getItems().getFirst().getAuctionInfo()))
                .options(optionList).build();
    }
}
