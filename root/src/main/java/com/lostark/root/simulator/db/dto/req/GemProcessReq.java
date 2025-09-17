package com.lostark.root.simulator.db.dto.req;

import lombok.Getter;

@Getter
public class GemProcessReq {

    private int[] optionNum;
    private String[] optionName;
    private int[] optionState;
    private int costExtraPercent;
    private int remainingProcessCount;
    private int[] effectNum;
    private String[] effectName;
    private int rerollChoiceList;

}
