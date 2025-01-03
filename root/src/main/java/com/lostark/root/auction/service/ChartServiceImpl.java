package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.res.ChartInfoRes;
import com.lostark.root.auction.db.entity.ChartGenericEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChartServiceImpl implements ChartService {

    private final EntityManager entityManager;

    @Override
    public List<ChartInfoRes> getChartInfo(int tier, String category, String grade, String value, String value2) {

        String sql = "SELECT * FROM (SELECT * FROM " + "chart_" + tier + "t_" + category + "_" + grade +"_" + value + value2 + " WHERE HOUR(create_at) % 1 = 0 AND MINUTE(create_at) = 0 ORDER BY create_at DESC LIMIT 20 ) AS subquery ORDER BY create_at ASC";

        List<ChartGenericEntity> result = entityManager.createNativeQuery(sql, ChartGenericEntity.class).getResultList();
        return result.stream()
                .map(entity -> {
                    LocalDateTime tmp = entity.getCreateAt();
                    return new ChartInfoRes(
                        tmp.getMonthValue() + "월 " + tmp.getDayOfMonth() +"일 " + tmp.getHour() + "시",
                        entity.getPrice()
                );})
                .toList();
    }
}
