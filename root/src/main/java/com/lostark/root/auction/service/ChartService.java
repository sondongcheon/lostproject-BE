package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.res.ChartInfoRes;

import java.util.List;

public interface ChartService {

    public List<ChartInfoRes> getChartInfo(int tier, String category, String grade, String value, String value2);
}
