package com.lostark.root.board.service;

import com.lostark.root.board.db.dto.req.MiniWriteReq;
import com.lostark.root.board.db.dto.req.NoticeWriteReq;
import com.lostark.root.board.db.dto.res.MiniMainRes;
import com.lostark.root.board.db.dto.res.NoticeContentRes;
import com.lostark.root.board.db.dto.res.NoticeMainRes;

import java.util.List;

public interface NoticeService {

    public String test();

    public NoticeMainRes getNoticeMain(int page);

    public NoticeContentRes getNoticeContent(long id);

    public long setNoticeContent(NoticeWriteReq noticeWriteReq);

}
