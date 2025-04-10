package com.lostark.root.auction.db.dto.ItemsEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Book implements ItemsData {

    ADRENALINE("adrenaline", 65203905),
    ONEHAN("onehan", 65200505),
    YEAHDUN("yeahdun", 65201005),
    DOLDAE("doldae", 65203305),
    JEBAT("jebat", 65202805),
    GISOP("gisop", 65203005),
    JILJENG("jiljeng", 65203505),
    TADAE("tadae", 65203705),
    GALDAE("galdae", 65201505),
    MAHEE("mahee", 65203105),
    SUPERCHARGE("supercharge", 65200605),
    JUNMON("junmon", 65204105),
    MAHWOJUNG("mahwojung", 65201305),
    GACKSUNG("gacksung", 65203405),
    GUDONG("gudong", 65200805),
    SOCKSOCK("socksock", 65204005),
    BARI("bari", 65203205),
    ANSANG("ansang", 65200405),
    JUNGGAP("junggap", 65202105),
    JANGDAN("jangdan", 65204305),
    GPTA("gpta", 65201105),
    ATTAR("attar", 65200305),
    MAXMANA("maxmana", 65201205),
    JUNGGI("junggi", 65200205);

    private final String name;
    private final int id;

    public String getTypeName() {
        return "book";
    }

    public int getCategoryCode() {
        return 40000;
    }

    public int getItemTier() {
        return 0;
    }

    public String getItemGrade() {
        return "유물";
    }

    public String getItemName() {
        return "각인서";
    }
}
