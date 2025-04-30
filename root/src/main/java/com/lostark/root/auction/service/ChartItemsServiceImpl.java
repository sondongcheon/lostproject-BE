package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.ItemsEnum.Book;
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

    private final EntityManager entityManager;

//    @Override
//    public List<ChartItemsInfoRes> getChartBookInfo(String key) {
//        List<String> names = List.of("adrenaline",
//                "onehan", "yeahdun", "doldae", "jebat", "gisop",
//                "jiljeng", "tadae", "galdae", "mahee", "supercharge",
//                "junmon", "mahwojung", "gacksung", "gudong", "socksock",
//                "bari", "ansang", "junggap", "jangdan", "gpta",
//                "attar", "maxmana", "junggi");
//
//        List<Integer> ids = List.of(65203905,
//                65200505, 65201005, 65203305, 65202805, 65203005,
//                65203505, 65203705, 65201505, 65203105, 65200605,
//                65204105, 65201305, 65203405, 65200805, 65204005,
//                65203205, 65200405, 65202105, 65204305, 65201105,
//                65200305, 65201205, 65200205);
//
//        List<ChartItemsInfoRes> chartItemsInfoResList = new ArrayList<>();
//
//        for(int i = 0; i < names.size(); i++){
//            StringBuilder sql = new StringBuilder("(SELECT * FROM lostDB.chart_book_");
//            sql.append(names.get(i)).append(" order by date desc limit ").append(10).append(") order by date asc");
//            List<ChartItemsEntity> result = entityManager.createNativeQuery(sql.toString(), ChartItemsEntity.class).getResultList();
//            entityManager.clear();
//
//            searchCurrent : for(int j = 1; j < 6; j++) {
//
//                Map<String, Object> books;
//                if(key == null || key.length() < 10) books = (Map<String, Object>) ApiRequest.requestPostAPIPersonal("markets/items", new ApiBookReq(j));
//                else {
//                    books = (Map<String, Object>) ApiRequest.requestPostAPIPersonal("markets/items", new ApiBookReq(j), key);
//                }
//                List<Map<String, Object>> items = (List<Map<String, Object>>) books.get("Items");
//                for(Map<String, Object> item : items) {
//                    if ((int) item.get("Id") == ids.get(i)) {
//                        chartItemsInfoResList.add(ChartItemsInfoRes.fromEntity(result, item.get("Grade").toString() + " " + item.get("Name").toString(), (int) item.get("CurrentMinPrice"), (int) item.get("RecentPrice")));
//                        break searchCurrent;
//                    }
//                }
//            }
//
//
//        }
//
//        return chartItemsInfoResList;
//    }

    @Override
    public List<ChartItemsInfoRes> getChartInfo(String key, int type, int time, int point) {
        ItemsData[] itemsData = selectType(type);
        List<ChartItemsInfoRes> chartItemsInfoResList = new ArrayList<>();

        List<Map<String, Object>> apiResultList = new ArrayList<>();
        int k = 1;
        while(true) {
            Map<String, Object> items;
            if(key == null || key.length() < 10) items = (Map<String, Object>) ApiRequest.requestPostAPIPersonal("markets/items", new ApiItemsReq(itemsData[0], k));
            else {
                items = (Map<String, Object>) ApiRequest.requestPostAPIPersonal("markets/items", new ApiItemsReq(itemsData[0], k), key);
            }
            apiResultList.add(items);
            if( (int) items.get("TotalCount") <= (k*10) ) break;
            k++;
        }

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
                        chartItemsInfoResList.add(ChartItemsInfoRes.fromEntity(result, items.get("Name").toString(), (int) items.get("CurrentMinPrice"), (int) items.get("RecentPrice"), (String) items.get("Icon")));
                        break searchCurrent;
                    }
                }
            }

        }

        return chartItemsInfoResList;
    }

    private ItemsData[] selectType (int type) {
        switch (type) {
            case 1 -> {
                return Book.values();
            }
            case 2 -> {
                return Upgrade.values();
            }
            default -> throw new CustomException(ErrorCode.NONE_ITEM_TYPE);
        }

    }
}
