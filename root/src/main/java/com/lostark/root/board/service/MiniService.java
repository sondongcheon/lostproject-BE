package com.lostark.root.board.service;

import com.lostark.root.board.db.dto.req.MiniWriteReq;
import com.lostark.root.board.db.dto.res.MiniMainRes;

import java.util.List;

public interface MiniService {

    public List<MiniMainRes> getMiniMain();

    public void setMiniContent(MiniWriteReq miniContent);

}
