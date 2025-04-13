package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.res.ChartItemsInfoRes;

import java.util.List;

public interface ChartItemsService {

//    public List<ChartItemsInfoRes> getChartBookInfo(String key);

    public List<ChartItemsInfoRes> getChartInfo(String key, int type, int time, int point);
}
