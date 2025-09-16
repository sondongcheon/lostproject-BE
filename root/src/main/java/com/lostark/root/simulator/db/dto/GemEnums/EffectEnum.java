package com.lostark.root.simulator.db.dto.GemEnums;

import com.lostark.root.simulator.db.dto.GemStateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Predicate;

@AllArgsConstructor
@Getter
public enum EffectEnum {

    // ────────────────────────────── 의지력 효율 ──────────────────────────────
    WILL_PLUS_1(1, "의지력 효율 +1 증가", 0.116500, s -> s.willEfficiency == 5), // 미등장 조건 : 의지력 효율이 5인 경우
    WILL_PLUS_2 ( 2, "의지력 효율 +2 증가", 0.044000, s -> s.willEfficiency >= 4), // 미등장 조건 : 의지력 효율이 4이상인 경우
    WILL_PLUS_3 ( 3, "의지력 효율 +3 증가", 0.017500, s -> s.willEfficiency >= 3), // 미등장 조건 : 의지력 효율이 3이상인 경우
    WILL_PLUS_4 ( 4, "의지력 효율 +4 증가", 0.004500, s -> s.willEfficiency >= 2), // 미등장 조건 : 의지력 효율이 2이상인 경우
    WILL_MINUS_1( 5, "의지력 효율 -1 감소", 0.030000, s -> s.willEfficiency == 1), // 미등장 조건 : 의지력 효율이 1인 경우

    // ───────────────────────────── 질서/혼돈 포인트 ────────────────────────────
    OC_PLUS_1(6, "질서/혼돈 포인트 +1 증가", 0.116500, s -> s.orderChaosPoint == 5), // 미등장 조건 : 질서/혼돈 포인트가 5인 경우
    OC_PLUS_2   ( 7,  "질서/혼돈 포인트 +2 증가", 0.044000, s -> s.orderChaosPoint >= 4), // 미등장 조건 : 질서/혼돈 포인트가 4이상인 경우
    OC_PLUS_3   ( 8,  "질서/혼돈 포인트 +3 증가", 0.017500, s -> s.orderChaosPoint >= 3), // 미등장 조건 : 질서/혼돈 포인트가 3이상인 경우
    OC_PLUS_4   ( 9,  "질서/혼돈 포인트 +4 증가", 0.004500, s -> s.orderChaosPoint >= 2), // 미등장 조건 : 질서/혼돈 포인트가 2이상인 경우
    OC_MINUS_1  (10,  "질서/혼돈 포인트 -1 감소", 0.030000, s -> s.orderChaosPoint == 1), // 미등장 조건 : 질서/혼돈 포인트가 1인 경우

    // ─────────────────────────────── 첫번째 효과 ───────────────────────────────
    FIRST_LV_UP_1 (11, "첫번째 효과 Lv. 1 증가", 0.116500, s -> s.firstEffectLevel == 5), // 미등장 조건 : 첫번째 효과가 Lv. 5인 경우
    FIRST_LV_UP_2 (12, "첫번째 효과 Lv. 2 증가", 0.044000, s -> s.firstEffectLevel >= 4), // 미등장 조건 : 첫번째 효과가 Lv. 4이상인 경우
    FIRST_LV_UP_3 (13, "첫번째 효과 Lv. 3 증가", 0.017500, s -> s.firstEffectLevel >= 3), // 미등장 조건 : 첫번째 효과가 Lv. 3이상인 경우
    FIRST_LV_UP_4 (14, "첫번째 효과 Lv. 4 증가", 0.004500, s -> s.firstEffectLevel >= 2), // 미등장 조건 : 첫번째 효과가 Lv. 2이상인 경우
    FIRST_LV_DOWN_1(15,"첫번째 효과 Lv. 1 감소", 0.030000, s -> s.firstEffectLevel == 1), // 미등장 조건 : 첫번째 효과가 Lv. 1인 경우

    // ─────────────────────────────── 두번째 효과 ───────────────────────────────
    SECOND_LV_UP_1 (16, "두번째 효과 Lv. 1 증가", 0.116500, s -> s.secondEffectLevel == 5), // 미등장 조건 : 두번째 효과가 Lv. 5인 경우
    SECOND_LV_UP_2 (17, "두번째 효과 Lv. 2 증가", 0.044000, s -> s.secondEffectLevel >= 4), // 미등장 조건 : 두번째 효과가 Lv. 4이상인 경우
    SECOND_LV_UP_3 (18, "두번째 효과 Lv. 3 증가", 0.017500, s -> s.secondEffectLevel >= 3), // 미등장 조건 : 두번째 효과가 Lv. 3이상인 경우
    SECOND_LV_UP_4 (19, "두번째 효과 Lv. 4 증가", 0.004500, s -> s.secondEffectLevel >= 2), // 미등장 조건 : 두번째 효과가 Lv. 2이상인 경우
    SECOND_LV_DOWN_1(20,"두번째 효과 Lv. 1 감소", 0.030000, s -> s.secondEffectLevel == 1), // 미등장 조건 : 두번째 효과가 Lv. 1인 경우

    // ─────────────────────────────── 효과 변경 ────────────────────────────────
    // ※ 주석 규칙:
    //  - 효과 변경 시, 변경 전 해당 효과는 등장하지 않음
    //  - 첫/두번째 효과 변경은 서로의 슬롯은 등장하지 않음
    FIRST_REROLL (21, "첫번째 효과 변경", 0.032500, s -> false),
    SECOND_REROLL(22, "두번째 효과 변경", 0.032500, s -> false),

    // ─────────────────────────────── 가공 비용/상태 ─────────────────────────────
    // 미등장 조건 : 가공 비용 추가 비율이 +100%에 도달한 경우
    // 미등장 조건 : 가공 가능 횟수가 1회 남은 경우
    COST_PLUS_100 (23, "가공 비용 +100% 증가", 0.017500,
            s -> !(s.remainingProcessCount == 1) || s.costExtraPercent >= 100),
    COST_MINUS_100(24, "가공 비용 -100% 감소", 0.017500,
            s -> !(s.remainingProcessCount == 1) || s.costExtraPercent <= -100),

    STATE_HOLD    (25, "가공 상태 유지",     0.017500, s -> false), // 미등장 조건 : 없음

    SEE_OTHER_PLUS_1(26, "다른 항목 보기 +1회 증가", 0.025000,
            s -> s.remainingProcessCount != 1), // 미등장 조건 : 가공 가능 횟수가 1회 남은 경우
    SEE_OTHER_PLUS_2(27, "다른 항목 보기 +2회 증가", 0.007500,
            s -> s.remainingProcessCount != 1); // 미등장 조건 : 가공 가능 횟수가 1회 남은 경우


    private final int num;
    private final String name;
    private final double probability;
    private final Predicate<GemStateDto> blockCondition; // true면 미등장(0%)

    /** 현재 상태에서 등장 가능한가? (미등장 조건 반영) */
    public boolean isAvailable(GemStateDto s) {
        return !blockCondition.test(s);
    }

    /** 현재 상태에서의 유효 가중치(미등장 조건이면 0) */
    public double effectiveWeight(GemStateDto s) {
        return isAvailable(s) ? probability : 0.0;
    }
}
