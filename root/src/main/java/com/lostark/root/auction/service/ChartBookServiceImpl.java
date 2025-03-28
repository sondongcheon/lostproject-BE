package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.req.APIreq.ApiBookReq;
import com.lostark.root.auction.db.dto.res.ChartBookInfoRes;
import com.lostark.root.auction.db.entity.ChartBookEntity;
import com.lostark.root.common.staticMethod.ApiRequest;
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
public class ChartBookServiceImpl implements ChartBookService {

    private final EntityManager entityManager;

    @Override
    public List<ChartBookInfoRes> getChartBookInfo(String key) {
        List<String> names = List.of("adrenaline",
                "onehan", "yeahdun", "doldae", "jebat", "gisop",
                "jiljeng", "tadae", "galdae", "mahee", "supercharge",
                "junmon", "mahwojung", "gacksung", "gudong", "socksock",
                "bari", "ansang", "junggap", "jangdan", "gpta",
                "attar", "maxmana", "junggi");

        List<Integer>  ids = List.of(65203905,
                65200505, 65201005, 65203305, 65202805, 65203005,
                65203505, 65203705, 65201505, 65203105, 65200605,
                65204105, 65201305, 65203405, 65200805, 65204005,
                65203205, 65200405, 65202105, 65204305, 65201105,
                65200305, 65201205, 65200205);

        List<ChartBookInfoRes> chartBookInfoResList = new ArrayList<>();

        for(int i = 0; i < names.size(); i++){
            StringBuilder sql = new StringBuilder("(SELECT * FROM lostDB.chart_book_");
            sql.append(names.get(i)).append(" order by date desc limit ").append(10).append(") order by date asc");
            List<ChartBookEntity> result = entityManager.createNativeQuery(sql.toString(), ChartBookEntity.class).getResultList();
            entityManager.clear();

            searchCurrent : for(int j = 1; j < 6; j++) {

                Map<String, Object> books;
                if(key == null || key.length() < 10) books = (Map<String, Object>) ApiRequest.requestPostAPIPersonal("markets/items", new ApiBookReq(j));
                else {
                    books = (Map<String, Object>) ApiRequest.requestPostAPIPersonal("markets/items", new ApiBookReq(j), key);
                }
                List<Map<String, Object>> items = (List<Map<String, Object>>) books.get("Items");
                for(Map<String, Object> item : items) {
                    if ((int) item.get("Id") == ids.get(i)) {
                        chartBookInfoResList.add(ChartBookInfoRes.fromEntity(result, item.get("Grade").toString() + " " + item.get("Name").toString(), (int) item.get("CurrentMinPrice"), (int) item.get("RecentPrice")));
                        break searchCurrent;
                    }
                }
            }


        }

        return chartBookInfoResList;
    }
}
