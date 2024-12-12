package com.lostark.root.board.service;

import com.lostark.root.board.db.dto.res.NoticeContentRes;
import com.lostark.root.board.db.dto.res.NoticeMainRes;

public interface NoticeService {

    public String test();

    public NoticeMainRes getNoticeMain(int page);

    public NoticeContentRes getNoticeContent(long id);
}
