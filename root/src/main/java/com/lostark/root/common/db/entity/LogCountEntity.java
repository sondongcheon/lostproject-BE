package com.lostark.root.common.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity(name = "log_count")
@Getter
public class LogCountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long count_id;

    private String name;
    private long count;
}
