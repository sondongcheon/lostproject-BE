package com.lostark.root.simulator.service;

import com.lostark.root.simulator.db.dto.GemEnums.EffectEnum;
import com.lostark.root.simulator.db.dto.GemEnums.OptionNameEnum;
import com.lostark.root.simulator.db.dto.GemStateDto;
import com.lostark.root.simulator.db.dto.req.GemProcessReq;
import com.lostark.root.simulator.db.dto.res.GemBasicRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GemServiceImpl implements GemService {


    @Override
    public GemBasicRes getBasicInfo(GemProcessReq gemProcessReq, int type) {
        Options option1 = selectOption(OptionNameEnum.values()[type], 0);
        Options option2 = selectOption(OptionNameEnum.values()[type], option1.getNumber());

        int[] effectNum = new int[4];
        String[] effectName = new String[4];
        for (int i = 0; i < 4; i++) {
            EffectEnum effectEnum = processResult(gemProcessReq).orElse(null);
            assert effectEnum != null;
            effectNum[i] = effectEnum.getNum();
            effectName[i] = effectEnum.getName();
        }
        //return new String[] { "의지력 효율", "질서 포인트", option1.getName(), option2.getName()};
        return GemBasicRes.builder()
                .optionNum(new int[] {option1.getNumber(), option2.getNumber(), 0, 0})
                .optionName(new String[] { option1.getName(), option2.getName(), "의지력 효율", "질서 포인트"})
                .optionState(new int[] { 1, 1, 1, 1})
                .costExtraPercent(0)
                .remainingProcessCount(9)
                .effectNum(effectNum)
                .effectName(effectName)
                .build();
    }

    @Override
    public Optional<EffectEnum> processResult(GemProcessReq gemProcessReq) {
        GemStateDto state = GemStateDto.fromReq(gemProcessReq);
        // 1) 유효 가중치 계산
        List<EffectEnum> all = Arrays.asList(EffectEnum.values());
        double total = all.stream().mapToDouble(e -> e.effectiveWeight(state)).sum();
        if (total <= 0) return Optional.empty();

        // 2) 정규화 후 룰렛 휠
        double r = Math.random() * total;
        double acc = 0.0;
        for (EffectEnum e : all) {
            double w = e.effectiveWeight(state);
            if (w <= 0) continue;
            acc += w;
            if (r <= acc) return Optional.of(e);
        }
        return Optional.empty();
    }

    // (참고) 디버깅용: 현재 상태에서의 유효 가중치 목록
    public static Map<EffectEnum, Double> effectiveWeights(GemStateDto s) {
        return Arrays.stream(EffectEnum.values())
                .collect(Collectors.toMap(e -> e, e -> e.effectiveWeight(s)));
    }

    private Options selectOption(OptionNameEnum nameEnum, int isAvailableNum) {
        List<Options> OptionList = fromNameEnum(nameEnum);

        if( isAvailableNum != 0) OptionList.remove(isAvailableNum-1);

        Random random = new Random();
        int rand = random.nextInt(OptionList.size());

        return OptionList.get(rand);

    }



    @Getter
    @AllArgsConstructor
    private static class Options {

        public int number;
        public String name;

    }

    private List<Options> fromNameEnum(OptionNameEnum nameEnum) {
        return new ArrayList<>(List.of(
                new Options(1, nameEnum.getOption1()),
                new Options(2, nameEnum.getOption2()),
                new Options(3, nameEnum.getOption3()),
                new Options(4, nameEnum.getOption4()))
        );
    }
}
