package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.res.ChartInfoRes;
import com.lostark.root.auction.db.entity.ChartGenericEntity;
import com.lostark.root.auction.db.entity.ChartJewelEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChartJewelServiceImpl implements ChartJewelService {

    /*  주요아이템 차트에 사용되는 서비스 메소드 클래스
     *  보석 type 에 해당하는 정보를 제공하기 위한 클래스
     *  보석은 [거래소] 타입에서 검색하는 정보가 아니기 때문에 클래스가 분리되어 있음
     */

    private final EntityManager entityManager;


    @Override
    public ChartInfoRes getChartInfo(String name, int level, int time, int point) {
        // SQL문 생성, time -> 날짜간격 / point -> 결과갯수(limit) / level -> 어떤 보석인지?(table 이름 결정)
        StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT * FROM chart_jewel_");
        sql.append(name).append("_").append(level).append(" WHERE DAY(create_at) % ").append(time).append(" = DAY(NOW()) % ").append(time).append(" AND HOUR(create_at) = HOUR(NOW()) AND MINUTE(create_at) < 10 ORDER BY create_at DESC LIMIT ").append(point).append(" ) AS subquery ORDER BY create_at ASC");
        List<ChartJewelEntity> result = entityManager.createNativeQuery(sql.toString(), ChartJewelEntity.class).getResultList();
        entityManager.clear();

        // SQL 결과를 가지고 반환값으로 가공
        List<ChartInfoRes.ChartInfo> chartInfo = result.stream()
                .map(entity -> {
                    LocalDateTime tmp = entity.getCreateAt();
                    return new ChartInfoRes.ChartInfo(
                            tmp.getMonthValue() + "월 " + tmp.getDayOfMonth() +"일 " + tmp.getHour() + "시",
                            entity.getPrice(),
                            entity.getTotalCount()
                    );})
                .toList();

        /* 평균가 조회를 위한 SQL 조회
            주간 최소/평균, 월 최소/평균 제공을 위해 총 4가지 조회를 시행
         */
        StringBuilder Wmin = new StringBuilder("SELECT MIN(price) AS lowest_price FROM chart_jewel_");
        Wmin.append(name).append("_").append(level).append(" WHERE create_at >= DATE_SUB(NOW(), INTERVAL 7 DAY)");

        int weeklyMin = (int) entityManager.createNativeQuery(Wmin.toString()).getSingleResult();
        entityManager.clear();

        StringBuilder Wavg = new StringBuilder("SELECT AVG(price) AS average_price FROM chart_jewel_");
        Wavg.append(name).append("_").append(level).append(" WHERE create_at >= DATE_SUB(NOW(), INTERVAL 7 DAY)");

        int weeklyAvg = ((BigDecimal) entityManager.createNativeQuery(Wavg.toString()).getSingleResult()).intValue();
        entityManager.clear();

        StringBuilder Mmin = new StringBuilder("SELECT MIN(price) AS lowest_price FROM chart_jewel_");
        Mmin.append(name).append("_").append(level).append(" WHERE create_at >= DATE_SUB(NOW(), INTERVAL 30 DAY)");

        int monthMin = (int) entityManager.createNativeQuery(Mmin.toString()).getSingleResult();
        entityManager.clear();

        StringBuilder Mavg = new StringBuilder("SELECT AVG(price) AS average_price FROM chart_jewel_");
        Mavg.append(name).append("_").append(level).append(" WHERE create_at >= DATE_SUB(NOW(), INTERVAL 30 DAY)");


        int monthAvg = ((BigDecimal) entityManager.createNativeQuery(Mavg.toString()).getSingleResult()).intValue();
        entityManager.clear();

        return new ChartInfoRes(chartInfo,
                monthMin,
                (int) (((double) (chartInfo.getLast().getBuyPrice() - monthMin) / monthMin) * 100), monthAvg,
                (int) (((double) (chartInfo.getLast().getBuyPrice() - monthAvg) / monthAvg) * 100), weeklyMin,
                (int) (((double) (chartInfo.getLast().getBuyPrice() - weeklyMin) / weeklyMin) * 100),  weeklyAvg,
                (int) (((double) (chartInfo.getLast().getBuyPrice() - weeklyAvg) / weeklyAvg) * 100),
                toChartName(name, level));
    }

    private String toChartName (String EngName, int level) {
        StringBuilder name = new StringBuilder();
        name.append(level).append("레벨 ");

        name.append(switch (EngName) {
            case "jack" -> " 작열";
            case "geop" -> " 겁화";
            default -> null;
        });
        return name.toString();

    }

}
