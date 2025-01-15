package com.lostark.root.auction.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OptionValueEnum {

    // 공격력%
    ATTACK_PERCENT(45, 19, "0.19%", 1, 1),
    ATTACK_PERCENT_2(45, 24, "0.24%", 2, 1),
    ATTACK_PERCENT_3(45, 29, "0.29%", 3, 1),
    ATTACK_PERCENT_4(45, 40, "0.4%", 4, 1),
    ATTACK_PERCENT_5(45, 42, "0.42%", 1, 2),
    ATTACK_PERCENT_6(45, 54, "0.54%", 2, 2),
    ATTACK_PERCENT_7(45, 66, "0.66%", 3, 2),
    ATTACK_PERCENT_8(45, 70, "0.7%", 1, 3),
    ATTACK_PERCENT_9(45, 89, "0.89%", 2, 3),
    ATTACK_PERCENT_10(45, 95, "0.95%", 4, 2),
    ATTACK_PERCENT_11(45, 109, "1.09%", 3, 3),
    ATTACK_PERCENT_12(45, 155, "1.55%", 4, 3),

    // 공격력 +
    ATTACK_PLUS(53, 9, "9", 1, 1),
    ATTACK_PLUS_2(53, 14, "14", 2, 1),
    ATTACK_PLUS_3(53, 19, "19", 1, 2),
    ATTACK_PLUS_4(53, 24, "24", 3, 1),
    ATTACK_PLUS_5(53, 33, "33", 2, 2),
    ATTACK_PLUS_6(53, 40, "40", 1, 3),
    ATTACK_PLUS_7(53, 61, "61", 3, 2),
    ATTACK_PLUS_8(53, 68, "68", 2, 3),
    ATTACK_PLUS_9(53, 80, "80", 4, 1),
    ATTACK_PLUS_10(53, 118, "118", 3, 3),
    ATTACK_PLUS_11(53, 195, "195", 4, 2),
    ATTACK_PLUS_12(53, 390, "390", 4, 3),

    // 낙인력
    IMPRESS_POWER(44, 96, "0.96%", 1, 1),
    IMPRESS_POWER_2(44, 120, "1.2%", 2, 1),
    IMPRESS_POWER_3(44, 148, "1.48%", 3, 1),
    IMPRESS_POWER_4(44, 215, "2.15%", 4, 1),
    IMPRESS_POWER_5(44, 216, "2.16%", 1, 2),
    IMPRESS_POWER_6(44, 276, "2.76%", 2, 2),
    IMPRESS_POWER_7(44, 336, "3.36%", 3, 2),
    IMPRESS_POWER_8(44, 360, "3.6%", 1, 3),
    IMPRESS_POWER_9(44, 460, "4.6%", 2, 3),
    IMPRESS_POWER_10(44, 480, "4.8%", 4, 2),
    IMPRESS_POWER_11(44, 560, "5.6%", 3, 3),
    IMPRESS_POWER_12(44, 800, "8.0%", 4, 3),

    // 무기 공격력 %
    WEAPON_ATTACK_PERCENT(46, 36, "0.36%", 1, 1),
    WEAPON_ATTACK_PERCENT_2(46, 46, "0.46%", 2, 1),
    WEAPON_ATTACK_PERCENT_3(46, 56, "0.56%", 3, 1),
    WEAPON_ATTACK_PERCENT_4(46, 80, "0.8%", 4, 1),
    WEAPON_ATTACK_PERCENT_5(46, 82, "0.82%", 1, 2),
    WEAPON_ATTACK_PERCENT_6(46, 104, "1.04%", 2, 2),
    WEAPON_ATTACK_PERCENT_7(46, 126, "1.26%", 3, 2),
    WEAPON_ATTACK_PERCENT_8(46, 136, "1.36%", 1, 3),
    WEAPON_ATTACK_PERCENT_9(46, 172, "1.72%", 2, 3),
    WEAPON_ATTACK_PERCENT_10(46, 180, "1.8%", 4, 2),
    WEAPON_ATTACK_PERCENT_11(46, 210, "2.1%", 3, 3),
    WEAPON_ATTACK_PERCENT_12(46, 300, "3.0%", 4, 3),

    // 무기 공격력 +
    WEAPON_ATTACK_PLUS(54, 23, "23", 1, 1),
    WEAPON_ATTACK_PLUS_2(54, 32, "32", 2, 1),
    WEAPON_ATTACK_PLUS_3(54, 50, "50", 1, 2),
    WEAPON_ATTACK_PLUS_4(54, 57, "57", 3, 1),
    WEAPON_ATTACK_PLUS_5(54, 75, "75", 2, 2),
    WEAPON_ATTACK_PLUS_6(54, 105, "105", 1, 3),
    WEAPON_ATTACK_PLUS_7(54, 147, "147", 3, 2),
    WEAPON_ATTACK_PLUS_8(54, 155, "155", 2, 3),
    WEAPON_ATTACK_PLUS_9(54, 195, "195", 4, 1),
    WEAPON_ATTACK_PLUS_10(54, 285, "285", 3, 3),
    WEAPON_ATTACK_PLUS_11(54, 480, "480", 4, 2),
    WEAPON_ATTACK_PLUS_12(54, 960, "960", 4, 3),

    // 상태이상 공격 지속시간
    STATUS_ATTACK_DURATION(57, 10, "0.1%", 1, 1),
    STATUS_ATTACK_DURATION_2(57, 15, "0.15%", 2, 1),
    STATUS_ATTACK_DURATION_3(57, 19, "0.19%", 3, 1),
    STATUS_ATTACK_DURATION_4(57, 20, "0.2%", 4, 1),
    STATUS_ATTACK_DURATION_5(57, 22, "0.22%", 1, 2),
    STATUS_ATTACK_DURATION_6(57, 35, "0.35%", 2, 2),
    STATUS_ATTACK_DURATION_7(57, 42, "0.42%", 3, 2),
    STATUS_ATTACK_DURATION_8(57, 45, "0.45%", 1, 3),
    STATUS_ATTACK_DURATION_9(57, 50, "0.5%", 4, 2),
    STATUS_ATTACK_DURATION_10(57, 58, "0.58%", 2, 3),
    STATUS_ATTACK_DURATION_11(57, 70, "0.7%", 3, 3),
    STATUS_ATTACK_DURATION_12(57, 100, "1.0%", 4, 3),

    // 서폿 아이덴티티 획득량 증가
    SUP_IDENTITY_INCREASE(43, 72, "0.72%", 1, 1),
    SUP_IDENTITY_INCREASE_2(43, 90, "0.9%", 2, 1),
    SUP_IDENTITY_INCREASE_3(43, 111, "1.11%", 3, 1),
    SUP_IDENTITY_INCREASE_4(43, 160, "1.6%", 4, 1),
    SUP_IDENTITY_INCREASE_5(43, 162, "1.62%", 1, 2),
    SUP_IDENTITY_INCREASE_6(43, 207, "2.07%", 2, 2),
    SUP_IDENTITY_INCREASE_7(43, 252, "2.52%", 3, 2),
    SUP_IDENTITY_INCREASE_8(43, 270, "2.7%", 1, 3),
    SUP_IDENTITY_INCREASE_9(43, 345, "3.45%", 2, 3),
    SUP_IDENTITY_INCREASE_10(43, 360, "3.6%", 4, 2),
    SUP_IDENTITY_INCREASE_11(43, 420, "4.2%", 3, 3),
    SUP_IDENTITY_INCREASE_12(43, 600, "6.0%", 4, 3),

    // 아군 공격력 강화 효과
    SUP_TEAM_ATTACK_POINT_INCREASE(51, 60, "0.6%", 1, 1),
    SUP_TEAM_ATTACK_POINT_INCREASE_2(51, 76, "0.76%", 2, 1),
    SUP_TEAM_ATTACK_POINT_INCREASE_3(51, 92, "0.92%", 3, 1),
    SUP_TEAM_ATTACK_POINT_INCREASE_4(51, 135, "1.35%", 4, 1),
    SUP_TEAM_ATTACK_POINT_INCREASE_5(51, 136, "1.36%", 1, 2),
    SUP_TEAM_ATTACK_POINT_INCREASE_6(51, 172, "1.72%", 2, 2),
    SUP_TEAM_ATTACK_POINT_INCREASE_7(51, 212, "2.12%", 3, 2),
    SUP_TEAM_ATTACK_POINT_INCREASE_8(51, 224, "2.24%", 1, 3),
    SUP_TEAM_ATTACK_POINT_INCREASE_9(51, 288, "2.88%", 2, 3),
    SUP_TEAM_ATTACK_POINT_INCREASE_10(51, 300, "3.0%", 4, 2),
    SUP_TEAM_ATTACK_POINT_INCREASE_11(51, 352, "3.52%", 3, 3),
    SUP_TEAM_ATTACK_POINT_INCREASE_12(51, 500, "5.0%", 4, 3),

    //아군 피해량 강화 효과
    SUP_TEAM_DAMAGE_POINT_INCREASE(52, 90, "0.9%", 1, 1),
    SUP_TEAM_DAMAGE_POINT_INCREASE_2(52, 114, "1.14%", 2, 1),
    SUP_TEAM_DAMAGE_POINT_INCREASE_3(52, 138, "1.38%", 3, 1),
    SUP_TEAM_DAMAGE_POINT_INCREASE_4(52, 200, "2.0%", 4, 1),
    SUP_TEAM_DAMAGE_POINT_INCREASE_5(52, 204, "2.04%", 1, 2),
    SUP_TEAM_DAMAGE_POINT_INCREASE_6(52, 258, "2.58%", 2, 2),
    SUP_TEAM_DAMAGE_POINT_INCREASE_7(52, 318, "3.18%", 3, 2),
    SUP_TEAM_DAMAGE_POINT_INCREASE_8(52, 336, "3.36%", 1, 3),
    SUP_TEAM_DAMAGE_POINT_INCREASE_9(52, 432, "4.32%", 2, 3),
    SUP_TEAM_DAMAGE_POINT_INCREASE_10(52, 450, "4.5%", 4, 2),
    SUP_TEAM_DAMAGE_POINT_INCREASE_11(52, 528, "5.28%", 3, 3),
    SUP_TEAM_DAMAGE_POINT_INCREASE_12(52, 750, "7.5%", 4, 3),

    // 적에게 주는 피해
    DAMAGE_TO_ENEMY(42, 24, "0.24%", 1, 1),
    DAMAGE_TO_ENEMY_2(42, 30, "0.3%", 2, 1),
    DAMAGE_TO_ENEMY_3(42, 37, "0.37%", 3, 1),
    DAMAGE_TO_ENEMY_4(42, 54, "0.54%", 1, 2),
    DAMAGE_TO_ENEMY_5(42, 55, "0.55%", 4, 1),
    DAMAGE_TO_ENEMY_6(42, 69, "0.69%", 2, 2),
    DAMAGE_TO_ENEMY_7(42, 84, "0.84%", 3, 2),
    DAMAGE_TO_ENEMY_8(42, 90, "0.9%", 1, 3),
    DAMAGE_TO_ENEMY_9(42, 115, "1.15%", 2, 3),
    DAMAGE_TO_ENEMY_10(42, 120, "1.2%", 4, 2),
    DAMAGE_TO_ENEMY_11(42, 140, "1.4%", 3, 3),
    DAMAGE_TO_ENEMY_12(42, 200, "2.0%", 4, 3),

    //전투 중 생명력 회복량
    HP_RECOVERY_IN_BATTLE(58, 3, "3", 1, 1),
    HP_RECOVERY_IN_BATTLE_2(58, 5, "5", 2, 1),
    HP_RECOVERY_IN_BATTLE_3(58, 6, "6", 1, 2),
    HP_RECOVERY_IN_BATTLE_4(58, 8, "8", 3, 1),
    HP_RECOVERY_IN_BATTLE_5(58, 10, "10", 4, 1),
    HP_RECOVERY_IN_BATTLE_6(58, 12, "12", 1, 3),
    HP_RECOVERY_IN_BATTLE_7(58, 13, "13", 2, 2),
    HP_RECOVERY_IN_BATTLE_8(58, 17, "17", 3, 2),
    HP_RECOVERY_IN_BATTLE_9(58, 21, "21", 2, 3),
    HP_RECOVERY_IN_BATTLE_10(58, 25, "25", 4, 2),
    HP_RECOVERY_IN_BATTLE_11(58, 28, "28", 3, 3),
    HP_RECOVERY_IN_BATTLE_12(58, 50, "50", 4, 3),

    //최대 마나
    MAX_MANA(56, 3, "3", 1, 2),
    MAX_MANA_2(56, 5, "5", 2, 1),
    MAX_MANA_3(56, 6, "6", 1, 2),
    MAX_MANA_4(56, 6, "6", 3, 1),
    MAX_MANA_5(56, 6, "6", 4, 1),
    MAX_MANA_6(56, 10, "10", 2, 2),
    MAX_MANA_7(56, 13, "13", 3, 2),
    MAX_MANA_8(56, 14, "14", 1, 3),
    MAX_MANA_9(56, 15, "15", 4, 2),
    MAX_MANA_10(56, 17, "17", 2, 3),
    MAX_MANA_11(56, 21, "21", 3, 3),
    MAX_MANA_12(56, 30, "30", 4, 3),

    //최대 생명력
    MAX_HP(55, 431, "431", 1, 1),
    MAX_HP_2(55, 719, "719", 2, 1),
    MAX_HP_3(55, 970, "970", 1, 2),
    MAX_HP_4(55, 992, "992", 3, 1),
    MAX_HP_5(55, 1300, "1300", 4, 1),
    MAX_HP_6(55, 1617, "1617", 1, 3),
    MAX_HP_7(55, 1654, "1654", 2, 2),
    MAX_HP_8(55, 2253, "2253", 3, 2),
    MAX_HP_9(55, 2756, "2756", 2, 3),
    MAX_HP_10(55, 3250, "3250", 4, 2),
    MAX_HP_11(55, 3755, "3755", 3, 3),
    MAX_HP_12(55, 6500, "6500", 4, 3),

    //추가 피해
    ADDITIONAL_DAMAGE(41, 31, "0.31%", 1, 1),
    ADDITIONAL_DAMAGE_2(41, 39, "0.39%", 2, 1),
    ADDITIONAL_DAMAGE_3(41, 48, "0.48%", 3, 1),
    ADDITIONAL_DAMAGE_4(41, 70, "0.7%", 1, 2),
    ADDITIONAL_DAMAGE_5(41, 70, "0.7%", 4, 1),
    ADDITIONAL_DAMAGE_6(41, 90, "0.9%", 2, 2),
    ADDITIONAL_DAMAGE_7(41, 109, "1.09%", 3, 2),
    ADDITIONAL_DAMAGE_8(41, 117, "1.17%", 1, 3),
    ADDITIONAL_DAMAGE_9(41, 150, "1.5%", 2, 3),
    ADDITIONAL_DAMAGE_10(41, 160, "1.6%", 4, 2),
    ADDITIONAL_DAMAGE_11(41, 182, "1.82%", 3, 3),
    ADDITIONAL_DAMAGE_12(41, 260, "2.6%", 4, 3),

    //치명타 적중률
    CRITICAL_HIT_RATE(49, 19, "0.19%", 1, 1),
    CRITICAL_HIT_RATE_2(49, 24, "0.24%", 2, 1),
    CRITICAL_HIT_RATE_3(49, 29, "0.29%", 3, 1),
    CRITICAL_HIT_RATE_4(49, 40, "0.4%", 4, 1),
    CRITICAL_HIT_RATE_5(49, 42, "0.42%", 1, 2),
    CRITICAL_HIT_RATE_6(49, 54, "0.54%", 2, 2),
    CRITICAL_HIT_RATE_7(49, 66, "0.66%", 3, 2),
    CRITICAL_HIT_RATE_8(49, 70, "0.7%", 1, 3),
    CRITICAL_HIT_RATE_9(49, 89, "0.89%", 2, 3),
    CRITICAL_HIT_RATE_10(49, 95, "0.95%", 4, 2),
    CRITICAL_HIT_RATE_11(49, 109, "1.09%", 3, 3),
    CRITICAL_HIT_RATE_12(49, 155, "1.55%", 4, 3),

    //치명타 피해
    CRITICAL_DAMAGE(50, 48, "0.48%", 1, 1),
    CRITICAL_DAMAGE_2(50, 61, "0.61%", 2, 1),
    CRITICAL_DAMAGE_3(50, 74, "0.74%", 3, 1),
    CRITICAL_DAMAGE_4(50, 109, "1.09%", 1, 2),
    CRITICAL_DAMAGE_5(50, 110, "1.1%", 4, 1),
    CRITICAL_DAMAGE_6(50, 138, "1.38%", 2, 2),
    CRITICAL_DAMAGE_7(50, 170, "1.7%", 3, 2),
    CRITICAL_DAMAGE_8(50, 179, "1.79%", 1, 3),
    CRITICAL_DAMAGE_9(50, 230, "2.3%", 2, 3),
    CRITICAL_DAMAGE_10(50, 240, "2.4%", 4, 2),
    CRITICAL_DAMAGE_11(50, 282, "2.82%", 3, 3),
    CRITICAL_DAMAGE_12(50, 400, "4.0%", 4, 3),

    //파티원 보호막 효과
    PARTY_SHIELD_EFFECT(48, 42, "0.42%", 1, 1),
    PARTY_SHIELD_EFFECT_2(48, 54, "0.54%", 2, 1),
    PARTY_SHIELD_EFFECT_3(48, 65, "0.65%", 3, 1),
    PARTY_SHIELD_EFFECT_4(48, 95, "0.95%", 4, 1),
    PARTY_SHIELD_EFFECT_5(48, 96, "0.96%", 1, 2),
    PARTY_SHIELD_EFFECT_6(48, 121, "1.21%", 2, 2),
    PARTY_SHIELD_EFFECT_7(48, 147, "1.47%", 3, 2),
    PARTY_SHIELD_EFFECT_8(48, 159, "1.59%", 1, 3),
    PARTY_SHIELD_EFFECT_9(48, 201, "2.01%", 2, 3),
    PARTY_SHIELD_EFFECT_10(48, 210, "2.1%", 4, 2),
    PARTY_SHIELD_EFFECT_11(48, 245, "2.45%", 3, 3),
    PARTY_SHIELD_EFFECT_12(48, 350, "3.5%", 4, 3),

    //파티원 회복 효과
    PARTY_HEAL_EFFECT(47, 42, "0.42%", 1, 1),
    PARTY_HEAL_EFFECT_2(47, 54, "0.54%", 2, 1),
    PARTY_HEAL_EFFECT_3(47, 65, "0.65%", 3, 1),
    PARTY_HEAL_EFFECT_4(47, 95, "0.95%", 4, 1),
    PARTY_HEAL_EFFECT_5(47, 96, "0.96%", 1, 2),
    PARTY_HEAL_EFFECT_6(47, 121, "1.21%", 2, 2),
    PARTY_HEAL_EFFECT_7(47, 147, "1.47%", 3, 2),
    PARTY_HEAL_EFFECT_8(47, 159, "1.59%", 1, 3),
    PARTY_HEAL_EFFECT_9(47, 201, "2.01%", 2, 3),
    PARTY_HEAL_EFFECT_10(47, 210, "2.1%", 4, 2),
    PARTY_HEAL_EFFECT_11(47, 245, "2.45%", 3, 3),
    PARTY_HEAL_EFFECT_12(47, 350, "3.5%", 4, 3),

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

