package com.lostark.root.auction.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatMinMaxEnum {

    /*  스텟 예상 범위 계산용
     *  경우의 수에 따른 최소치와 최대치
     */

    NECK_1(200010, 1, 13035, 15714),
    NECK_2(200010, 2, 13749, 16428),
    NECK_3(200010, 3, 15178, 17857),

    EARING_1(200020, 1, 10139, 12222),
    EARING_2(200020, 2, 10695, 12778),
    EARING_3(200020, 3, 11806, 13889),

    RING_1(200030, 1, 9414, 11349),
    RING_2(200030, 2, 9930, 11865),
    RING_3(200030, 3, 10962, 12897),

    NULL_OPTION(0, 0, 1, 1);

    private final int categoryCode;
    private final int upgrade;
    private final int minValue;
    private final int maxValue;


    public static StatMinMaxEnum getByCategory(int categoryCode, int upgrade) {
        for (StatMinMaxEnum item : StatMinMaxEnum.values()) {
            if (item.getCategoryCode() == categoryCode && item.getUpgrade() == upgrade) {
                return item; // 일치하는 항목 반환
            }
        }
        return NULL_OPTION; // 일치하는 항목이 없으면 null 반환
    }

}
