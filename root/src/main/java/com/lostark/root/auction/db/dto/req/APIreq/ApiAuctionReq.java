package com.lostark.root.auction.db.dto.req.APIreq;

import com.lostark.root.auction.db.dto.ChartSelectTypeDto;
import com.lostark.root.auction.db.dto.OptionValueEnum;
import com.lostark.root.auction.db.dto.req.SelectOptionReq;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ApiAuctionReq {

    /*  OpenAPI 요청 Body DTO
     */

    private int itemGradeQuality;           // 품질
    private Integer itemUpgradeLevel;       // 연마 횟수 0일 경우 오류
    private Integer itemTradeAllowCount;    // 거래 가능 횟수
    @Setter
    private List<EtcOption> etcOptions;
    private String sort;                    // 정렬 기준 ( ex. BUY_PRICE )
    @Setter
    private int categoryCode;
    private int itemTier;                   // 티어, 0일경우 모두로 취급
    private String itemGrade;               // 등급 ( 희귀 영웅 유물 고대 )
    private String itemName;                // 표기 이름
    private int pageNo;                     // 페이지 ( 0과 1이 동일취급 2이후 숫자대로 감 )
    private String sortCondition;           // 정렬 방식 ( ASC, DESC )

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Builder
    public static class EtcOption {

        private Integer firstOption;        // 옵션 카테고리 ( ex. 연마 종류 등등 )
        private Integer secondOption;       // 카테고리 세부 분류 ( 여기서는 연마 옵션 )
        private Integer minValue;           // 옵션 수치
        private Integer maxValue;           // 옵션 수치

        // Request 받은 선택 옵션으로 요청 API BODY 만들기
        static public EtcOption fromSelectEtcOption(SelectOptionReq.EtcOption etcOption,    //선택한 옵션 Request 에서 그대로 가져옴
                                                    int tier)                               //티어는 etcOption 밖에 있어서 따로
        {
            // 옵션 코드를 가지고 어떤 옵션인지 정보 가져오기
            OptionValueEnum option = OptionValueEnum.getByOptionTierValueLevel(etcOption.getOption(), tier, etcOption.getValue());

            return EtcOption.builder()
                    .firstOption(7) // 7 -> 악세서리 연마 메소드 용도에 맞는 고정
                    .secondOption(etcOption.getOption() == 0 ? null : etcOption.getOption())    // 미선택 오류 방지
                    .minValue(option.getValue())
                    .maxValue(option.getValue()).build();

        }
    }

    // 우리 페이지에서 선택한 옵션으로 OpenAPI 요청 양식에 맞는 Body 만들기
    // 페이지네이션 X, 오버로딩
    static public ApiAuctionReq fromSelectOption(SelectOptionReq selectOptionReq) {

        List<EtcOption> etcOptionList = new ArrayList<>();
        // 1품목의 선택 옵션 리스트 만들기 ( 보통 0~2 )
        for (int i = 0; i < selectOptionReq.getEtcOptionList().size(); i++) {
            etcOptionList.add(
                    ApiAuctionReq.EtcOption.fromSelectEtcOption(
                            selectOptionReq.getEtcOptionList().get(i),
                            SelectOptionReq.filterTier(selectOptionReq.getTier(), selectOptionReq.getItemGrade())
                    )
            );
        }

        return ApiAuctionReq.builder()
                .itemGradeQuality(selectOptionReq.getQuality())
                .itemUpgradeLevel(selectOptionReq.getUpgradeLevel())
                .itemTradeAllowCount(selectOptionReq.getTradeAllowCount())
                .etcOptions(etcOptionList)      // 위에서 만든 옵션 리스트 클래스
                .sort("BUY_PRICE")              // 가격순 정렬 기본값
                .categoryCode(selectOptionReq.getCategoryCode())
                .itemTier(selectOptionReq.getTier())
                .itemGrade(selectOptionReq.getItemGrade())
                .pageNo(selectOptionReq.getPageNo())
                .sortCondition("ASC").build();
    }

    // 03.20 힘민지 필터 몇페이지까지 했는지
    // 페이지네이션 O, 오버로딩
    static public ApiAuctionReq fromSelectOption(SelectOptionReq selectOptionReq, int cntPage) {
        List<EtcOption> etcOptionList = new ArrayList<>();
        for (int i = 0; i < selectOptionReq.getEtcOptionList().size(); i++) {
            etcOptionList.add(
                    ApiAuctionReq.EtcOption.fromSelectEtcOption(
                        selectOptionReq.getEtcOptionList().get(i),
                        SelectOptionReq.filterTier(selectOptionReq.getTier(), selectOptionReq.getItemGrade())
                    )
            );
        }
//        if(selectOptionReq.getEtcOptionList().size() == 1) etcOptionList.add(new EtcOption());
        return ApiAuctionReq.builder()
                .itemGradeQuality(selectOptionReq.getQuality())
                .itemUpgradeLevel(selectOptionReq.getUpgradeLevel())
                .itemTradeAllowCount(selectOptionReq.getTradeAllowCount())
                .etcOptions(etcOptionList)
                .sort("BUY_PRICE")
                .categoryCode(selectOptionReq.getCategoryCode())
                .itemTier(selectOptionReq.getTier())
                .itemGrade(selectOptionReq.getItemGrade())
                .pageNo(cntPage)
                .sortCondition("ASC").build();
    }

    // 보석 검색용 OpenAPI 요청 Body
    static public ApiAuctionReq forJewel(String name) {
        return ApiAuctionReq.builder()
                .categoryCode(210000)
                .itemTier(4)
                .sort("BUY_PRICE")
                .itemName(name)
                .sortCondition("ASC")
                .build();
    }


    /* 악세서리 차트 제작용
     * 스케쥴링을 위한 데이터 수집 요청때 사용
     */
    static public ApiAuctionReq toChart(ChartSelectTypeDto dto) {
        List<EtcOption> etcOptionList = new ArrayList<>();
        etcOptionList.add(EtcOption.builder().firstOption(7).secondOption(dto.getEtcOption().getOption() == 0 ? null : dto.getEtcOption().getOption()).minValue(dto.getEtcOption().getValue()).maxValue(dto.getEtcOption().getValue()).build());
        etcOptionList.add(EtcOption.builder().firstOption(7).secondOption(dto.getEtcOption2().getOption() == 0 ? null : dto.getEtcOption2().getOption()).minValue(dto.getEtcOption2().getValue()).maxValue(dto.getEtcOption2().getValue()).build());

        return ApiAuctionReq.builder()
                .itemGradeQuality(dto.getQuality())
                .itemUpgradeLevel(null)
                .itemTradeAllowCount(null)
                .etcOptions(etcOptionList)
                .sort("BUY_PRICE")
                .categoryCode(dto.getCategoryCode())
                .itemTier(4)
                .itemGrade(dto.getGrade())
                .pageNo(0)
                .sortCondition("ASC").build();
    }

    //클래스 핸들링 : 연마옵션 가져오기
    public Integer getSecondOption(int index) {
        return etcOptions.get(index).getSecondOption();
    }

    //클래스 핸들링 : 단일값 검색용 -> 범위 검색이 아니면 Min Max 설정값이 동일한 설정을 해야하는데 사용
    public void setValue(int index, int value) {
        etcOptions.get(index).setFirstOption(7);
        etcOptions.get(index).setMinValue(value);
        etcOptions.get(index).setMaxValue(value);
    }

    // 종류와 첫번째 옵션이 있으면 나머지 옵션을 가져오는데 사용 ( 옵션의 종류는 두개, 옵션 코드 배치가 규치성 )
    public void setAccInfo(int index, int type) {
        setCategoryCode(200010 + (index * 10));
        etcOptions.getFirst().setSecondOption(41 + type + (index * 4));
        etcOptions.get(1).setSecondOption(42 + type + (index * 4));
    }


}
