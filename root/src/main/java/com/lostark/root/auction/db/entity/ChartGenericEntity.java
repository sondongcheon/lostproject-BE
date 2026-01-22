package com.lostark.root.auction.db.entity;

import com.lostark.root.auction.db.dto.res.APIres.ApiAuctionRes;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChartGenericEntity {

    /*  악세서리 가격 정보 Entity
     *  악세서리 Table 의 컬럼은 모두 동일하므로 여러 테이블을 Generic 사용중
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int tier;
    private int quality;
    private int upgrade;
    private int trade;
    private int price;
    private int totalCount;

    @CreationTimestamp
    private LocalDateTime createAt;
    
}
