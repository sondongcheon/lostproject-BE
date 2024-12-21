package com.lostark.root.auction.db.dto.res;

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
        public double value;

        static public Option fromApiResOption(ApiAuctionRes.Item.Option option) {
            return Option.builder()
                    .optionName(option.getOptionName())
                    .value(option.getValue())
                    .build();
        }

        static public Option NoneResult() {
            return Option.builder().build();
        }
    }

    static public SearchResultRes fromApiRes(ApiAuctionRes apiAuctionRes, int duplication) {
        if(apiAuctionRes.getItems().size() <= duplication) return NoneResult();
        List<Option> optionList = new ArrayList<>();
        for (int i = 0; i < apiAuctionRes.getItems().get(duplication).getOptions().size(); i++) {
            optionList.add(Option.fromApiResOption(apiAuctionRes.getItems().get(duplication).getOptions().get(i)));
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
