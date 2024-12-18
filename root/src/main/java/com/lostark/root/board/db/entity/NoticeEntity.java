package com.lostark.root.board.db.entity;


import com.lostark.root.board.db.dto.req.NoticeWriteReq;
import com.lostark.root.user.db.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name = "board_notice")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noticeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    private String category;
    private String title;
    private String content;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;

    static public NoticeEntity fromReq(NoticeWriteReq noticeWriteReq) {
        return NoticeEntity.builder()
                .user(UserEntity.builder().userId(1).build())
                .category(noticeWriteReq.getCategory())
                .title(noticeWriteReq.getTitle())
                .content(noticeWriteReq.getContent())
                .build();
    }

}
