package com.lostark.root.auction.db.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChartSelectTypeDto {

    private OptionValueEnum etcOption;
    private OptionValueEnum etcOption2;
    private int quality;
    private int categoryCode;
    private String grade;

    public static ChartSelectTypeDto ofOption(int categoryCode, int tier, int valueLevel, int valueLevel2, int quality, String grade, int type) {
        int option;
        if(type == 0) {
            option =
                    switch (categoryCode) {
                        case 200010 -> 41;
                        case 200020 -> 45;
                        case 200030 -> 49;
                        default -> 0;
                    };
        } else {
            option =
                    switch (categoryCode) {
                        case 200010 -> 43;
                        case 200020 -> 47;
                        case 200030 -> 51;
                        default -> 0;
                    };
        }

        return ChartSelectTypeDto.builder()
                .etcOption(OptionValueEnum.getByOptionTierValueLevel(option, tier, valueLevel))
                .etcOption2(OptionValueEnum.getByOptionTierValueLevel(option+1, tier, valueLevel2))
                .quality(quality)
                .categoryCode(categoryCode)
                .grade(grade)
                .build();
    }


}
