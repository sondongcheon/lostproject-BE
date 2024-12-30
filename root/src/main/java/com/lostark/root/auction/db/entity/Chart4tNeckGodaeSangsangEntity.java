package com.lostark.root.auction.db.entity;

import com.lostark.root.auction.db.dto.res.APIres.ApiAuctionRes;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "chart_4t_neck_godae_sangsang")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chart4tNeckGodaeSangsangEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int tier;
    private String wearType;
    private int quality;
    private int upgrade;
    private int trade;
    private int price;

    static public Chart4tNeckGodaeSangsangEntity fromApiRes(ApiAuctionRes.Item item) {
        return Chart4tNeckGodaeSangsangEntity.builder()
                .tier(item.getTier())
                .wearType(item.getGrade())
                .quality(item.getGradeQuality())
                .upgrade(item.getAuctionInfo().getUpgradeLevel())
                .trade(item.getAuctionInfo().getTradeAllowCount())
                .price(item.getAuctionInfo().getBuyPrice())
                .build();
    }

}
