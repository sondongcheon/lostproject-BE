package com.lostark.root.auction.db.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChartSelectTypeDto {

    /* 악세서리 정보 스케줄링 요청용 DTO, Request 전 중간다리 역할
     * 고정값, 규칙값이 많아 지정값이 많음
     */

    private OptionValueEnum etcOption;
    private OptionValueEnum etcOption2;
    private int quality;
    private int categoryCode;
    private String grade;

    // 카테고리 저장시 옵션 종류는 고정이므로 switch 통해 입력
    // 옵션 종류가 2종류 이므로 고정하고 value 통해 상 중 하 를 규칙성 구분함 (ex. 상하 하상 같은 경우)
    public static ChartSelectTypeDto ofOption(int categoryCode, int tier, int valueLevel, int valueLevel2, int quality, String grade, int type) {
        int option;
        // [ 목걸이 : 200010 ] , [ 귀걸이 : 200020 ] , [ 반지 : 200030 ]
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
                .etcOption(OptionValueEnum.getByOptionTierValueLevel(option, tier, valueLevel)) // ex. 추피 다음 코드는 적추피 인걸 활용한 option+1
                .etcOption2(OptionValueEnum.getByOptionTierValueLevel(option+1, tier, valueLevel2))
                .quality(quality)
                .categoryCode(categoryCode)
                .grade(grade)
                .build();
    }


}
