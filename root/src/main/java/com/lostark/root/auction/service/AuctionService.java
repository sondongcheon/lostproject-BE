package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.req.SelectOptionReq;
import com.lostark.root.auction.db.dto.res.SearchFinalRes;

import java.util.List;

public interface AuctionService {

    public SearchFinalRes getAuctionResult(List<SelectOptionReq> selectOptionReqList, int type, String key);
}
