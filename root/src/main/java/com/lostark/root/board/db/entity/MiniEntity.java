package com.lostark.root.board.db.entity;

import com.lostark.root.board.db.dto.req.MiniWriteReq;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name = "board_mini")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MiniEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long miniId;

    private String unknown;
    private String content;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;

    static public MiniEntity fromReq(MiniWriteReq miniWriteReq) {
        return MiniEntity.builder()
                .unknown("익명의 유저")
                .content(miniWriteReq.getContent())
                .build();
    }
}
