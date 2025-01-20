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
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChartServiceImpl implements ChartService {

    private final EntityManager entityManager;
    private final LogCountRepository logCountRepository;

    private static final int valTime = 8;

    @Override
    public ChartInfoRes getChartInfo(int tier, String category, String grade, String value, String value2, String type) {

        String sql = "SELECT * FROM (SELECT * FROM " + "chart_" + type + tier + "t_" + category + "_" + grade +"_" + value + value2 + " WHERE HOUR(create_at) % "+ valTime +" = 0 AND MINUTE(create_at) = 0 ORDER BY create_at DESC LIMIT 15 ) AS subquery ORDER BY create_at ASC";

        System.out.println(sql);
        List<ChartGenericEntity> result = entityManager.createNativeQuery(sql, ChartGenericEntity.class).getResultList();
        entityManager.clear();

        List<ChartInfoRes.ChartInfo> chartInfo = result.stream()
                .map(entity -> {
                    LocalDateTime tmp = entity.getCreateAt();
                    return new ChartInfoRes.ChartInfo(
                        tmp.getMonthValue() + "월 " + tmp.getDayOfMonth() +"일 " + tmp.getHour() + "시",
                        entity.getPrice()
                );})
                .toList();

        String Wmin = "SELECT MIN(price) AS lowest_price FROM chart_" + type + tier + "t_" + category + "_" + grade +"_" + value + value2 + " WHERE create_at >= DATE_SUB(NOW(), INTERVAL 7 DAY)";

        int weeklyMin = (int) entityManager.createNativeQuery(Wmin).getSingleResult();
        entityManager.clear();

        String Wavg = "SELECT AVG(price) AS average_price FROM chart_" + type + tier + "t_" + category + "_" + grade + "_" + value + value2 +
                " WHERE create_at >= DATE_SUB(NOW(), INTERVAL 7 DAY)";

        int weeklyAvg = ((BigDecimal) entityManager.createNativeQuery(Wavg).getSingleResult()).intValue();
        entityManager.clear();

        String Mmin = "SELECT MIN(price) AS lowest_price FROM chart_" + type + tier + "t_" + category + "_" + grade +"_" + value + value2 + " WHERE create_at >= DATE_SUB(NOW(), INTERVAL 30 DAY)";

        int monthMin = (int) entityManager.createNativeQuery(Mmin).getSingleResult();
        entityManager.clear();

        String Mavg = "SELECT AVG(price) AS average_price FROM chart_" + type + tier + "t_" + category + "_" + grade + "_" + value + value2 +
                " WHERE create_at >= DATE_SUB(NOW(), INTERVAL 30 DAY)";

        int monthAvg = ((BigDecimal) entityManager.createNativeQuery(Mavg).getSingleResult()).intValue();
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
        String box1 = "", box2 = "", box3 = "", box4 = "", box5 = "";

        if (req.getBox1() != 0) {
            box1 = "    SELECT CONCAT(DATE_FORMAT(create_at, '%Y-%m-%d %H:'), LPAD(FLOOR(MINUTE(create_at) / 10) * 10, 2, '0')) AS create_at, price "
                    + "    FROM " + toTableName(req.getBox1()) + " "
                    + "    WHERE create_at >= DATE_SUB(NOW(), INTERVAL 7 DAY) ";
            if( req.getBox2() != 0 || req.getBox3() != 0 || req.getBox4() != 0 || req.getBox5() != 0) {
                box1 += "    UNION ALL ";
            }
        }

        if (req.getBox2() != 0) {
            box2 = "    SELECT CONCAT(DATE_FORMAT(create_at, '%Y-%m-%d %H:'), LPAD(FLOOR(MINUTE(create_at) / 10) * 10, 2, '0')) AS create_at, price "
                    + "    FROM " + toTableName(req.getBox2()) + " "
                    + "    WHERE create_at >= DATE_SUB(NOW(), INTERVAL 7 DAY) ";
            if( req.getBox3() != 0 || req.getBox4() != 0 || req.getBox5() != 0) {
                box2 += "    UNION ALL ";
            }
        }

        if (req.getBox3() != 0) {
            box3 = "    SELECT CONCAT(DATE_FORMAT(create_at, '%Y-%m-%d %H:'), LPAD(FLOOR(MINUTE(create_at) / 10) * 10, 2, '0')) AS create_at, price "
                    + "    FROM " + toTableName(req.getBox3()) + " "
                    + "    WHERE create_at >= DATE_SUB(NOW(), INTERVAL 7 DAY) ";
            if(req.getBox4() != 0 || req.getBox5() != 0) {
                box3 += "    UNION ALL ";
            }
        }

        if (req.getBox4() != 0) {
            box4 = "    SELECT CONCAT(DATE_FORMAT(create_at, '%Y-%m-%d %H:'), LPAD(FLOOR(MINUTE(create_at) / 10) * 10, 2, '0')) AS create_at, price "
                    + "    FROM " + toTableName(req.getBox4()) + " "
                    + "    WHERE create_at >= DATE_SUB(NOW(), INTERVAL 7 DAY) ";
            if( req.getBox5() != 0) {
                box4 += "    UNION ALL ";
            }
        }

        if (req.getBox5() != 0) {
            box5 = "    SELECT CONCAT(DATE_FORMAT(create_at, '%Y-%m-%d %H:'), LPAD(FLOOR(MINUTE(create_at) / 10) * 10, 2, '0')) AS create_at, price "
                    + "    FROM " + toTableName(req.getBox5()) + " "
                    + "    WHERE create_at >= DATE_SUB(NOW(), INTERVAL 7 DAY) ";
        }

        String unionTable = "SELECT create_at, SUM(price) AS price "
                + "    FROM ( "
                + box1
                + box2
                + box3
                + box4
                + box5
                + ") AS combined_prices ";

        String queryStr = unionTable
                + "WHERE HOUR(create_at) % "+ valTime +" = 0 AND MINUTE(create_at) = 0 "
                + "GROUP BY create_at "
                + "ORDER BY create_at ASC "
                + "LIMIT 15";

        List<Tuple> result = entityManager.createNativeQuery(queryStr, Tuple.class).getResultList();
        entityManager.clear();

        List<ChartInfoRes.ChartInfo> chartInfo = result.stream()
                .map(entity -> {
                    String createAtStr = entity.get("create_at", String.class);
                    LocalDateTime tmp = LocalDateTime.parse(createAtStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    return new ChartInfoRes.ChartInfo(
                            tmp.getMonthValue() + "월 " + tmp.getDayOfMonth() +"일 " + tmp.getHour() + "시",
                            entity.get("price", BigDecimal.class).intValue()
                    );})
                .toList();

        String queryMin = "SELECT MIN(price) AS min "
                + "FROM ( "
                + unionTable
                + "    GROUP BY create_at "
                + ") AS combined_min";


        int weeklyMin = ((BigDecimal) entityManager.createNativeQuery(queryMin).getSingleResult()).intValue();
        entityManager.clear();

        String queryAvg = "SELECT AVG(price) AS avg "
                + "FROM ( "
                + unionTable
                + "    GROUP BY create_at "
                + ") AS combined_avg";

        int weeklyAvg = ((BigDecimal) entityManager.createNativeQuery(queryAvg).getSingleResult()).intValue();
        entityManager.clear();

        return new ChartInfoRes(chartInfo, 0, 0, 0, 0, weeklyMin, (int) (((double) (chartInfo.getLast().getBuyPrice() - weeklyMin) / weeklyMin) * 100),  weeklyAvg, (int) (((double) (chartInfo.getLast().getBuyPrice() - weeklyAvg) / weeklyAvg) * 100), null);

    }

    private String toTableName (int number) {
        CustomChartEnum chartEnum = CustomChartEnum.getByNumber(number);
        return "chart_" + chartEnum.getTier() + "t_" + chartEnum.getCategory() + "_" + chartEnum.getGrade() + "_" + chartEnum.getValue() + chartEnum.getValue2();
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
