package com.lostark.root.simulator.db.dto;

import com.lostark.root.simulator.db.dto.req.GemProcessReq;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
public class GemStateDto {

    public int willEfficiency;      // 1~5
    public int orderChaosPoint;     // 1~5
    public int firstEffectLevel;    // 1~5
    public int secondEffectLevel;   // 1~5
    public int costExtraPercent;    // -100 ~ +100
    public int remainingProcessCount; // 남은 가공 가능 횟수
    public int rerollChoiceList; // 다른 항목 보기 횟수

    public final Set<Integer> pickedNums = new HashSet<>();

    static public GemStateDto fromReq(GemProcessReq gemProcessReq) {
        return GemStateDto.builder()
                .willEfficiency(gemProcessReq.getOptionState()[2])
                .orderChaosPoint(gemProcessReq.getOptionState()[3])
                .firstEffectLevel(gemProcessReq.getOptionState()[0])
                .secondEffectLevel(gemProcessReq.getOptionState()[1])
                .costExtraPercent(gemProcessReq.getCostExtraPercent())
                .remainingProcessCount(gemProcessReq.getRemainingProcessCount())
                .rerollChoiceList(gemProcessReq.getRerollChoiceList())
                .build();
    }

    public void addPickedNums (int num) {
        this.pickedNums.add(num);
    }

    public void processCountMinus() {
        this.remainingProcessCount--;
    }

    public void processResult(int selectNum) {

        //첫째줄 첫번째 효과 두번째줄 두번째 효과 세번째줄 의지력 효율 네번째 줄 질서 포인트
        switch (selectNum) {
            case 1 -> this.willEfficiency = this.willEfficiency + 1;
            case 2 -> this.willEfficiency = this.willEfficiency + 2;
            case 3 -> this.willEfficiency = this.willEfficiency + 3;
            case 4 -> this.willEfficiency = this.willEfficiency + 4;
            case 5 -> this.willEfficiency = this.willEfficiency - 1;
            case 6 -> this.orderChaosPoint = this.orderChaosPoint + 1;
            case 7 -> this.orderChaosPoint = this.orderChaosPoint + 2;
            case 8 -> this.orderChaosPoint = this.orderChaosPoint + 3;
            case 9 -> this.orderChaosPoint = this.orderChaosPoint + 4;
            case 10 -> this.orderChaosPoint = this.orderChaosPoint - 1;
            case 11 -> this.firstEffectLevel = this.firstEffectLevel + 1;
            case 12 -> this.firstEffectLevel = this.firstEffectLevel + 2;
            case 13 -> this.firstEffectLevel = this.firstEffectLevel + 3;
            case 14 -> this.firstEffectLevel = this.firstEffectLevel + 4;
            case 15 -> this.firstEffectLevel = this.firstEffectLevel - 1;
            case 16 -> this.secondEffectLevel = this.secondEffectLevel + 1;
            case 17 -> this.secondEffectLevel = this.secondEffectLevel + 2;
            case 18 -> this.secondEffectLevel = this.secondEffectLevel + 3;
            case 19 -> this.secondEffectLevel = this.secondEffectLevel + 4;
            case 20 -> this.secondEffectLevel = this.secondEffectLevel - 1;
            // case 21, 22 인 경우 service 에서 처리
            case 23 -> this.costExtraPercent = this.costExtraPercent + 100;
            case 24 -> this.costExtraPercent = this.costExtraPercent - 100;
            // case 25 : 가공 상태 유지
            case 26 -> this.rerollChoiceList = this.rerollChoiceList + 1;
            case 27 -> this.rerollChoiceList = this.rerollChoiceList + 2;
        }
    }

}
