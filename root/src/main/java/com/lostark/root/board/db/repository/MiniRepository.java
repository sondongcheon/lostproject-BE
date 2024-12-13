package com.lostark.root.board.db.repository;

import com.lostark.root.board.db.entity.MiniEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MiniRepository  extends JpaRepository<MiniEntity, Long> {
}
