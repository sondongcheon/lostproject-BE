package com.lostark.root.board.db.dto.res;

import com.lostark.root.board.db.entity.MiniEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MiniMainRes {

    private long number;
    private String unknown;
    private String content;
    private LocalDateTime createAt;

    static public MiniMainRes fromEntity(MiniEntity miniEntity) {
        return MiniMainRes.builder()
                .number(miniEntity.getMiniId())
                .unknown("익명의 유저")
                .content(miniEntity.getContent())
                .createAt(miniEntity.getCreateAt())
                .build();
    }
}
