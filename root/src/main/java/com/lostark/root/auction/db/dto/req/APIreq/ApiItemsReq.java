package com.lostark.root.auction.db.dto.req.APIreq;

import com.lostark.root.auction.db.dto.ItemsEnum.ItemsData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiItemsReq {

    /*  OpenAPI 요청 Body DTO
     *  거래소 아이템 요청 ( 클라이언트로 받은 종류를 가지고 Enums 을 만들어 생성자로 클래스 만듬 )
     */

    public ApiItemsReq(ItemsData itemsData, int pageNo) {

        this.sort = "YDAY_AVG_PRICE";
        this.categoryCode = itemsData.getCategoryCode();
        this.itemTier = itemsData.getItemTier();
        this.itemGrade = itemsData.getItemGrade();
        this.itemName = itemsData.getItemName();
        this.pageNo = pageNo;
        this.SortCondition = "DESC";
    }

    private String sort;
    private int categoryCode;
    private int itemTier;
    private String itemGrade;
    private String itemName;
    private int pageNo;
    private String SortCondition;


}
