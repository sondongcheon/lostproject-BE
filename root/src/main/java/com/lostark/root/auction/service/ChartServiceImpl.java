package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.res.ChartInfoRes;
import com.lostark.root.auction.db.entity.ChartGenericEntity;
import com.lostark.root.common.db.repository.LogCountRepository;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChartServiceImpl implements ChartService {

    private final EntityManager entityManager;
    private final LogCountRepository logCountRepository;

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

    public void loadChartPage(Cookie[] cookies, HttpServletResponse response) {
        for (Cookie value : cookies) {
            if(value.getName().equals("CCP")) return;
        }

        Cookie cookie = new Cookie("CCP", null);
        cookie.setMaxAge(24*60*60);
        cookie.setSecure(true);
        cookie.setPath("/chart");
        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        logCountRepository.incrementCountByName("totalSearch");
        logCountRepository.incrementCountByName("todaySearch");

    }
}
