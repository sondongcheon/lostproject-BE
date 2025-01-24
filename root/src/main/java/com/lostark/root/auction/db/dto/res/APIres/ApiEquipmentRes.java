package com.lostark.root.auction.db.dto.res.APIres;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiEquipmentRes {

    @JsonProperty("ArmoryProfile")
    private Profile profile;

    @JsonProperty("ArmoryEquipment")
    private ArmoryEquipment[] equipment;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Profile {
        @JsonProperty("ItemAvgLevel")
        private String ItemAvgLevel;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ArmoryEquipment {
        @JsonProperty("Type")
        private String type;

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Icon")
        private String icon;

        @JsonProperty("Grade")
        private String grade;

        @JsonProperty("Tooltip")
        private String tooltip;
    }

}
