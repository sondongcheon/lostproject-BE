package com.lostark.root.auction.db.dto.res;

import com.lostark.root.auction.db.dto.StatMinMaxEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private double stat;
    private double statPer;
    private String statLevel;
    private int tier;
    private String itemLevel;

    public EquipmentRes(String itemLevel) {
        this.itemLevel = itemLevel;
        this.option = new ArrayList<>();
        this.value = new ArrayList<>();
        this.valueLevel = new ArrayList<>();
        this.statLevel = "無급 스텟";
    }

    public void addValueLevel(int valueLevel) {
        this.valueLevel.add(switch (valueLevel) {
            case 3 -> "상";
            case 2 -> "중";
            case 1 -> "하";
            default -> "공";
        });
    }

    public void setStats(double stat, String category) {
        this.stat = stat;
        double statPer = calStatPer(stat, category.equals("목걸이") ? 200010 : category.equals("귀걸이") ? 200020 : 200030, this.option.size());
        this.statPer = new BigDecimal(statPer)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
        if (this.statPer > 75) {
            this.statLevel = "상급 스텟";
        } else if (this.statPer > 50) {
            this.statLevel = "중급 스텟";
        } else if (this.statPer > 25) {
            this.statLevel = "하급 스텟";
        } else {
            this.statLevel = "無급 스텟";
        }

    }

    //힘민지 필터
    private double calStatPer(double currentStat, int categoryCode, int upgrade) {
        StatMinMaxEnum stat = StatMinMaxEnum.getByCategory(categoryCode, upgrade);
        return (currentStat - stat.getMinValue()) / (stat.getMaxValue() - stat.getMinValue()) * 100;
    }

}
