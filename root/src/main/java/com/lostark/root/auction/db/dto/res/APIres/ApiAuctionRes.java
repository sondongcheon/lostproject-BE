package com.lostark.root.auction.db.dto.res.APIres;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ApiAuctionRes {
    public int PageNo;
    public int PageSize;
    public int TotalCount;
    public List<Item> Items;

    @Getter
    @ToString
    public static class Item {
        public String Name;
        public String Grade;
        public int Tier;
        public int Level;
        public String Icon;
        public int GradeQuality;
        public AuctionInfo AuctionInfo;
        public List<Option> Options;

        @Getter
        public static class AuctionInfo {
            public int StartPrice;
            public int BuyPrice;
            public int BidPrice;
            public LocalDateTime EndDate;
            public int BidCount;
            public int BidStartPrice;
            public boolean isCompetitive;
            public int TradeAllowCount;
            public int upgradeLevel;
        }

        @Getter
        public static class Option {
            public String Type;
            public String OptionName;
            public String OptionNameTripod;
            public double Value;
            public boolean IsPenalty;
            public String ClassName;
            public boolean IsValuePercentage;
        }
    }

    public LocalDateTime getFirstItemEndDate() {
        return Items.getFirst().getAuctionInfo().getEndDate();
    }

    public int getBuyPrice(int i) {
        return Items.get(i).getAuctionInfo().getBuyPrice();
    }


}
