package com.lostark.root.auction.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OptionDisplayDel {
    /* 공식 옵션 코드와 표기 이름 ( 딜러 )
     */

    ATTACK_PERCENT(45, "공격력 "), // 이름에 의도적으로 띄어쓰기가 포함되어있으니 주의
    WEAPON_ATTACK_PERCENT(46, "무기 공격력 "), // 이름에 의도적으로 띄어쓰기가 포함되어 있으니 주의
    DAMAGE_TO_ENEMY(42, "적에게 주는 피해 증가"),
    ADDITIONAL_DAMAGE(41, "추가 피해"),
    CRITICAL_HIT_RATE(49, "치명타 적중률"),
    CRITICAL_DAMAGE(50, "치명타 피해"),

    NULL_OPTION(0,"");

    private final int option;
    private final String displayName;

    public static OptionDisplayDel getByName(String name) {
        for (OptionDisplayDel item : OptionDisplayDel.values()) {
            if (item.getDisplayName().equals(name)) {
                return item; // 일치하는 항목 반환
            }
        }
        return NULL_OPTION; // 일치하는 항목이 없으면 null 반환
    }
}
