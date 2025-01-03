package com.lostark.root.auction.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomChartEnum {

    T4_NECK_G_HH(1, 4, "neck", "godae", "h", "h"),
    T4_NECK_G_MM(2, 4, "neck", "godae", "m", "m"),
    T4_NECK_G_LL(3, 4, "neck", "godae", "l", "l"),
    T4_NECK_G_HX(4, 4, "neck", "godae", "h", "x"),
    T4_NECK_G_XH(5, 4, "neck", "godae", "x", "h"),
    T4_NECK_G_MX(6, 4, "neck", "godae", "m", "x"),
    T4_NECK_G_XM(7, 4, "neck", "godae", "x", "m"),

    T4_NECK_U_HH(8, 4, "neck", "umuoel", "h", "h"),
    T4_NECK_U_MM(9, 4, "neck", "umuoel", "m", "m"),
    T4_NECK_U_LL(10, 4, "neck", "umuoel", "l", "l"),
    T4_NECK_U_HX(11, 4, "neck", "umuoel", "h", "x"),
    T4_NECK_U_XH(12, 4, "neck", "umuoel", "x", "h"),
    T4_NECK_U_MX(13, 4, "neck", "umuoel", "m", "x"),
    T4_NECK_U_XM(14, 4, "neck", "umuoel", "x", "m"),

    T4_EARING_G_HH(101, 4, "earing", "godae", "h", "h"),
    T4_EARING_G_MM(102, 4, "earing", "godae", "m", "m"),
    T4_EARING_G_LL(103, 4, "earing", "godae", "l", "l"),
    T4_EARING_G_HX(104, 4, "earing", "godae", "h", "x"),
    T4_EARING_G_XH(105, 4, "earing", "godae", "x", "h"),
    T4_EARING_G_MX(106, 4, "earing", "godae", "m", "x"),
    T4_EARING_G_XM(107, 4, "earing", "godae", "x", "m"),

    T4_EARING_U_HH(108, 4, "earing", "umuoel", "h", "h"),
    T4_EARING_U_MM(109, 4, "earing", "umuoel", "m", "m"),
    T4_EARING_U_LL(110, 4, "earing", "umuoel", "l", "l"),
    T4_EARING_U_HX(111, 4, "earing", "umuoel", "h", "x"),
    T4_EARING_U_XH(112, 4, "earing", "umuoel", "x", "h"),
    T4_EARING_U_MX(113, 4, "earing", "umuoel", "m", "x"),
    T4_EARING_U_XM(114, 4, "earing", "umuoel", "x", "m"),

    T4_RING_G_HH(201, 4, "ring", "godae", "h", "h"),
    T4_RING_G_MM(202, 4, "ring", "godae", "m", "m"),
    T4_RING_G_LL(203, 4, "ring", "godae", "l", "l"),
    T4_RING_G_HX(204, 4, "ring", "godae", "h", "x"),
    T4_RING_G_XH(205, 4, "ring", "godae", "x", "h"),
    T4_RING_G_MX(206, 4, "ring", "godae", "m", "x"),
    T4_RING_G_XM(207, 4, "ring", "godae", "x", "m"),

    T4_RING_U_HH(208, 4, "ring", "umuoel", "h", "h"),
    T4_RING_U_MM(209, 4, "ring", "umuoel", "m", "m"),
    T4_RING_U_LL(210, 4, "ring", "umuoel", "l", "l"),
    T4_RING_U_HX(211, 4, "ring", "umuoel", "h", "x"),
    T4_RING_U_XH(212, 4, "ring", "umuoel", "x", "h"),
    T4_RING_U_MX(213, 4, "ring", "umuoel", "m", "x"),
    T4_RING_U_XM(214, 4, "ring", "umuoel", "x", "m"),

    NULL_OPTION(0,0,"","", "", "");


    private final int number;
    private final int tier;
    private final String category;
    private final String grade;
    private final String value;
    private final String value2;

    public static CustomChartEnum getByNumber(int number) {
        for (CustomChartEnum item : CustomChartEnum.values()) {
            if (item.getNumber() == number) {
                return item; // 일치하는 항목 반환
            }
        }
        return NULL_OPTION; // 일치하는 항목이 없으면 null 반환
    }
}
