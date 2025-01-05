package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.CustomChartEnum;
import com.lostark.root.auction.db.dto.req.CustomChartReq;
import com.lostark.root.auction.db.dto.res.ChartInfoRes;
import com.lostark.root.auction.db.entity.ChartGenericEntity;
import com.lostark.root.common.db.repository.LogCountRepository;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChartServiceImpl implements ChartService {

    private final EntityManager entityManager;
    private final LogCountRepository logCountRepository;

    @Override
    public List<ChartInfoRes> getChartInfo(int tier, String category, String grade, String value, String value2) {

        String sql = "SELECT * FROM (SELECT * FROM " + "chart_" + tier + "t_" + category + "_" + grade +"_" + value + value2 + " WHERE HOUR(create_at) % 4 = 0 AND MINUTE(create_at) = 0 ORDER BY create_at DESC LIMIT 20 ) AS subquery ORDER BY create_at ASC";

        List<ChartGenericEntity> result = entityManager.createNativeQuery(sql, ChartGenericEntity.class).getResultList();
        entityManager.clear();
        return result.stream()
                .map(entity -> {
                    LocalDateTime tmp = entity.getCreateAt();
                    return new ChartInfoRes(
                        tmp.getMonthValue() + "월 " + tmp.getDayOfMonth() +"일 " + tmp.getHour() + "시",
                        entity.getPrice()
                );})
                .toList();
    }

    @Override
    public List<ChartInfoRes> getCustomChartInfo(CustomChartReq req) {
        List<List<ChartInfoRes>> getInfo = new ArrayList<>();

        List<CustomChartEnum> chartEnums = new ArrayList<>();
        chartEnums.add(CustomChartEnum.getByNumber(req.getBox1()));
        chartEnums.add(CustomChartEnum.getByNumber(req.getBox2()));
        chartEnums.add(CustomChartEnum.getByNumber(req.getBox3()));
        chartEnums.add(CustomChartEnum.getByNumber(req.getBox4()));
        chartEnums.add(CustomChartEnum.getByNumber(req.getBox5()));

        for (CustomChartEnum customInfo : chartEnums) {
            if(customInfo.getNumber() == 0) continue;

            getInfo.add(getChartInfo(customInfo.getTier(), customInfo.getCategory(), customInfo.getGrade(), customInfo.getValue(), customInfo.getValue2()));
        }

        List<ChartInfoRes> result = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            ChartInfoRes newInfo = new ChartInfoRes();
            for (List<ChartInfoRes> info : getInfo) {
                if(info.size() <= i) continue;
                newInfo.setBuyPrice(newInfo.getBuyPrice() + info.get(i).getBuyPrice());
                newInfo.setTime(info.get(i).getTime());
            }

            result.add(newInfo);
        }

        return result;
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
