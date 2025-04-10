package com.lostark.root.auction.db.dto.ItemsEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Upgrade implements ItemsData{

    AVIBOS("avibos", 6861012),
    UNPASUK("unpasuk", 66102006),
    UNSUSUK("unsusuk", 66102106),
    UNDOL("undol", 66110225),
    UNPA_S("unpa_s", 66130141),
    UNPA_M("unpa_m", 66130142),
    UNPA_L("unpa_l", 66130143),
    BINGSEOM("bingseom", 66111132),
    YONGSEOM("yongseom", 66111131),
    YAGUMBOOK_14("yagumbook_14", 66112543),
    JAEBONGBOOK_14("jaebongbook_14", 66112546),
    YAGUMSCROLL_1("yagumscroll_1", 66112711),
    JAEBONGSCROLL_1("jaebongscroll_1", 66112712),
    YAGUMSCROLL_2("yagumscroll_2", 66112713),
    JAEBONGSCROLL_2("jaebongscroll_2", 66112714);

    private final String name;
    private final int id;

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
