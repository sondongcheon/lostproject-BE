package com.lostark.root.auction.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OptionValueEnum {

    // 공격력%
    ATTACK_PERCENT(45, 1, "0.19%", 1, 1),
    ATTACK_PERCENT_2(45, 2, "0.24%", 2, 1),
    ATTACK_PERCENT_3(45, 3, "0.29%", 3, 1),
    ATTACK_PERCENT_4(45, 4, "0.4%", 4, 1),
    ATTACK_PERCENT_5(45, 5, "0.42%", 1, 2),
    ATTACK_PERCENT_6(45, 6, "0.54%", 2, 2),
    ATTACK_PERCENT_7(45, 7, "0.66%", 3, 2),
    ATTACK_PERCENT_8(45, 8, "0.7%", 1, 3),
    ATTACK_PERCENT_9(45, 9, "0.89%", 2, 3),
    ATTACK_PERCENT_10(45, 10, "0.95%", 4, 2),
    ATTACK_PERCENT_11(45, 11, "1.09%", 3, 3),
    ATTACK_PERCENT_12(45, 12, "1.55%", 4, 3),

    // 공격력 +
    ATTACK_PLUS(53, 1, "9", 1, 1),
    ATTACK_PLUS_2(53, 2, "14", 2, 1),
    ATTACK_PLUS_3(53, 3, "19", 1, 2),
    ATTACK_PLUS_4(53, 4, "24", 3, 1),
    ATTACK_PLUS_5(53, 5, "33", 2, 2),
    ATTACK_PLUS_6(53, 6, "40", 1, 3),
    ATTACK_PLUS_7(53, 7, "61", 3, 2),
    ATTACK_PLUS_8(53, 8, "68", 2, 3),
    ATTACK_PLUS_9(53, 9, "80", 4, 1),
    ATTACK_PLUS_10(53, 10, "118", 3, 3),
    ATTACK_PLUS_11(53, 11, "195", 4, 2),
    ATTACK_PLUS_12(53, 12, "390", 4, 3),

    // 낙인력
    IMPRESS_POWER(44, 1, "0.96%", 1, 1),
    IMPRESS_POWER_2(44, 2, "1.2%", 2, 1),
    IMPRESS_POWER_3(44, 3, "1.48%", 3, 1),
    IMPRESS_POWER_4(44, 4, "2.15%", 4, 1),
    IMPRESS_POWER_5(44, 5, "2.16%", 1, 2),
    IMPRESS_POWER_6(44, 6, "2.76%", 2, 2),
    IMPRESS_POWER_7(44, 7, "3.36%", 3, 2),
    IMPRESS_POWER_8(44, 8, "3.6%", 1, 3),
    IMPRESS_POWER_9(44, 9, "4.6%", 2, 3),
    IMPRESS_POWER_10(44, 10, "4.8%", 4, 2),
    IMPRESS_POWER_11(44, 11, "5.6%", 3, 3),
    IMPRESS_POWER_12(44, 12, "8.0%", 4, 3),

    // 무기 공격력 %
    WEAPON_ATTACK_PERCENT(46, 1, "0.36%", 1, 1),
    WEAPON_ATTACK_PERCENT_2(46, 2, "0.46%", 2, 1),
    WEAPON_ATTACK_PERCENT_3(46, 3, "0.56%", 3, 1),
    WEAPON_ATTACK_PERCENT_4(46, 4, "0.8%", 4, 1),
    WEAPON_ATTACK_PERCENT_5(46, 5, "0.82%", 1, 2),
    WEAPON_ATTACK_PERCENT_6(46, 6, "1.04%", 2, 2),
    WEAPON_ATTACK_PERCENT_7(46, 7, "1.26%", 3, 2),
    WEAPON_ATTACK_PERCENT_8(46, 8, "1.36%", 1, 3),
    WEAPON_ATTACK_PERCENT_9(46, 9, "1.72%", 2, 3),
    WEAPON_ATTACK_PERCENT_10(46, 10, "1.8%", 4, 2),
    WEAPON_ATTACK_PERCENT_11(46, 11, "2.1%", 3, 3),
    WEAPON_ATTACK_PERCENT_12(46, 12, "3.0%", 4, 3),

    // 무기 공격력 +
    WEAPON_ATTACK_PLUS(54, 1, "23", 1, 1),
    WEAPON_ATTACK_PLUS_2(54, 2, "32", 2, 1),
    WEAPON_ATTACK_PLUS_3(54, 3, "50", 1, 2),
    WEAPON_ATTACK_PLUS_4(54, 4, "57", 3, 1),
    WEAPON_ATTACK_PLUS_5(54, 5, "75", 2, 2),
    WEAPON_ATTACK_PLUS_6(54, 6, "105", 1, 3),
    WEAPON_ATTACK_PLUS_7(54, 7, "147", 3, 2),
    WEAPON_ATTACK_PLUS_8(54, 8, "155", 2, 3),
    WEAPON_ATTACK_PLUS_9(54, 9, "195", 4, 1),
    WEAPON_ATTACK_PLUS_10(54, 10, "285", 3, 3),
    WEAPON_ATTACK_PLUS_11(54, 11, "480", 4, 2),
    WEAPON_ATTACK_PLUS_12(54, 12, "960", 4, 3),

    // 상태이상 공격 지속시간
    STATUS_ATTACK_DURATION(57, 1, "0.1%", 1, 1),
    STATUS_ATTACK_DURATION_2(57, 2, "0.15%", 2, 1),
    STATUS_ATTACK_DURATION_3(57, 3, "0.19%", 3, 1),
    STATUS_ATTACK_DURATION_4(57, 4, "0.2%", 4, 1),
    STATUS_ATTACK_DURATION_5(57, 5, "0.22%", 1, 2),
    STATUS_ATTACK_DURATION_6(57, 6, "0.35%", 2, 2),
    STATUS_ATTACK_DURATION_7(57, 7, "0.42%", 3, 2),
    STATUS_ATTACK_DURATION_8(57, 8, "0.45%", 1, 3),
    STATUS_ATTACK_DURATION_9(57, 9, "0.5%", 4, 2),
    STATUS_ATTACK_DURATION_10(57, 10, "0.58%", 2, 3),
    STATUS_ATTACK_DURATION_11(57, 11, "0.7%", 3, 3),
    STATUS_ATTACK_DURATION_12(57, 12, "1.0%", 4, 3),

    // 서폿 아이덴티티 획득량 증가
    SUP_IDENTITY_INCREASE(43, 1, "0.72%", 1, 1),
    SUP_IDENTITY_INCREASE_2(43, 2, "0.9%", 2, 1),
    SUP_IDENTITY_INCREASE_3(43, 3, "1.11%", 3, 1),
    SUP_IDENTITY_INCREASE_4(43, 4, "1.6%", 4, 1),
    SUP_IDENTITY_INCREASE_5(43, 5, "1.62%", 1, 2),
    SUP_IDENTITY_INCREASE_6(43, 6, "2.07%", 2, 2),
    SUP_IDENTITY_INCREASE_7(43, 7, "2.52%", 3, 2),
    SUP_IDENTITY_INCREASE_8(43, 8, "2.7%", 1, 3),
    SUP_IDENTITY_INCREASE_9(43, 9, "3.45%", 2, 3),
    SUP_IDENTITY_INCREASE_10(43, 10, "3.6%", 4, 2),
    SUP_IDENTITY_INCREASE_11(43, 11, "4.2%", 3, 3),
    SUP_IDENTITY_INCREASE_12(43, 12, "6.0%", 4, 3),

    // 아군 공격력 강화 효과
    SUP_TEAM_ATTACK_POINT_INCREASE(51, 1, "0.6%", 1, 1),
    SUP_TEAM_ATTACK_POINT_INCREASE_2(51, 2, "0.76%", 2, 1),
    SUP_TEAM_ATTACK_POINT_INCREASE_3(51, 3, "0.92%", 3, 1),
    SUP_TEAM_ATTACK_POINT_INCREASE_4(51, 4, "1.35%", 4, 1),
    SUP_TEAM_ATTACK_POINT_INCREASE_5(51, 5, "1.36%", 1, 2),
    SUP_TEAM_ATTACK_POINT_INCREASE_6(51, 6, "1.72%", 2, 2),
    SUP_TEAM_ATTACK_POINT_INCREASE_7(51, 7, "2.12%", 3, 2),
    SUP_TEAM_ATTACK_POINT_INCREASE_8(51, 8, "2.24%", 1, 3),
    SUP_TEAM_ATTACK_POINT_INCREASE_9(51, 9, "2.88%", 2, 3),
    SUP_TEAM_ATTACK_POINT_INCREASE_10(51, 10, "3.0%", 4, 2),
    SUP_TEAM_ATTACK_POINT_INCREASE_11(51, 11, "3.52%", 3, 3),
    SUP_TEAM_ATTACK_POINT_INCREASE_12(51, 12, "5.0%", 4, 3),

    //아군 피해량 강화 효과
    SUP_TEAM_DAMAGE_POINT_INCREASE(52, 1, "0.9%", 1, 1),
    SUP_TEAM_DAMAGE_POINT_INCREASE_2(52, 2, "1.14%", 2, 1),
    SUP_TEAM_DAMAGE_POINT_INCREASE_3(52, 3, "1.38%", 3, 1),
    SUP_TEAM_DAMAGE_POINT_INCREASE_4(52, 4, "2.0%", 4, 1),
    SUP_TEAM_DAMAGE_POINT_INCREASE_5(52, 5, "2.04%", 1, 2),
    SUP_TEAM_DAMAGE_POINT_INCREASE_6(52, 6, "2.58%", 2, 2),
    SUP_TEAM_DAMAGE_POINT_INCREASE_7(52, 7, "3.18%", 3, 2),
    SUP_TEAM_DAMAGE_POINT_INCREASE_8(52, 8, "3.36%", 1, 3),
    SUP_TEAM_DAMAGE_POINT_INCREASE_9(52, 9, "4.32%", 2, 3),
    SUP_TEAM_DAMAGE_POINT_INCREASE_10(52, 10, "4.5%", 4, 2),
    SUP_TEAM_DAMAGE_POINT_INCREASE_11(52, 11, "5.28%", 3, 3),
    SUP_TEAM_DAMAGE_POINT_INCREASE_12(52, 12, "7.5%", 4, 3),

    // 적에게 주는 피해
    DAMAGE_TO_ENEMY(42, 1, "0.24%", 1, 1),
    DAMAGE_TO_ENEMY_2(42, 2, "0.3%", 2, 1),
    DAMAGE_TO_ENEMY_3(42, 3, "0.37%", 3, 1),
    DAMAGE_TO_ENEMY_4(42, 4, "0.54%", 1, 2),
    DAMAGE_TO_ENEMY_5(42, 5, "0.55%", 4, 1),
    DAMAGE_TO_ENEMY_6(42, 6, "0.69%", 2, 2),
    DAMAGE_TO_ENEMY_7(42, 7, "0.84%", 3, 2),
    DAMAGE_TO_ENEMY_8(42, 8, "0.9%", 1, 3),
    DAMAGE_TO_ENEMY_9(42, 9, "1.15%", 2, 3),
    DAMAGE_TO_ENEMY_10(42, 10, "1.2%", 4, 2),
    DAMAGE_TO_ENEMY_11(42, 11, "1.4%", 3, 3),
    DAMAGE_TO_ENEMY_12(42, 12, "2.0%", 4, 3),

    //전투 중 생명력 회복량
    HP_RECOVERY_IN_BATTLE(58, 1, "3", 1, 1),
    HP_RECOVERY_IN_BATTLE_2(58, 2, "5", 2, 1),
    HP_RECOVERY_IN_BATTLE_3(58, 3, "6", 1, 2),
    HP_RECOVERY_IN_BATTLE_4(58, 4, "8", 3, 1),
    HP_RECOVERY_IN_BATTLE_5(58, 5, "10", 4, 1),
    HP_RECOVERY_IN_BATTLE_6(58, 6, "12", 1, 3),
    HP_RECOVERY_IN_BATTLE_7(58, 7, "13", 2, 2),
    HP_RECOVERY_IN_BATTLE_8(58, 8, "17", 3, 2),
    HP_RECOVERY_IN_BATTLE_9(58, 9, "21", 2, 3),
    HP_RECOVERY_IN_BATTLE_10(58, 10, "25", 4, 2),
    HP_RECOVERY_IN_BATTLE_11(58, 11, "28", 3, 3),
    HP_RECOVERY_IN_BATTLE_12(58, 12, "50", 4, 3),

    //최대 마나
    MAX_MANA(56, 1, "3", 1, 2),
    MAX_MANA_2(56, 2, "5", 2, 1),
    MAX_MANA_3(56, 3, "6", 1, 2),
    MAX_MANA_4(56, 3, "6", 3, 1),
    MAX_MANA_5(56, 3, "6", 4, 1),
    MAX_MANA_6(56, 4, "10", 2, 2),
    MAX_MANA_7(56, 5, "13", 3, 2),
    MAX_MANA_8(56, 6, "14", 1, 3),
    MAX_MANA_9(56, 7, "15", 4, 2),
    MAX_MANA_10(56, 8, "17", 2, 3),
    MAX_MANA_11(56, 9, "21", 3, 3),
    MAX_MANA_12(56, 10, "30", 4, 3),

    //최대 생명력
    MAX_HP(55, 1, "431", 1, 1),
    MAX_HP_2(55, 2, "719", 2, 1),
    MAX_HP_3(55, 3, "970", 1, 2),
    MAX_HP_4(55, 4, "992", 3, 1),
    MAX_HP_5(55, 5, "1300", 4, 1),
    MAX_HP_6(55, 6, "1617", 1, 3),
    MAX_HP_7(55, 7, "1654", 2, 2),
    MAX_HP_8(55, 8, "2253", 3, 2),
    MAX_HP_9(55, 9, "2756", 2, 3),
    MAX_HP_10(55, 10, "3250", 4, 2),
    MAX_HP_11(55, 11, "3755", 3, 3),
    MAX_HP_12(55, 12, "6500", 4, 3),

    //추가 피해
    ADDITIONAL_DAMAGE(41, 1, "0.31%", 1, 1),
    ADDITIONAL_DAMAGE_2(41, 2, "0.39%", 2, 1),
    ADDITIONAL_DAMAGE_3(41, 3, "0.48%", 3, 1),
    ADDITIONAL_DAMAGE_4(41, 4, "0.7%", 1, 2),
    ADDITIONAL_DAMAGE_5(41, 4, "0.7%", 4, 1),
    ADDITIONAL_DAMAGE_6(41, 5, "0.9%", 2, 2),
    ADDITIONAL_DAMAGE_7(41, 6, "1.09%", 3, 2),
    ADDITIONAL_DAMAGE_8(41, 7, "1.17%", 1, 3),
    ADDITIONAL_DAMAGE_9(41, 8, "1.5%", 2, 3),
    ADDITIONAL_DAMAGE_10(41, 9, "1.6%", 4, 2),
    ADDITIONAL_DAMAGE_11(41, 10, "1.82%", 3, 3),
    ADDITIONAL_DAMAGE_12(41, 11, "2.6%", 4, 3),

    //치명타 적중률
    CRITICAL_HIT_RATE(49, 1, "0.19%", 1, 1),
    CRITICAL_HIT_RATE_2(49, 2, "0.24%", 2, 1),
    CRITICAL_HIT_RATE_3(49, 3, "0.29%", 3, 1),
    CRITICAL_HIT_RATE_4(49, 4, "0.4%", 4, 1),
    CRITICAL_HIT_RATE_5(49, 5, "0.42%", 1, 2),
    CRITICAL_HIT_RATE_6(49, 6, "0.54%", 2, 2),
    CRITICAL_HIT_RATE_7(49, 7, "0.66%", 3, 2),
    CRITICAL_HIT_RATE_8(49, 8, "0.7%", 1, 3),
    CRITICAL_HIT_RATE_9(49, 9, "0.89%", 2, 3),
    CRITICAL_HIT_RATE_10(49, 10, "0.95%", 4, 2),
    CRITICAL_HIT_RATE_11(49, 11, "1.09%", 3, 3),
    CRITICAL_HIT_RATE_12(49, 12, "1.55%", 4, 3),

    //치명타 피해
    CRITICAL_DAMAGE(50, 1, "0.48%", 1, 1),
    CRITICAL_DAMAGE_2(50, 2, "0.61%", 2, 1),
    CRITICAL_DAMAGE_3(50, 3, "0.74%", 3, 1),
    CRITICAL_DAMAGE_4(50, 4, "1.09%", 1, 2),
    CRITICAL_DAMAGE_5(50, 5, "1.1%", 4, 1),
    CRITICAL_DAMAGE_6(50, 6, "1.38%", 2, 2),
    CRITICAL_DAMAGE_7(50, 7, "1.7%", 3, 2),
    CRITICAL_DAMAGE_8(50, 8, "1.79%", 1, 3),
    CRITICAL_DAMAGE_9(50, 9, "2.3%", 2, 3),
    CRITICAL_DAMAGE_10(50, 10, "2.4%", 4, 2),
    CRITICAL_DAMAGE_11(50, 11, "2.82%", 3, 3),
    CRITICAL_DAMAGE_12(50, 12, "4.0%", 4, 3),

    //파티원 보호막 효과
    PARTY_SHIELD_EFFECT(48, 1, "0.42%", 1, 1),
    PARTY_SHIELD_EFFECT_2(48, 2, "0.54%", 2, 1),
    PARTY_SHIELD_EFFECT_3(48, 3, "0.65%", 3, 1),
    PARTY_SHIELD_EFFECT_4(48, 4, "0.95%", 4, 1),
    PARTY_SHIELD_EFFECT_5(48, 5, "0.96%", 1, 2),
    PARTY_SHIELD_EFFECT_6(48, 6, "1.21%", 2, 2),
    PARTY_SHIELD_EFFECT_7(48, 7, "1.47%", 3, 2),
    PARTY_SHIELD_EFFECT_8(48, 8, "1.59%", 1, 3),
    PARTY_SHIELD_EFFECT_9(48, 9, "2.01%", 2, 3),
    PARTY_SHIELD_EFFECT_10(48, 10, "2.1%", 4, 2),
    PARTY_SHIELD_EFFECT_11(48, 11, "2.45%", 3, 3),
    PARTY_SHIELD_EFFECT_12(48, 12, "3.5%", 4, 3),

    //파티원 회복 효과
    PARTY_HEAL_EFFECT(47, 1, "0.42%", 1, 1),
    PARTY_HEAL_EFFECT_2(47, 2, "0.54%", 2, 1),
    PARTY_HEAL_EFFECT_3(47, 3, "0.65%", 3, 1),
    PARTY_HEAL_EFFECT_4(47, 4, "0.95%", 4, 1),
    PARTY_HEAL_EFFECT_5(47, 5, "0.96%", 1, 2),
    PARTY_HEAL_EFFECT_6(47, 6, "1.21%", 2, 2),
    PARTY_HEAL_EFFECT_7(47, 7, "1.47%", 3, 2),
    PARTY_HEAL_EFFECT_8(47, 8, "1.59%", 1, 3),
    PARTY_HEAL_EFFECT_9(47, 9, "2.01%", 2, 3),
    PARTY_HEAL_EFFECT_10(47, 10, "2.1%", 4, 2),
    PARTY_HEAL_EFFECT_11(47, 11, "2.45%", 3, 3),
    PARTY_HEAL_EFFECT_12(47, 12, "3.5%", 4, 3),

    NULL_OPTION(0,0,"",0, 0);

    private final int option;
    private final int value;
    private final String displayValue;
    private final int tier;
    private final int valueLevel;

    public static OptionValueEnum getByOptionTierValueLevel(int option, int tier, int valueLevel) {
        for (OptionValueEnum item : OptionValueEnum.values()) {
            if (item.getOption() == option && item.getTier() == tier && item.getValueLevel() == valueLevel) {
                return item; // 일치하는 항목 반환
            }
        }
        return NULL_OPTION; // 일치하는 항목이 없으면 null 반환
    }

    public static OptionValueEnum getByDisplayValue(int option, String displayValue, int tier) {
        for (OptionValueEnum item : OptionValueEnum.values()) {
            if (item.getOption() == option && item.getDisplayValue().equals(displayValue) && item.getTier() == tier ) {
                return item; // 일치하는 항목 반환
            }
        }
        return NULL_OPTION; // 일치하는 항목이 없으면 null 반환
    }
}

