package com.lostark.root.auction.db.dto.ItemsEnum;

public interface ItemsData {

    /*      OpenAPI 검색용 Body 만들기위한 get 위주로 구성
     *      혹은 DB 구성을 위한
     */

    String getName();
    int getId();
    String getTypeName();
    int getCategoryCode();
    int getItemTier();
    String getItemGrade();
    String getItemName();
    String getVisualName();
}
