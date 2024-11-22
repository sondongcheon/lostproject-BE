package com.lostark.root.action.service;

import com.lostark.root.action.db.dto.req.SearchOptionReq;
import com.lostark.root.action.db.dto.res.APIres.ApiAuctionResponse;

public interface ActionService {

    public ApiAuctionResponse getActionResult(SearchOptionReq searchOptionReq);
}
