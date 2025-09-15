package com.lostark.root.auction.db.dto.ItemsEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Upgrade implements ItemsData{

    AVIBOS("avibos", 6861012, "아비도스 융화 재료"),
    UNPASUK("unpasuk", 66102006, "운명의 파괴석"),
    UNSUSUK("unsusuk", 66102106, "운명의 수호석"),
    UNDOL("undol", 66110225, "운명의 돌파석"),
    UNPA_S("unpa_s", 66130141, "운명의 파편 주머니(소)"),
    UNPA_M("unpa_m", 66130142, "운명의 파편 주머니(중)"),
    UNPA_L("unpa_l", 66130143, "운명의 파편 주머니(대)"),
    BINGSEOM("bingseom", 66111132, "빙하의 숨결"),
    YONGSEOM("yongseom", 66111131, "용암의 숨결"),
    YAGUMBOOK_14("yagumbook_14", 66112543, "야금술 : 업화 [11-14]"),
    JAEBONGBOOK_14("jaebongbook_14", 66112546, "재봉술 : 업화 [11-14]"),
    YAGUMSCROLL_1("yagumscroll_1", 66112711, "장인의 야금술 : 1단계"),
    JAEBONGSCROLL_1("jaebongscroll_1", 66112712, "장인의 재봉술 : 1단계"),
    YAGUMSCROLL_2("yagumscroll_2", 66112713, "장인의 야금술 : 2단계"),
    JAEBONGSCROLL_2("jaebongscroll_2", 66112714, "장인의 재봉술 : 2단계");

    private final String name;
    private final int id;
    private final String visualName;

    public String getTypeName() {
        return "upgrade";
    }

    public int getCategoryCode() {
        return 50000;
    }

    public int getItemTier() {
        return 4;
    }

    public String getItemGrade() {
        return "";
    }

    public String getItemName() {
        return "";
    }
}
