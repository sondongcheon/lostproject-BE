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
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChartGenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int tier;
    private int quality;
    private int upgrade;
    private int trade;
    private int price;

    @CreationTimestamp
    private LocalDateTime createAt;
    
}
