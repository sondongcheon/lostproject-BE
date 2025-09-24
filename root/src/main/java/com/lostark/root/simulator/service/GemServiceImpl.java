package com.lostark.root.simulator.service;

import com.lostark.root.exception.CustomException;
import com.lostark.root.exception.ErrorCode;
import com.lostark.root.simulator.db.dto.EffectDto;
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
    public GemBasicRes getBasicInfo(GemProcessReq gemProcessReq, int type, int grade) {
        Options option1 = selectOption(OptionNameEnum.values()[type], 0);
        Options option2 = selectOption(OptionNameEnum.values()[type], option1.getNumber());

        GemStateDto state = GemStateDto.fromReq(gemProcessReq);
        int[] effectNum = new int[4];
        String[] effectName = new String[4];
        for (int i = 0; i < 4; i++) {
            EffectEnum effectEnum = processChoiceList(state).orElse(null);
            assert effectEnum != null;
            state.addPickedNums(effectEnum.getNum());
            effectNum[i] = effectEnum.getNum();
            effectName[i] = effectEnum.getName();

        }

        List<EffectEnum> all = Arrays.asList(EffectEnum.values());
        List<Double> weight = all.stream()
                .map(e -> e.effectiveWeight(state))
                .toList();
        //return new String[] { "의지력 효율", "질서 포인트", option1.getName(), option2.getName()};
        return GemBasicRes.builder()
                .optionNum(new int[] {option1.getNumber(), option2.getNumber(), 0, 0})
                .optionName(new String[] { option1.getName(), option2.getName(), "의지력 효율", "질서 포인트"})
                .optionState(new int[] { 1, 1, 1, 1})
                .costExtraPercent(0)
                .remainingProcessCount(grade)
                .effectNum(effectNum)
                .effectName(effectName)
                .rerollChoiceList(1)
                .choiceEffect("가공 이전")
                .weight(getWeight(state))
                .build();
    }



    //가공하기
    @Override
    public GemBasicRes processGem(GemProcessReq gemProcessReq, int type) {
        int r = (int) (Math.random() * 4);
        log.info("choice num : {}", r);
        GemStateDto dto = GemStateDto.fromReq(gemProcessReq);
        dto.processCountMinus();
        int[] OptionNum = new int[] { gemProcessReq.getOptionNum()[0], gemProcessReq.getOptionNum()[1], 0, 0};
        String[] OptionName = new String[] { gemProcessReq.getOptionName()[0], gemProcessReq.getOptionName()[1], "의지력 효율", "질서 포인트"};
        log.info("choice EffectNum : {}", gemProcessReq.getEffectNum()[r]);
        if( gemProcessReq.getEffectNum()[r] != 21 && gemProcessReq.getEffectNum()[r] != 22) {
            dto.processResult(gemProcessReq.getEffectNum()[r]);
        } else if (gemProcessReq.getEffectNum()[r] == 21) {
            Options newOption = selectOption(OptionNameEnum.values()[type], gemProcessReq.getOptionNum()[1]);
            OptionNum[0] = newOption.getNumber();
            OptionName[0] = newOption.getName();
        } else if (gemProcessReq.getEffectNum()[r] == 22) {
            Options newOption = selectOption(OptionNameEnum.values()[type], gemProcessReq.getOptionNum()[2]);
            OptionNum[1] = newOption.getNumber();
            OptionName[1] = newOption.getName();
        }

        int[] effectNum = new int[4];
        String[] effectName = new String[4];
        dto.pickedNums.clear();
        for (int i = 0; i < 4; i++) {
            EffectEnum effectEnum = processChoiceList(dto).orElse(null);
            assert effectEnum != null;
            dto.addPickedNums(effectEnum.getNum());
            effectNum[i] = effectEnum.getNum();
            effectName[i] = effectEnum.getName();
        }

        return GemBasicRes.dtoToProcessRes(dto, OptionNum, OptionName, effectNum, effectName, gemProcessReq.getEffectName()[r], getWeight(dto));

    }

    @Override
    public GemBasicRes reRollChoiceList(GemProcessReq gemProcessReq) {
        if(gemProcessReq.getRemainingProcessCount() == 0)  throw new CustomException(ErrorCode.SIMULATOR_ERROR);
        GemStateDto dto = GemStateDto.fromReq(gemProcessReq);
        int[] effectNum = new int[4];
        String[] effectName = new String[4];
        dto.pickedNums.clear();
        for (int i = 0; i < 4; i++) {
            EffectEnum effectEnum = processChoiceList(dto).orElse(null);
            assert effectEnum != null;
            dto.addPickedNums(effectEnum.getNum());
            effectNum[i] = effectEnum.getNum();
            effectName[i] = effectEnum.getName();

        }

        return GemBasicRes.forReRollEffect(effectNum, effectName, getWeight(dto));
        //return GemBasicRes.dtoToProcessRes(dto, gemProcessReq.getOptionNum(), gemProcessReq.getOptionName(), effectNum, effectName, null);
    }

    @Override
    public GemBasicRes checkWeight(GemProcessReq gemProcessReq) {
        GemStateDto dto = GemStateDto.fromReq(gemProcessReq);
        for( int a : gemProcessReq.getEffectNum()) dto.pickedNums.add(a);
        return GemBasicRes.forReRollEffect(null, null, getWeight(dto));
    }

    // (참고) 디버깅용: 현재 상태에서의 유효 가중치 목록
    public static Map<EffectEnum, Double> effectiveWeights(GemStateDto s) {
        return Arrays.stream(EffectEnum.values())
                .collect(Collectors.toMap(e -> e, e -> e.effectiveWeight(s)));
    }

    //가공 선택지 뽑기
    private Options selectOption(OptionNameEnum nameEnum, int isAvailableNum) {
        List<Options> OptionList = fromNameEnum(nameEnum);

        if( isAvailableNum != 0) OptionList.remove(isAvailableNum-1);

        Random random = new Random();
        int rand = random.nextInt(OptionList.size());

        return OptionList.get(rand);

    }

    //가공 효과 뽑기
    private Optional<EffectEnum> processChoiceList(GemStateDto state) {
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

    private List<EffectDto> getWeight(GemStateDto dto) {
        List<EffectEnum> all = Arrays.asList(EffectEnum.values());

        double total = all.stream().mapToDouble(e -> e.effectiveWeight(dto)).sum();
        log.info("total : , {}", total);

        return all.stream().filter(e -> e.effectiveWeight(dto) != 0)
                .map(e -> new EffectDto(
                        e.getNum(),
                        e.getName(),              // key: Enum의 name
                        e.effectiveWeight(dto) / total * 100) // value: 계산된 값
                ).toList();
    }



}
