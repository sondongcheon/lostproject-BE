package com.lostark.root.auction.db.dto.ItemsEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Book implements ItemsData {

    /* 유각 아이템 코드 리스트
     * 스케쥴링으로 정보 가져오는 용도로 주로 사용
     */

    ADRENALINE("adrenaline", 65203905, "아드레날린 각인서"),
    ONEHAN("onehan", 65200505, "원한 각인서"),
    YEAHDUN("yeahdun", 65201005, "예리한 둔기 각인서"),
    DOLDAE("doldae", 65203305, "돌격대장 각인서"),
    JEBAT("jebat", 65202805, "저주받은 인형 각인서"),
    GISOP("gisop", 65203005, "기습의 대가 각인서"),
    JILJENG("jiljeng", 65203505, "질량 증가 각인서"),
    TADAE("tadae", 65203705, "타격의 대가 각인서"),
    GALDAE("galdae", 65201505, "결투의 대가 각인서"),
    MAHEE("mahee", 65203105, "마나의 흐름 각인서"),
    SUPERCHARGE("supercharge", 65200605, "슈퍼 차지 각인서"),
    JUNMON("junmon", 65204105, "전문의 각인서"),
    MAHWOJUNG("mahwojung", 65201305, "마나 효율 증가 각인서"),
    GACKSUNG("gacksung", 65203405, "각성 각인서"),
    GUDONG("gudong", 65200805, "구슬동자 각인서"),
    SOCKSOCK("socksock", 65204005, "속전속결 각인서"),
    BARI("bari", 65203205, "바리케이트 각인서"),
    ANSANG("ansang", 65200405, "안정된 상태 각인서"),
    JUNGGAP("junggap", 65202105, "중갑 착용 각인서"),
    JANGDAN("jangdan", 65204305, "정밀 단도 각인서"),
    GPTA("gpta", 65201105, "급소 타격 각인서"),
    ATTAR("attar", 65200305, "에테르 포식자 각인서"),
    MAXMANA("maxmana", 65201205, "최대 마나 증가 각인서"),
    JUNGGI("junggi", 65200205, "정기 흡수 각인서");

    private final String name;          // DB table name
    private final int id;               // 아이템 코드
    private final String visualName;    // 표기 이름

    public String getTypeName() {
        return "book";
    }

    public int getCategoryCode() {
        return 40000;   // 각인서 카테고리 코드
    }

    public int getItemTier() {
        return 0;       // 티어 미분류
    }

    public String getItemGrade() {
        return "유물";    // 유각 전용이라 고정
    }

    public String getItemName() {
        return "각인서";   // 각인서 전용이라 고정
    }
}
