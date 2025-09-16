package com.lostark.root.simulator.db.dto.res;

import lombok.Builder;
import lombok.Getter;

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
}
