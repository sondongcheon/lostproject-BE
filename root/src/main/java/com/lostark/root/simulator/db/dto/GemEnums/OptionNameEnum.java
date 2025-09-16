package com.lostark.root.simulator.db.dto.GemEnums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OptionNameEnum {


    NONE_OPTION(0, null, null, null, null, null),
    HONDON_WAEGOK(1, "혼돈의 젬 : 왜곡", "공격력", "보스 피해", "아군 피해 강화", "아군 공격 강화"),
    HONDON_CHIMSIK(2, "혼돈의 젬 : 침식", "공격력", "추가 피해", "아군 피해 강화", "낙인력"),
    HONDON_BUNGGWE(3, "혼돈의 젬 : 붕괴", "추가 피해", "보스 피해", "낙인력", "아군 공격 강화"),
    JILSEO_BULBYEON(4, "질서의 젬 : 불변", "추가 피해", "보스 피해", "낙인력", "아군 공격 강화"),
    JILSEO_GYEONGO(5, "질서의 젬 : 견고", "공격력", "보스 피해", "아군 피해 강화", "공격 강화"),
    JILSEO_ANJEONG(6, "질서의 젬 : 안정", "공격력", "추가 피해", "아군 피해 강화", "낙인력");


    private final int type;
    private final String name;
    private final String option1;
    private final String option2;
    private final String option3;
    private final String option4;


}
