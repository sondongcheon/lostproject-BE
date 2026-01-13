package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.ItemsEnum.Book;
import com.lostark.root.auction.db.dto.ItemsEnum.Gem;
import com.lostark.root.auction.db.dto.ItemsEnum.ItemsData;
import com.lostark.root.auction.db.dto.ItemsEnum.Upgrade;
import com.lostark.root.auction.db.dto.req.APIreq.ApiBookReq;
import com.lostark.root.auction.db.dto.req.APIreq.ApiItemsReq;
import com.lostark.root.auction.db.dto.res.ChartItemsInfoRes;
import com.lostark.root.auction.db.entity.ChartItemsEntity;
import com.lostark.root.common.staticMethod.ApiRequest;
import com.lostark.root.exception.CustomException;
import com.lostark.root.exception.ErrorCode;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChartItemsServiceImpl implements ChartItemsService {

    /*  주요아이템 차트에 사용되는 서비스 메소드 클래스
     *  거래소 아이템 정보에 사용 (+ 거래소 아이템은 table 컬럼이 모두 동일)
     */

    private final EntityManager entityManager;


    /*  차트를 그리기 위한 정보를 반환하는 서비스 메소드
     *  최근 가격을 구하기 위한 key / 어떤 아이템인지 구분하기 위한 type / db 옵션의 time, point
     */
    @Override
    public List<ChartItemsInfoRes> getChartInfo(String key, int type, int time, int point) {
        ItemsData[] itemsData = selectType(type);
        List<ChartItemsInfoRes> chartItemsInfoResList = new ArrayList<>();

        /* 같은 종류의 현재 가격정보를 수집 ( ex. 각인서 들, 젬 들 )
         * currentPayList 메소드를 통해 apiResultList에 저장
         */
        List<Map<String, Object>> apiResultList = new ArrayList<>();
        currentPayList(key, itemsData[0], apiResultList);

        //ChartItemsInfoRes.GoldoEvent goldoEvent = getGoldo(type, apiResultList);

        for(int i = 0; i < itemsData.length; i++){
            StringBuilder sql = new StringBuilder("WITH RECURSIVE date_series AS ( SELECT DATE_SUB(DATE(NOW()), INTERVAL 1 DAY) AS target_date, 0 AS step UNION ALL SELECT DATE_SUB(target_date, INTERVAL ");

            sql.append(time).append(" DAY), step + 1 FROM date_series WHERE step < ").append(point).append(" ) SELECT ch.* FROM date_series ds LEFT JOIN chart_")
                    .append(itemsData[i].getTypeName()).append("_").append(itemsData[i].getName())
                    .append(" ch ON DATE(ch.date) = ds.target_date ORDER BY ds.target_date ASC");


//            StringBuilder sql = new StringBuilder("(SELECT * FROM lostDB.chart_");
//            sql.append(itemsData[i].getTypeName()).append("_").append(itemsData[i].getName()).append(" order by date desc limit ").append(10).append(") order by date asc");
            List<ChartItemsEntity> result = entityManager.createNativeQuery(sql.toString(), ChartItemsEntity.class).getResultList();
            entityManager.clear();

            searchCurrent : for(Map<String, Object> apiResult : apiResultList ) {
                List<Map<String, Object>> itemsList = (List<Map<String, Object>>) apiResult.get("Items");

                for(Map<String, Object> items : itemsList ) {
                    if ((int) items.get("Id") == itemsData[i].getId()) {

                        chartItemsInfoResList.add(ChartItemsInfoRes.fromEntity(result, itemsData[i].getVisualName(), (int) items.get("CurrentMinPrice"), (int) items.get("RecentPrice"), (String) items.get("Icon")));
                        //                        chartItemsInfoResList.add(ChartItemsInfoRes.fromEntity(result, items.get("Name").toString(), (int) items.get("CurrentMinPrice"), (int) items.get("RecentPrice"), (String) items.get("Icon"), goldoEvent));
                        break searchCurrent;
                    }
                }
            }

        }

        return chartItemsInfoResList;
    }


    /* 어떤 아이템인지 분류, type을 PathVariable로 받아옴
     */
    private ItemsData[] selectType (int type) {
        switch (type) {
            case 1 -> {
                return Book.values();
            }
            case 2 -> {
                return Upgrade.values();
            }
            case 3 -> {
                return Gem.values();
            }
            default -> throw new CustomException(ErrorCode.NONE_ITEM_TYPE);
        }
    }

    private void currentPayList(String key, ItemsData itemsData, List<Map<String, Object>> apiResultList) {
        int k = 1;
        while(true) {
            Map<String, Object> items;
            if(key == null || key.length() < 10) items = (Map<String, Object>) ApiRequest.requestPostAPIPersonal("markets/items", new ApiItemsReq(itemsData, k));
            else {
                items = (Map<String, Object>) ApiRequest.requestPostAPIPersonal("markets/items", new ApiItemsReq(itemsData, k), key);
            }
            apiResultList.add(items);
            if( (int) items.get("TotalCount") <= (k*10) ) break;
            k++;
        }
    }
}
