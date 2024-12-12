package com.lostark.root.board.db.repository;

import com.lostark.root.board.db.entity.NoticeCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeCommentRepository extends JpaRepository<NoticeCommentEntity, Long> {

    long countByNotice_NoticeId(long noticeId);
}
