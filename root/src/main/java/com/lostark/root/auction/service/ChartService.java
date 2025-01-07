package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.req.CustomChartReq;
import com.lostark.root.auction.db.dto.res.ChartInfoRes;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface ChartService {

    public ChartInfoRes getChartInfo(int tier, String category, String grade, String value, String value2);

    public ChartInfoRes getCustomChartInfo(CustomChartReq req);

    public void loadChartPage(Cookie[] cookies, HttpServletResponse response);
}
