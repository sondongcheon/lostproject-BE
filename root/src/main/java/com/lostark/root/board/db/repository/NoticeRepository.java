package com.lostark.root.board.db.repository;

import com.lostark.root.board.db.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
    Optional<NoticeEntity> findTopByOrderByNoticeIdDesc();
}
