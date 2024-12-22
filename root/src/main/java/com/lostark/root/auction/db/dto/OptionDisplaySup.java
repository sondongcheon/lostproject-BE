package com.lostark.root.auction.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OptionDisplaySup {

    IMPRESS_POWER(44, "낙인력"),
    WEAPON_ATTACK_PERCENT(46, "무기 공격력 "),
    SUP_IDENTITY_INCREASE(43, "세레나데, 신성, 조화 게이지 획득량 증가"),
    SUP_TEAM_ATTACK_POINT_INCREASE(51, "아군 공격력 강화 효과"),
    SUP_TEAM_DAMAGE_POINT_INCREASE(52, "아군 피해량 강화 효과"),
    PARTY_SHIELD_EFFECT(48, "파티원 보호막 효과"),
    PARTY_HEAL_EFFECT(47, "파티원 회복 효과"),

    NULL_OPTION(0,"");

    private final int option;
    private final String displayName;

    public static OptionDisplaySup getByName(String name) {
        for (OptionDisplaySup item : OptionDisplaySup.values()) {
            if (item.getDisplayName().equals(name)) {
                return item; // 일치하는 항목 반환
            }
        }
        return NULL_OPTION; // 일치하는 항목이 없으면 null 반환
    }
}
