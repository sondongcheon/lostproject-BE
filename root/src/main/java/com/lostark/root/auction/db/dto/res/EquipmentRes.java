package com.lostark.root.auction.db.dto.res;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EquipmentRes {

    private List<String> option;
    private List<String> value;
    private List<String> valueLevel;
    private String type;
    private String grade;
    private int tier;
    private String itemLevel;

    public EquipmentRes(String itemLevel) {
        this.itemLevel = itemLevel;
        this.option = new ArrayList<>();
        this.value = new ArrayList<>();
        this.valueLevel = new ArrayList<>();
    }
}
