package com.lostark.root.auction.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChartItemsEntity {

    /*  주요 아이템 가격 정보 Entity
     *  주요 아이템 Table 의 컬럼은 모두 동일하므로 여러 테이블을 Generic 사용중
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;
    private double avgPrice;
    private int tradeCount;


}
