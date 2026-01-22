package com.lostark.root.auction.db.dto.req.APIreq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiBookReq {

    /*  OpenAPI 요청 Body DTO (유물각인서)
     */

    public ApiBookReq(int pageNo) {
        this.sort = "YDAY_AVG_PRICE";
        this.categoryCode = 40000;
        this.itemGrade = "유물";
        this.itemName = "각인서";
        this.pageNo = pageNo;
        this.SortCondition = "DESC";
    }

    private String sort;
    private int categoryCode;
    private String itemGrade;
    private String itemName;
    private int pageNo;
    private String SortCondition;

}
