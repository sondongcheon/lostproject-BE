package com.lostark.root.auction.db.repository;

import com.lostark.root.auction.db.entity.ChartItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChartItemsRepository extends JpaRepository<ChartItemsEntity, Long> {
}
