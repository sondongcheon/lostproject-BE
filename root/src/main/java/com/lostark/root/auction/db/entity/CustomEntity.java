package com.lostark.root.auction.db.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class CustomEntity {

    private int price;
    private LocalDateTime create_at;
}
