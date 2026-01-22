package com.lostark.root.auction.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OptionDisplay {

    /* 공식 옵션 코드와 표기 이름 ( 전체 )
     */

    ATTACK_PERCENT(45, "공격력 "),
    //ATTACK_PLUS(53, "공격력 "),
    IMPRESS_POWER(44, "낙인력"),
    WEAPON_ATTACK_PERCENT(46, "무기 공격력 "),
    //WEAPON_ATTACK_PLUS(54, "무기 공격력 "),
    //STATUS_ATTACK_DURATION(57, "상태이상 공격 지속시간"),
    SUP_IDENTITY_INCREASE(43, "세레나데, 신성, 조화 게이지 획득량 증가"),
    SUP_IDENTITY_INCREASE_2(43, "세레나데, 신앙, 조화 게이지 획득량"),
    SUP_TEAM_ATTACK_POINT_INCREASE(51, "아군 공격력 강화 효과"),
    SUP_TEAM_DAMAGE_POINT_INCREASE(52, "아군 피해량 강화 효과"),
    DAMAGE_TO_ENEMY(42, "적에게 주는 피해"),
    //HP_RECOVERY_IN_BATTLE(58, "전투 중 생명력 회복량"),
    //MAX_MANA(56, "최대 마나"),
    //MAX_HP(55, "최대 생명력"),
    ADDITIONAL_DAMAGE(41, "추가 피해"),
    CRITICAL_HIT_RATE(49, "치명타 적중률"),
    CRITICAL_DAMAGE(50, "치명타 피해"),
    PARTY_SHIELD_EFFECT(48, "파티원 보호막 효과"),
    PARTY_HEAL_EFFECT(47, "파티원 회복 효과"),

    NULL_OPTION(0,"");

    private final int option;
    private final String displayName;

    public static OptionDisplay getByName(String name) {
        for (OptionDisplay item : OptionDisplay.values()) {
            if (item.getDisplayName().equals(name)) {
                return item; // 일치하는 항목 반환
            }
        }
        return NULL_OPTION; // 일치하는 항목이 없으면 null 반환
    }
}
