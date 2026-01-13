package com.lostark.root.auction.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChartJewelEntity {

    /*  보석 가격 정보 Entity
     *  보석 Table 의 컬럼은 모두 동일하므로 여러 테이블을 Generic 사용중
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int price;
    private int totalCount;
    @CreationTimestamp
    private LocalDateTime createAt;
}
