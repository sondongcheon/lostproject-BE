package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.CustomChartEnum;
import com.lostark.root.auction.db.dto.req.CustomChartReq;
import com.lostark.root.auction.db.dto.res.ChartInfoRes;
import com.lostark.root.auction.db.entity.ChartGenericEntity;
import com.lostark.root.common.db.repository.LogCountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChartServiceImpl implements ChartService {

    private final EntityManager entityManager;
    private final LogCountRepository logCountRepository;

    private static final int valTime = 8;

    @Override
    public ChartInfoRes getChartInfo(int tier, String category, String grade, String value, String value2, String type, int time, int point) {
        log.info("GetChartInfo");
        StringBuilder sql = new StringBuilder(tier);
        sql.append("SELECT * FROM (SELECT * FROM chart_").append(type).append(tier).append("t_").append(category).append("_").append(grade).append("_").append(value).append(value2).append(" WHERE DAY(create_at) % ").append(time).append(" = DAY(NOW()) % ").append(time).append(" AND HOUR(create_at) = HOUR(NOW()) AND MINUTE(create_at) < 10 ORDER BY create_at DESC LIMIT ").append(point).append(" ) AS subquery ORDER BY create_at ASC");
        List<ChartGenericEntity> result = entityManager.createNativeQuery(sql.toString(), ChartGenericEntity.class).getResultList();
        entityManager.clear();

        List<ChartInfoRes.ChartInfo> chartInfo = result.stream()
                .map(entity -> {
                    LocalDateTime tmp = entity.getCreateAt();
                    return new ChartInfoRes.ChartInfo(
                        tmp.getMonthValue() + "월 " + tmp.getDayOfMonth() +"일 " + tmp.getHour() + "시",
                        entity.getPrice(),
                            entity.getTotalCount()
                );})
                .toList();

        StringBuilder Wmin = new StringBuilder("SELECT MIN(price) AS lowest_price FROM chart_");
        Wmin.append(type).append(tier).append("t_").append(category).append("_").append(grade).append("_").append(value).append(value2).append(" WHERE create_at >= DATE_SUB(NOW(), INTERVAL 7 DAY)");

        int weeklyMin = (int) entityManager.createNativeQuery(Wmin.toString()).getSingleResult();
        entityManager.clear();

        StringBuilder Wavg = new StringBuilder("SELECT AVG(price) AS average_price FROM chart_");
        Wavg.append(type).append(tier).append("t_").append(category).append("_").append(grade).append("_").append(value).append(value2).append(" WHERE create_at >= DATE_SUB(NOW(), INTERVAL 7 DAY)");

        int weeklyAvg = ((BigDecimal) entityManager.createNativeQuery(Wavg.toString()).getSingleResult()).intValue();
        entityManager.clear();

        StringBuilder Mmin = new StringBuilder("SELECT MIN(price) AS lowest_price FROM chart_");
        Mmin.append(type).append(tier).append("t_").append(category).append("_").append(grade).append("_").append(value).append(value2).append(" WHERE create_at >= DATE_SUB(NOW(), INTERVAL 30 DAY)");

        int monthMin = (int) entityManager.createNativeQuery(Mmin.toString()).getSingleResult();
        entityManager.clear();

        StringBuilder Mavg = new StringBuilder("SELECT AVG(price) AS average_price FROM chart_");
        Mavg.append(type).append(tier).append("t_").append(category).append("_").append(grade).append("_").append(value).append(value2).append(" WHERE create_at >= DATE_SUB(NOW(), INTERVAL 30 DAY)");


        int monthAvg = ((BigDecimal) entityManager.createNativeQuery(Mavg.toString()).getSingleResult()).intValue();
        entityManager.clear();

        return new ChartInfoRes(chartInfo,
                monthMin,
                (int) (((double) (chartInfo.getLast().getBuyPrice() - monthMin) / monthMin) * 100), monthAvg,
                (int) (((double) (chartInfo.getLast().getBuyPrice() - monthAvg) / monthAvg) * 100), weeklyMin,
                (int) (((double) (chartInfo.getLast().getBuyPrice() - weeklyMin) / weeklyMin) * 100),  weeklyAvg,
                (int) (((double) (chartInfo.getLast().getBuyPrice() - weeklyAvg) / weeklyAvg) * 100),
                toChartName(tier, category, grade, value, value2, type));

    }

    @Override
    public ChartInfoRes getCustomChartInfo(CustomChartReq req) {
        StringBuilder[] box = new StringBuilder[] {new StringBuilder(), new StringBuilder(), new StringBuilder(), new StringBuilder(), new StringBuilder()};

        if (req.getBox1() != 0) {
            box[0].append("SELECT CONCAT(DATE_FORMAT(create_at, '%Y-%m-%d %H:'), LPAD(FLOOR(MINUTE(create_at) / 10) * 10, 2, '0')) AS create_at, price FROM ")
                    .append(toTableName(req.getBox1()))
                    .append(" WHERE create_at >= DATE_SUB(NOW(), INTERVAL 30 DAY) ");
            if( req.getBox2() != 0 || req.getBox3() != 0 || req.getBox4() != 0 || req.getBox5() != 0) {
                box[0].append("UNION ALL ");
            }
        }

        if (req.getBox2() != 0) {
            box[1].append("SELECT CONCAT(DATE_FORMAT(create_at, '%Y-%m-%d %H:'), LPAD(FLOOR(MINUTE(create_at) / 10) * 10, 2, '0')) AS create_at, price FROM ")
                    .append(toTableName(req.getBox2()))
                    .append(" WHERE create_at >= DATE_SUB(NOW(), INTERVAL 30 DAY) ");
            if( req.getBox3() != 0 || req.getBox4() != 0 || req.getBox5() != 0) {
                box[1].append("UNION ALL ");
            }
        }

        if (req.getBox3() != 0) {
            box[2].append("SELECT CONCAT(DATE_FORMAT(create_at, '%Y-%m-%d %H:'), LPAD(FLOOR(MINUTE(create_at) / 10) * 10, 2, '0')) AS create_at, price FROM ")
                    .append(toTableName(req.getBox3()))
                    .append(" WHERE create_at >= DATE_SUB(NOW(), INTERVAL 30 DAY) ");
            if(req.getBox4() != 0 || req.getBox5() != 0) {
                box[2].append("UNION ALL ");
            }
        }

        if (req.getBox4() != 0) {
            box[3].append("SELECT CONCAT(DATE_FORMAT(create_at, '%Y-%m-%d %H:'), LPAD(FLOOR(MINUTE(create_at) / 10) * 10, 2, '0')) AS create_at, price FROM ")
                    .append(toTableName(req.getBox4()))
                    .append(" WHERE create_at >= DATE_SUB(NOW(), INTERVAL 30 DAY) ");
            if( req.getBox5() != 0) {
                box[3].append("UNION ALL ");
            }
        }

        if (req.getBox5() != 0) {
            box[4].append("SELECT CONCAT(DATE_FORMAT(create_at, '%Y-%m-%d %H:'), LPAD(FLOOR(MINUTE(create_at) / 10) * 10, 2, '0')) AS create_at, price FROM ")
                    .append(toTableName(req.getBox5()))
                    .append(" WHERE create_at >= DATE_SUB(NOW(), INTERVAL 30 DAY) ");
        }

        StringBuilder unionTable = new StringBuilder("SELECT create_at, SUM(price) AS price FROM ( ");
        unionTable.append(box[0]).append(box[1]).append(box[2]).append(box[3]).append(box[4]).append(") AS combined_prices ");

        StringBuilder queryStr = new StringBuilder();
        //queryStr.append(unionTable).append("WHERE HOUR(create_at) % ").append(valTime).append(" = 0 AND MINUTE(create_at) = 0 GROUP BY create_at ORDER BY create_at ASC LIMIT 15");
        queryStr.append(unionTable).append("WHERE DAY(create_at) % 1 = DAY(NOW()) % 1 AND HOUR(create_at) = HOUR(NOW()) AND MINUTE(create_at) < 10 GROUP BY create_at ORDER BY create_at DESC LIMIT 10");

        List<Tuple> result = entityManager.createNativeQuery(queryStr.toString(), Tuple.class).getResultList();
        entityManager.clear();

        Collections.reverse(result);

        List<ChartInfoRes.ChartInfo> chartInfo = result.stream()
                .map(entity -> {
                    String createAtStr = entity.get("create_at", String.class);
                    LocalDateTime tmp = LocalDateTime.parse(createAtStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    return new ChartInfoRes.ChartInfo(
                            tmp.getMonthValue() + "월 " + tmp.getDayOfMonth() +"일 " + tmp.getHour() + "시",
                            entity.get("price", BigDecimal.class).intValue(),0
                    );})
                .toList();

        StringBuilder queryMin = new StringBuilder("SELECT MIN(price) AS min FROM ( ");
        queryMin.append(unionTable).append(" GROUP BY create_at ) AS combined_min");

        int weeklyMin = ((BigDecimal) entityManager.createNativeQuery(queryMin.toString()).getSingleResult()).intValue();
        entityManager.clear();

        StringBuilder queryAvg = new StringBuilder("SELECT AVG(price) AS avg FROM ( ");
        queryAvg.append(unionTable).append(" GROUP BY create_at ) AS combined_avg");

        int weeklyAvg = ((BigDecimal) entityManager.createNativeQuery(queryAvg.toString()).getSingleResult()).intValue();
        entityManager.clear();

        return new ChartInfoRes(chartInfo, 0, 0, 0, 0, weeklyMin, (int) (((double) (chartInfo.getLast().getBuyPrice() - weeklyMin) / weeklyMin) * 100),  weeklyAvg, (int) (((double) (chartInfo.getLast().getBuyPrice() - weeklyAvg) / weeklyAvg) * 100), null);

    }

    private StringBuilder toTableName (int number) {
        CustomChartEnum chartEnum = CustomChartEnum.getByNumber(number);
        return new StringBuilder("chart_").append(chartEnum.getTier()).append("t_").append(chartEnum.getCategory()).append("_").append(chartEnum.getGrade()).append("_").append(chartEnum.getValue()).append(chartEnum.getValue2());
    }

    private String toChartName (int tier, String category, String grade, String value, String value2, String type) {
        StringBuilder name = new StringBuilder(tier + "티어");

        name.append(switch (category) {
            case "neck" -> " 목걸이";
            case "earing" -> " 귀걸이";
            case "ring" -> " 반지";
            default -> null;
        });

        name.append(switch (grade) {
            case "godae" -> " 고대 /";
            case "umuoel" -> " 유물 /";
            default -> null;
        });

        if(!value.equals("x")) {
            name.append(switch (category+type) {
                case "neck" -> " 추피%";
                case "earing" -> " 공%";
                case "ring" -> " 치적%";
                case "necksup_" -> " 아덴+";
                case "ringsup_" -> " 아공강%";
                default -> null;
            });
            name.append(switch (value) {
                case "h" -> " 상 /";
                case "m" -> " 중 /";
                case "l" -> " 하 /";
                default -> null;
            });
        }

        if(!value2.equals("x")) {
            name.append(switch (category+type) {
                case "neck" -> " 적주피%";
                case "earing" -> " 무공%";
                case "ring" -> " 치피%";
                case "necksup_" -> " 낙인력+";
                case "ringsup_" -> " 아피강%";
                default -> null;
            });

            name.append(switch (value2) {
                case "h" -> " 상";
                case "m" -> " 중";
                case "l" -> " 하";
                default -> null;
            });
        }
        return name.toString();

    }

    public void loadChartPage(Cookie[] cookies, HttpServletResponse response) {
        if(cookies != null ) {
            for (Cookie value : cookies) {
                if (value.getName().equals("CCP")) return;
            }
        }

        Cookie cookie = new Cookie("CCP", null);
        cookie.setMaxAge(24*60*60);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        logCountRepository.incrementCountByName("totalLoadChart");
        logCountRepository.incrementCountByName("todayLoadChart");

    }
}
