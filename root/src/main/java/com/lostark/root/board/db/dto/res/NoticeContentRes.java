package com.lostark.root.board.db.dto.res;

import com.lostark.root.board.db.entity.NoticeCommentEntity;
import com.lostark.root.board.db.entity.NoticeEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class NoticeContentRes {

    private long number;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createAt;
    private List<Comment> comentList;

    @Getter
    @Builder
    static public class Comment {
        private String nickname;
        private String content;

        static public Comment fromEntity(NoticeCommentEntity comentEntity) {
            return Comment.builder()
                    .nickname("익명의 유저")
                    .content(comentEntity.getContent())
                    .build();
        }
    }

    static public NoticeContentRes fromEntity(NoticeEntity noticeEntity) {
        return NoticeContentRes.builder()
                .number(noticeEntity.getNoticeId())
                .title(noticeEntity.getTitle())
                .content(noticeEntity.getContent())
                .nickname(noticeEntity.getUser().getNickname())
                .createAt(noticeEntity.getCreateAt())
                .build();
    }
}


