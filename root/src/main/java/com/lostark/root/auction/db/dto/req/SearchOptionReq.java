package com.lostark.root.auction.db.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchOptionReq {

    @JsonProperty("ItemGradeQuality")
    private int itemGradeQuality;

    @JsonProperty("ItemUpgradeLevel")
    private Integer itemUpgradeLevel;

    @JsonProperty("ItemTradeAllowCount")
    private Integer itemTradeAllowCount;

    @JsonProperty("EtcOptions")
    private List<EtcOption> etcOptions;

    @JsonProperty("Sort")
    private String sort;

    @JsonProperty("CategoryCode")
    private int categoryCode;

    @JsonProperty("CharacterClass")
    private String characterClass;

    @JsonProperty("ItemTier")
    private int itemTier;

    @JsonProperty("ItemGrade")
    private String itemGrade;

    @JsonProperty("PageNo")
    private int pageNo;

    @JsonProperty("SortCondition")
    private String sortCondition;

    @Getter
    @Setter
    public static class EtcOption {

        @JsonProperty("FirstOption")
        private int firstOption;

        @JsonProperty("SecondOption")
        private int secondOption;

        @JsonProperty("MinValue")
        private int minValue;

        @JsonProperty("MaxValue")
        private int maxValue;
    }
}
