package com.lostark.root.auction.db.dto;

public class testDto<T> {

    public testDto(T[] test, T[] test2) {
        this.test = test;
        this.test2 = test2;
    }

    public testDto(T[] test) {
        this.test = test;

    }

    T[] test;
    T[] test2;
}
