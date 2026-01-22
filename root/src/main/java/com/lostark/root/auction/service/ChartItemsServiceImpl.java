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

        List<Map<String, Object>> apiResultList = new ArrayList<>();
        // 현재 타입의 실시간 가격 정보를 가져옴
        currentPayList(key, itemsData[0], apiResultList);

        // 물건마다 sql 조회 + 최종결과 만들기
        for(int i = 0; i < itemsData.length; i++){
            //SQL문 생성, DATE(NOW()), INTERVAL 1 DAY -> 어제부터 / time -> 몇일 간격 / point -> 몇개의 결과 (limit)
            StringBuilder sql = new StringBuilder("WITH RECURSIVE date_series AS ( SELECT DATE_SUB(DATE(NOW()), INTERVAL 1 DAY) AS target_date, 0 AS step UNION ALL SELECT DATE_SUB(target_date, INTERVAL ");

            sql.append(time).append(" DAY), step + 1 FROM date_series WHERE step < ").append(point).append(" ) SELECT ch.* FROM date_series ds LEFT JOIN chart_")
                    // table 이름을 완성하기 위한 append, itemsData가 interface화 되어있어 get 메소드가 자연스럽게 연결되는 구조
                    .append(itemsData[i].getTypeName()).append("_").append(itemsData[i].getName())
                    .append(" ch ON DATE(ch.date) = ds.target_date ORDER BY ds.target_date ASC");

            List<ChartItemsEntity> result = entityManager.createNativeQuery(sql.toString(), ChartItemsEntity.class).getResultList();
            entityManager.clear();

            /* 반복문 설명, 현재 i 순서에 맞는 조회결과와 i 순서에 해당하는 물건의 실시간 결과를 결합하여 최종 결과를 만들어냄 ChartItemsInfoRes.fromEntity 메소드 참조
               현재 apiResultList에는 '각인서'로 검색한 api 결과들이 들어있음, LIST인 이유는 각인서로 검색한 결과 1페이지, 2페이지 ... 이런 결과들을 다 넣었기 때문 (api 호출을 최소화 하기위한 구조)
               searchCurrent : for(Map<String, Object> apiResult : apiResultList )는 각 페이지를 꺼냄

               for(Map<String, Object> items : itemsList ) {
                    if ((int) items.get("Id") == itemsData[i].getId())는 꺼낸 페이지에서 지금 순서에 맞는 물건을 찾아서 맞으면 메소드를 시작, 찾았으니 break
               최초의 for문으로 돌아가서 다음 물건의 sql 조회부터 다시
             */
            searchCurrent : for(Map<String, Object> apiResult : apiResultList ) {
                List<Map<String, Object>> itemsList = (List<Map<String, Object>>) apiResult.get("Items");

                for(Map<String, Object> items : itemsList ) {
                    if ((int) items.get("Id") == itemsData[i].getId()) {

                        chartItemsInfoResList.add(ChartItemsInfoRes.fromEntity(result, itemsData[i].getVisualName(), (int) items.get("CurrentMinPrice"), (int) items.get("RecentPrice"), (String) items.get("Icon")));
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

    /* 같은 종류의 현재 가격정보를 수집 ( ex. 각인서 들, 젬 들 )
     * currentPayList 메소드를 통해 apiResultList에 저장
     */
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
