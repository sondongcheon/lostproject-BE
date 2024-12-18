package com.lostark.root.board.db.dto.req;

import lombok.Getter;

@Getter
public class NoticeWriteReq {

    private String category;
    private String title;
    private String content;
    private int password;
}
