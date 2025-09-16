package com.lostark.root.simulator.db.dto;

import com.lostark.root.simulator.db.dto.req.GemProcessReq;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GemStateDto {

    public final int willEfficiency;      // 1~5
    public final int orderChaosPoint;     // 1~5
    public final int firstEffectLevel;    // 1~5
    public final int secondEffectLevel;   // 1~5
    public final int costExtraPercent;    // -100 ~ +100
    public final int remainingProcessCount; // 남은 가공 가능 횟수

    static public GemStateDto fromReq(GemProcessReq gemProcessReq) {
        return GemStateDto.builder()
                .willEfficiency(gemProcessReq.getOptionState()[2])
                .orderChaosPoint(gemProcessReq.getOptionState()[3])
                .firstEffectLevel(gemProcessReq.getOptionState()[0])
                .secondEffectLevel(gemProcessReq.getOptionState()[1])
                .costExtraPercent(gemProcessReq.getCostExtraPercent())
                .remainingProcessCount(gemProcessReq.getRemainingProcessCount())
                .build();
    }

}
