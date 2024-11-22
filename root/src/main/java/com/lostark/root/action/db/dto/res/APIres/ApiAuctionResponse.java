package com.lostark.root.action.db.dto.res.APIres;

import java.time.LocalDateTime;
import java.util.List;

public class ApiAuctionResponse {
    public int PageNo;
    public int PageSize;
    public int TotalCount;
    public List<Item> Items;

    public static class Item {
        public String Name;
        public String Grade;
        public int Tier;
        public int Level;
        public String Icon;
        public int GradeQuality;
        public AuctionInfo AuctionInfo;
        public List<Option> Options;

        public static class AuctionInfo {
            public int StartPrice;
            public int BuyPrice;
            public int BidPrice;
            public LocalDateTime EndDate;
            public int BidCount;
            public int BidStartPrice;
            public boolean isCompetitive;
            public int TradeAllowCount;
            public int UpgradeLevel;
        }

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
}
