package com.lostark.root.simulator.db.dto.res;

import com.lostark.root.simulator.db.dto.EffectDto;
import com.lostark.root.simulator.db.dto.GemStateDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class GemBasicRes {

    private int[] optionNum;
    private String[] optionName;
    private int[] optionState;
    private int costExtraPercent;
    private int remainingProcessCount;
    private int[] effectNum;
    private String[] effectName;
    private int rerollChoiceList;
    private String choiceEffect;
    private List<EffectDto> weight;


    static public GemBasicRes dtoToProcessRes(GemStateDto dto, int[] optionNum, String[] optionName, int[] effectNum, String[] effectName, String choiceEffect, List<EffectDto>  weight) {
        return GemBasicRes.builder()
                .optionNum(optionNum)
                .optionName(optionName)
                .optionState(new int[] { dto.getFirstEffectLevel(), dto.getSecondEffectLevel(), dto.getWillEfficiency(), dto.getOrderChaosPoint()})
                .costExtraPercent(dto.getCostExtraPercent())
                .remainingProcessCount(dto.getRemainingProcessCount())
                .effectNum(effectNum)
                .effectName(effectName)
                .rerollChoiceList(dto.getRerollChoiceList())
                .choiceEffect(choiceEffect)
                .weight(weight)
                .build();
    }

    static public GemBasicRes forReRollEffect(int[] effectNum, String[] effectName, List<EffectDto> weight) {
        return GemBasicRes.builder()
                .effectNum(effectNum)
                .effectName(effectName)
                .weight(weight)
                .build();
    }
}
