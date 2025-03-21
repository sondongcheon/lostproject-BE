package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.req.APIreq.ApiBookReq;
import com.lostark.root.auction.db.dto.res.ChartBookInfoRes;
import com.lostark.root.auction.db.entity.ChartBookEntity;
import com.lostark.root.auction.db.entity.ChartGenericEntity;
import com.lostark.root.common.staticMethod.ApiRequest;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChartBookServiceImpl implements ChartBookService {

    private final EntityManager entityManager;

    @Override
    public List<ChartBookInfoRes> getChartBookInfo(String key) {

        List<String> names = List.of("adrenaline", "onehan", "yeahdun", "doldae", "jebat", "gisop", "jiljeng", "tadae", "galdae", "mahee");
        List<String> koreans = List.of("유물 아드레날린 각인서", "유물 원한 각인서", "유물 예리한 둔기 각인서", "유물 돌격대장 각인서", "유물 저주받은 인형 각인서", "유물 기습의 대가 각인서", "유물 질량 증가 각인서", "유물 타격의 대가 각인서", "유물 결투의 대가 각인서", "유물 마나의 흐름 각인서");
        List<Integer> ids = List.of(65203905, 65200505, 65201005, 65203305, 65202805, 65203005, 65203505, 65203705, 65201505, 65203105);


        List<ChartBookInfoRes> chartBookInfoResList = new ArrayList<>();

        for(int i = 0; i < names.size(); i++){
            StringBuilder sql = new StringBuilder("(SELECT * FROM lostDB.chart_book_");
            sql.append(names.get(i)).append(" order by date desc limit ").append(10).append(") order by date asc");
            List<ChartBookEntity> result = entityManager.createNativeQuery(sql.toString(), ChartBookEntity.class).getResultList();
            entityManager.clear();

            searchCurrent : for(int j = 0; j < 6; j++) {
                Map<String, Object> books;
                if(key == null || key.length() < 10) books = (Map<String, Object>) ApiRequest.requestPostAPIPersonal("markets/items", new ApiBookReq(j));
                else {

                    books = (Map<String, Object>) ApiRequest.requestPostAPIPersonal("markets/items", new ApiBookReq(j), key);
                }
                List<Map<String, Object>> items = (List<Map<String, Object>>) books.get("Items");
                for(Map<String, Object> item : items) {
                    if ((int) item.get("Id") == ids.get(i)) {
                        chartBookInfoResList.add(ChartBookInfoRes.fromEntity(result, koreans.get(i), (int) item.get("CurrentMinPrice"), (int) item.get("RecentPrice")));
                        break searchCurrent;
                    }
                }
            }


        }

        return chartBookInfoResList;
    }
}
