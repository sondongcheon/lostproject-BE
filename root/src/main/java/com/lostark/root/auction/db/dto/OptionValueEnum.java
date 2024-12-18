package com.lostark.root.auction.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OptionValueEnum {

    // 공격력 %
    ATTACK_PERCENT(45, 1, "0.19", 0, 0),
    ATTACK_PERCENT_2(45, 2, "0.24", 0, 0),
    ATTACK_PERCENT_3(45, 3, "0.29", 0, 0),
    ATTACK_PERCENT_4(45, 4, "0.40", 4, 1),
    ATTACK_PERCENT_5(45, 5, "0.42", 0, 0),
    ATTACK_PERCENT_6(45, 6, "0.54", 0, 0),
    ATTACK_PERCENT_7(45, 7, "0.66", 0, 0),
    ATTACK_PERCENT_8(45, 8, "0.70", 0, 0),
    ATTACK_PERCENT_9(45, 9, "0.89", 0, 0),
    ATTACK_PERCENT_10(45, 10, "0.95", 4, 2),
    ATTACK_PERCENT_11(45, 11, "1.09", 0, 0),
    ATTACK_PERCENT_12(45, 12, "1.55", 4, 3),

    // 공격력 +
    ATTACK_PLUS(53, 1, "9", 0, 0),
    ATTACK_PLUS_2(53, 2, "14", 0, 0),
    ATTACK_PLUS_3(53, 3, "19", 0, 0),
    ATTACK_PLUS_4(53, 4, "24", 0, 0),
    ATTACK_PLUS_5(53, 5, "33", 0, 0),
    ATTACK_PLUS_6(53, 6, "40", 0, 0),
    ATTACK_PLUS_7(53, 7, "61", 0, 0),
    ATTACK_PLUS_8(53, 8, "68", 0, 0),
    ATTACK_PLUS_9(53, 9, "80", 4, 1),
    ATTACK_PLUS_10(53, 10, "118", 0, 0),
    ATTACK_PLUS_11(53, 11, "195", 4, 2),
    ATTACK_PLUS_12(53, 12, "390", 4, 3),

    // 낙인력
    IMPRESS_POWER(44, 1, "0.96", 0, 0),
    IMPRESS_POWER_2(44, 2, "1.20", 0, 0),
    IMPRESS_POWER_3(44, 3, "1.48", 0, 0),
    IMPRESS_POWER_4(44, 4, "2.15", 4, 1),
    IMPRESS_POWER_5(44, 5, "2.16", 0, 0),
    IMPRESS_POWER_6(44, 6, "2.76", 0, 0),
    IMPRESS_POWER_7(44, 7, "3.36", 0, 0),
    IMPRESS_POWER_8(44, 8, "3.60", 0, 0),
    IMPRESS_POWER_9(44, 9, "4.60", 0, 0),
    IMPRESS_POWER_10(44, 10, "4.80", 4, 2),
    IMPRESS_POWER_11(44, 11, "5.60", 0, 0),
    IMPRESS_POWER_12(44, 12, "8.00", 4, 3),

    // 무기 공격력 %
    WEAPON_ATTACK_PERCENT(46, 1, "0.36", 0, 0),
    WEAPON_ATTACK_PERCENT_2(46, 2, "0.46", 0, 0),
    WEAPON_ATTACK_PERCENT_3(46, 3, "0.56", 0, 0),
    WEAPON_ATTACK_PERCENT_4(46, 4, "0.80", 4, 1),
    WEAPON_ATTACK_PERCENT_5(46, 5, "0.82", 0, 0),
    WEAPON_ATTACK_PERCENT_6(46, 6, "1.04", 0, 0),
    WEAPON_ATTACK_PERCENT_7(46, 7, "1.26", 0, 0),
    WEAPON_ATTACK_PERCENT_8(46, 8, "1.36", 0, 0),
    WEAPON_ATTACK_PERCENT_9(46, 9, "1.72", 0, 0),
    WEAPON_ATTACK_PERCENT_10(46, 10, "1.80", 4, 2),
    WEAPON_ATTACK_PERCENT_11(46, 11, "2.10", 0, 0),
    WEAPON_ATTACK_PERCENT_12(46, 12, "3.00", 4, 3),

    // 무기 공격력 +
    WEAPON_ATTACK_PLUS(54, 1, "23", 0, 0),
    WEAPON_ATTACK_PLUS_2(54, 2, "32", 0, 0),
    WEAPON_ATTACK_PLUS_3(54, 3, "50", 0, 0),
    WEAPON_ATTACK_PLUS_4(54, 4, "57", 0, 0),
    WEAPON_ATTACK_PLUS_5(54, 5, "75", 0, 0),
    WEAPON_ATTACK_PLUS_6(54, 6, "105", 0, 0),
    WEAPON_ATTACK_PLUS_7(54, 7, "147", 0, 0),
    WEAPON_ATTACK_PLUS_8(54, 8, "155", 0, 0),
    WEAPON_ATTACK_PLUS_9(54, 9, "195", 4, 1),
    WEAPON_ATTACK_PLUS_10(54, 10, "285", 0, 0),
    WEAPON_ATTACK_PLUS_11(54, 11, "480", 4, 2),
    WEAPON_ATTACK_PLUS_12(54, 12, "960", 4, 3),

    // 상태이상 공격 지속시간
    STATUS_ATTACK_DURATION(57, 1, "0.10", 0, 0),
    STATUS_ATTACK_DURATION_2(57, 2, "0.15", 0, 0),
    STATUS_ATTACK_DURATION_3(57, 3, "0.19", 0, 0),
    STATUS_ATTACK_DURATION_4(57, 4, "0.20", 4, 1),
    STATUS_ATTACK_DURATION_5(57, 5, "0.22", 0, 0),
    STATUS_ATTACK_DURATION_6(57, 6, "0.35", 0, 0),
    STATUS_ATTACK_DURATION_7(57, 7, "0.42", 0, 0),
    STATUS_ATTACK_DURATION_8(57, 8, "0.45", 0, 0),
    STATUS_ATTACK_DURATION_9(57, 9, "0.50", 4, 2),
    STATUS_ATTACK_DURATION_10(57, 10, "0.58", 0, 0),
    STATUS_ATTACK_DURATION_11(57, 11, "0.70", 0, 0),
    STATUS_ATTACK_DURATION_12(57, 12, "1.00", 4, 3),

    // 서폿 아이덴티티 획득량 증가
    SUP_IDENTITY_INCREASE(43, 1, "0.72%", 0, 0),
    SUP_IDENTITY_INCREASE_2(43, 2, "0.90%", 0, 0),
    SUP_IDENTITY_INCREASE_3(43, 3, "1.11%", 0, 0),
    SUP_IDENTITY_INCREASE_4(43, 4, "1.60%", 4, 1),
    SUP_IDENTITY_INCREASE_5(43, 5, "1.62%", 0, 0),
    SUP_IDENTITY_INCREASE_6(43, 6, "2.07%", 0, 0),
    SUP_IDENTITY_INCREASE_7(43, 7, "2.52%", 0, 0),
    SUP_IDENTITY_INCREASE_8(43, 8, "2.70%", 0, 0),
    SUP_IDENTITY_INCREASE_9(43, 9, "3.45%", 0, 0),
    SUP_IDENTITY_INCREASE_10(43, 10, "3.60%", 4, 2),
    SUP_IDENTITY_INCREASE_11(43, 11, "4.20%", 0, 0),
    SUP_IDENTITY_INCREASE_12(43, 12, "6.00%", 4, 3),

    // 아군 공격력 강화 효과
    SUP_TEAM_ATTACK_POINT_INCREASE(51, 1, "0.60%", 0, 0),
    SUP_TEAM_ATTACK_POINT_INCREASE_2(51, 2, "0.76%", 0, 0),
    SUP_TEAM_ATTACK_POINT_INCREASE_3(51, 3, "0.92%", 0, 0),
    SUP_TEAM_ATTACK_POINT_INCREASE_4(51, 4, "1.35%", 4, 1),
    SUP_TEAM_ATTACK_POINT_INCREASE_5(51, 5, "1.36%", 0, 0),
    SUP_TEAM_ATTACK_POINT_INCREASE_6(51, 6, "1.72%", 0, 0),
    SUP_TEAM_ATTACK_POINT_INCREASE_7(51, 7, "2.12%", 0, 0),
    SUP_TEAM_ATTACK_POINT_INCREASE_8(51, 8, "2.24%", 0, 0),
    SUP_TEAM_ATTACK_POINT_INCREASE_9(51, 9, "2.88%", 0, 0),
    SUP_TEAM_ATTACK_POINT_INCREASE_10(51, 10, "3.00%", 4, 2),
    SUP_TEAM_ATTACK_POINT_INCREASE_11(51, 11, "3.52%", 0, 0),
    SUP_TEAM_ATTACK_POINT_INCREASE_12(51, 12, "5.00%", 4, 3),
    
    //아군 피해량 강화 효과
    SUP_TEAM_DAMAGE_POINT_INCREASE(52, 1, "0.90%", 0, 0),
    SUP_TEAM_DAMAGE_POINT_INCREASE_2(52, 2, "1.14%", 0, 0),
    SUP_TEAM_DAMAGE_POINT_INCREASE_3(52, 3, "1.38%", 0, 0),
    SUP_TEAM_DAMAGE_POINT_INCREASE_4(52, 4, "2.00%", 4, 1),
    SUP_TEAM_DAMAGE_POINT_INCREASE_5(52, 5, "2.04%", 0, 0),
    SUP_TEAM_DAMAGE_POINT_INCREASE_6(52, 6, "2.58%", 0, 0),
    SUP_TEAM_DAMAGE_POINT_INCREASE_7(52, 7, "3.18%", 0, 0),
    SUP_TEAM_DAMAGE_POINT_INCREASE_8(52, 8, "3.36%", 0, 0),
    SUP_TEAM_DAMAGE_POINT_INCREASE_9(52, 9, "4.32%", 0, 0),
    SUP_TEAM_DAMAGE_POINT_INCREASE_10(52, 10, "4.50%", 4, 2),
    SUP_TEAM_DAMAGE_POINT_INCREASE_11(52, 11, "5.28%", 0, 0),
    SUP_TEAM_DAMAGE_POINT_INCREASE_12(52, 12, "7.50%", 4, 3),

    // 적에게 주는 피해
    DAMAGE_TO_ENEMY(42, 1, "0.24", 0, 0),
    DAMAGE_TO_ENEMY_2(42, 2, "0.30", 0, 0),
    DAMAGE_TO_ENEMY_3(42, 3, "0.37", 0, 0),
    DAMAGE_TO_ENEMY_4(42, 4, "0.54", 0, 0),
    DAMAGE_TO_ENEMY_5(42, 5, "0.55", 4, 1),
    DAMAGE_TO_ENEMY_6(42, 6, "0.69", 0, 0),
    DAMAGE_TO_ENEMY_7(42, 7, "0.84", 0, 0),
    DAMAGE_TO_ENEMY_8(42, 8, "0.90", 0, 0),
    DAMAGE_TO_ENEMY_9(42, 9, "1.15", 0, 0),
    DAMAGE_TO_ENEMY_10(42, 10, "1.20", 4, 2),
    DAMAGE_TO_ENEMY_11(42, 11, "1.40", 0, 0),
    DAMAGE_TO_ENEMY_12(42, 12, "2.00", 4, 3),

    //전투 중 생명력 회복량
    HP_RECOVERY_IN_BATTLE(58, 1, "3", 0, 0),
    HP_RECOVERY_IN_BATTLE_2(58, 2, "5", 0, 0),
    HP_RECOVERY_IN_BATTLE_3(58, 3, "6", 0, 0),
    HP_RECOVERY_IN_BATTLE_4(58, 4, "8", 0, 0),
    HP_RECOVERY_IN_BATTLE_5(58, 5, "10", 4, 1),
    HP_RECOVERY_IN_BATTLE_6(58, 6, "12", 0, 0),
    HP_RECOVERY_IN_BATTLE_7(58, 7, "13", 0, 0),
    HP_RECOVERY_IN_BATTLE_8(58, 8, "17", 0, 0),
    HP_RECOVERY_IN_BATTLE_9(58, 9, "21", 0, 0),
    HP_RECOVERY_IN_BATTLE_10(58, 10, "25", 4, 2),
    HP_RECOVERY_IN_BATTLE_11(58, 11, "28", 0, 0),
    HP_RECOVERY_IN_BATTLE_12(58, 12, "50", 4, 3),

    //최대 마나
    MAX_MANA(56, 1, "3", 0, 0),
    MAX_MANA_2(56, 2, "5", 0, 0),
    MAX_MANA_3(56, 3, "6", 4, 1),
    MAX_MANA_4(56, 4, "10", 0, 0),
    MAX_MANA_5(56, 5, "13", 0, 0),
    MAX_MANA_6(56, 6, "14", 0, 0),
    MAX_MANA_7(56, 7, "15", 4, 2),
    MAX_MANA_8(56, 8, "17", 0, 0),
    MAX_MANA_9(56, 9, "21", 0, 0),
    MAX_MANA_10(56, 10, "30", 4, 3),

    //최대 생명력
    MAX_HP(55, 1, "431", 0, 0),
    MAX_HP_2(55, 2, "719", 0, 0),
    MAX_HP_3(55, 3, "970", 0, 0),
    MAX_HP_4(55, 4, "992", 0, 0),
    MAX_HP_5(55, 5, "1300", 4, 1),
    MAX_HP_6(55, 6, "1617", 0, 0),
    MAX_HP_7(55, 7, "1654", 0, 0),
    MAX_HP_8(55, 8, "2253", 0, 0),
    MAX_HP_9(55, 9, "2756", 0, 0),
    MAX_HP_10(55, 10, "3250", 4, 2),
    MAX_HP_11(55, 11, "3755", 0, 0),
    MAX_HP_12(55, 12, "6500", 4, 3),

    //추가 피해
    ADDITIONAL_DAMAGE(41, 1, "0.31%", 0, 0),
    ADDITIONAL_DAMAGE_2(41, 2, "0.39%", 0, 0),
    ADDITIONAL_DAMAGE_3(41, 3, "0.48%", 0, 0),
    ADDITIONAL_DAMAGE_4(41, 4, "0.70%", 4, 1),
    ADDITIONAL_DAMAGE_5(41, 5, "0.90%", 0, 0),
    ADDITIONAL_DAMAGE_6(41, 6, "1.09%", 0, 0),
    ADDITIONAL_DAMAGE_7(41, 7, "1.17%", 0, 0),
    ADDITIONAL_DAMAGE_8(41, 8, "1.50%", 0, 0),
    ADDITIONAL_DAMAGE_9(41, 9, "1.60%", 4, 2),
    ADDITIONAL_DAMAGE_10(41, 10, "1.82%", 0, 0),
    ADDITIONAL_DAMAGE_11(41, 11, "2.60%", 4, 3),

    //치명타 적중률
    CRITICAL_HIT_RATE(49, 1, "0.19%", 0, 0),
    CRITICAL_HIT_RATE_2(49, 2, "0.24%", 0, 0),
    CRITICAL_HIT_RATE_3(49, 3, "0.29%", 0, 0),
    CRITICAL_HIT_RATE_4(49, 4, "0.40%", 4, 1),
    CRITICAL_HIT_RATE_5(49, 5, "0.42%", 0, 0),
    CRITICAL_HIT_RATE_6(49, 6, "0.54%", 0, 0),
    CRITICAL_HIT_RATE_7(49, 7, "0.66%", 0, 0),
    CRITICAL_HIT_RATE_8(49, 8, "0.70%", 0, 0),
    CRITICAL_HIT_RATE_9(49, 9, "0.89%", 0, 0),
    CRITICAL_HIT_RATE_10(49, 10, "0.95%", 4, 2),
    CRITICAL_HIT_RATE_11(49, 11, "1.09%", 0, 0),
    CRITICAL_HIT_RATE_12(49, 12, "1.55%", 4, 3),

    //치명타 피해
    CRITICAL_DAMAGE(50, 1, "0.48%", 0, 0),
    CRITICAL_DAMAGE_2(50, 2, "0.61%", 0, 0),
    CRITICAL_DAMAGE_3(50, 3, "0.74%", 0, 0),
    CRITICAL_DAMAGE_4(50, 4, "1.09%", 0, 0),
    CRITICAL_DAMAGE_5(50, 5, "1.10%", 4, 1),
    CRITICAL_DAMAGE_6(50, 6, "1.38%", 0, 0),
    CRITICAL_DAMAGE_7(50, 7, "1.70%", 0, 0),
    CRITICAL_DAMAGE_8(50, 8, "1.79%", 0, 0),
    CRITICAL_DAMAGE_9(50, 9, "2.30%", 0, 0),
    CRITICAL_DAMAGE_10(50, 10, "2.40%", 4, 2),
    CRITICAL_DAMAGE_11(50, 11, "2.82%", 0, 0),
    CRITICAL_DAMAGE_12(50, 12, "4.00%", 4, 3),

    //파티원 보호막 효과
    PARTY_SHIELD_EFFECT(48, 1, "0.42%", 0, 0),
    PARTY_SHIELD_EFFECT_2(48, 2, "0.54%", 0, 0),
    PARTY_SHIELD_EFFECT_3(48, 3, "0.65%", 0, 0),
    PARTY_SHIELD_EFFECT_4(48, 4, "0.95%", 4, 1),
    PARTY_SHIELD_EFFECT_5(48, 5, "0.96%", 0, 0),
    PARTY_SHIELD_EFFECT_6(48, 6, "1.21%", 0, 0),
    PARTY_SHIELD_EFFECT_7(48, 7, "1.47%", 0, 0),
    PARTY_SHIELD_EFFECT_8(48, 8, "1.59%", 0, 0),
    PARTY_SHIELD_EFFECT_9(48, 9, "2.01%", 0, 0),
    PARTY_SHIELD_EFFECT_10(48, 10, "2.10%", 4, 2),
    PARTY_SHIELD_EFFECT_11(48, 11, "2.45%", 0, 0),
    PARTY_SHIELD_EFFECT_12(48, 12, "3.50%", 4, 3),

    //파티원 회복 효과
    PARTY_HEAL_EFFECT(47, 1, "0.42%", 0, 0),
    PARTY_HEAL_EFFECT_2(47, 2, "0.54%", 0, 0),
    PARTY_HEAL_EFFECT_3(47, 3, "0.65%", 0, 0),
    PARTY_HEAL_EFFECT_4(47, 4, "0.95%", 4, 1),
    PARTY_HEAL_EFFECT_5(47, 5, "0.96%", 0, 0),
    PARTY_HEAL_EFFECT_6(47, 6, "1.21%", 0, 0),
    PARTY_HEAL_EFFECT_7(47, 7, "1.47%", 0, 0),
    PARTY_HEAL_EFFECT_8(47, 8, "1.59%", 0, 0),
    PARTY_HEAL_EFFECT_9(47, 9, "2.01%", 0, 0),
    PARTY_HEAL_EFFECT_10(47, 10, "2.10%", 4, 2),
    PARTY_HEAL_EFFECT_11(47, 11, "2.45%", 0, 0),
    PARTY_HEAL_EFFECT_12(47, 12, "3.50%", 4, 3),

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
}

