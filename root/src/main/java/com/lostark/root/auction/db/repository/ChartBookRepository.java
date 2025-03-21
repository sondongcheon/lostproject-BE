package com.lostark.root.auction.db.repository;

import com.lostark.root.auction.db.entity.ChartBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChartBookRepository extends JpaRepository<ChartBookEntity, Long> {
}
