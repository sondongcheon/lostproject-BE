package com.lostark.root.common.db.repository;

import com.lostark.root.common.db.entity.LogCountEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LogCountRepository extends JpaRepository<LogCountEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE log_count e SET e.count = e.count + 1 WHERE e.name = :name")
    void incrementCountByName(@Param("name") String name);
}
