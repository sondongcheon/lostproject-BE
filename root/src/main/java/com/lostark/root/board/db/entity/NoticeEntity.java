package com.lostark.root.board.db.entity;


import com.lostark.root.user.db.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
