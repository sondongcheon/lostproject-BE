package com.lostark.root.auction.db.repository;

import com.lostark.root.auction.db.entity.ChartGenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartGenericRepository extends JpaRepository<ChartGenericEntity, Long> {

}
