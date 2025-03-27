package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.res.ChartInfoRes;

public interface ChartJewelService {

    public ChartInfoRes getChartInfo(String name, int level, int time, int point);
}
