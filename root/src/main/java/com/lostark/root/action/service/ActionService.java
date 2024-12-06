package com.lostark.root.action.service;

import com.lostark.root.action.db.dto.req.SearchOptionReq;
import com.lostark.root.action.db.dto.req.SelectOptionReq;
import com.lostark.root.action.db.dto.res.APIres.ApiAuctionRes;
import com.lostark.root.action.db.dto.res.SearchFinalRes;
import com.lostark.root.action.db.dto.res.SearchResultRes;

import java.util.List;

public interface ActionService {

    public ApiAuctionRes getActionResult(SearchOptionReq searchOptionReq);

    public ApiAuctionRes getActionResult2(List<SearchOptionReq> searchOptionReqList);

    public ApiAuctionRes getActionResult3(List<SearchOptionReq> searchOptionReqList);

    public SearchResultRes[] getActionResult4(List<SelectOptionReq> selectOptionReqList);

    public SearchFinalRes getActionResult5(List<SelectOptionReq> selectOptionReqList);
}
