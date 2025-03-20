package com.lostark.root.auction.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lostark.root.auction.db.dto.req.SelectOptionReq;
import com.lostark.root.auction.db.dto.res.EquipmentRes;
import com.lostark.root.auction.db.dto.res.SearchFinalRes;
import com.lostark.root.auction.db.dto.res.SearchResultRes;

import java.io.IOException;
import java.util.List;

public interface AuctionService {

    public SearchFinalRes getAuctionResult(List<SelectOptionReq> selectOptionReqList, int type, String key);

    public List<SearchResultRes> getAuctionNextPage(SelectOptionReq selectOptionReq, int type, String key);

    public EquipmentRes[] getEquipment(String key, String name);
}
