package com.lostark.root.board.db.dto.res;

import com.lostark.root.board.db.entity.NoticeEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class NoticeMainRes {

    private int page;
    private List<ContentList> contentList;

    @Getter
    @Builder
    static public class ContentList {
        private long number;
        private String category;
        private String title;
        private String writer;
        private long commentCount;
        private LocalDateTime createAt;

        static public ContentList fromEntity(NoticeEntity noticeEntity, long commentCount) {
            return ContentList.builder().number(noticeEntity.getNoticeId())
                    .category(noticeEntity.getCategory())
                    .title(noticeEntity.getTitle())
                    .writer(noticeEntity.getUser().getNickname())
                    .commentCount(commentCount)
                    .createAt(noticeEntity.getCreateAt())
                    .build();
        }
    }

}
