package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.res.ChartBookInfoRes;

import java.util.List;

public interface ChartBookService {

    public List<ChartBookInfoRes> getChartBookInfo(String key);
}
