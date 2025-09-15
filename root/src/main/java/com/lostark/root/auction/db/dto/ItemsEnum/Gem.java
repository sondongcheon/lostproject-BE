package com.lostark.root.auction.db.dto.ItemsEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gem implements ItemsData {

    GOGUB_HONDON_WAEGOK("gogub_hondon_waegok", 67410401, "고급 혼돈의 젬 : 왜곡"),
    GOGUB_HONDON_CHIMSIK("gogub_hondon_chimsik", 67410301, "고급 혼돈의 젬 : 침식"),
    GOGUB_HONDON_BUNGGWE("gogub_hondon_bunggwe", 67410501, "고급 혼돈의 젬 : 붕괴"),
    GOGUB_JILSEO_BULBYEON("gogub_jilseo_bulbyeon", 67400201, "고급 질서의 젬 : 불변"),
    GOGUB_JILSEO_GYEONGO("gogub_jilseo_gyeongo", 67400101, "고급 질서의 젬 : 견고"),
    GOGUB_JILSEO_ANJEONG("gogub_jilseo_anjeong", 67400001, "고급 질서의 젬 : 안정"),

    HEEGWI_HONDON_WAEGOK("heegwi_hondon_waegok", 67410402, "희귀 혼돈의 젬 : 왜곡"),
    HEEGWI_HONDON_CHIMSIK("heegwi_hondon_chimsik", 67410302, "희귀 혼돈의 젬 : 침식"),
    HEEGWI_HONDON_BUNGGWE("heegwi_hondon_bunggwe", 67410502, "희귀 혼돈의 젬 : 붕괴"),
    HEEGWI_JILSEO_BULBYEON("heegwi_jilseo_bulbyeon", 67400202, "희귀 질서의 젬 : 불변"),
    HEEGWI_JILSEO_GYEONGO("heegwi_jilseo_gyeongo", 67400102, "희귀 질서의 젬 : 견고"),
    HEEGWI_JILSEO_ANJEONG("heegwi_jilseo_anjeong", 67400002, "희귀 질서의 젬 : 안정"),

    YOUNGWONG_HONDON_WAEGOK("youngwong_hondon_waegok", 67410403, "영웅 혼돈의 젬 : 왜곡"),
    YOUNGWONG_HONDON_CHIMSIK("youngwong_hondon_chimsik", 67410303, "영웅 혼돈의 젬 : 침식"),
    YOUNGWONG_HONDON_BUNGGWE("youngwong_hondon_bunggwe", 67410503, "영웅 혼돈의 젬 : 붕괴"),
    YOUNGWONG_JILSEO_BULBYEON("youngwong_jilseo_bulbyeon", 67400203, "영웅 질서의 젬 : 불변"),
    YOUNGWONG_JILSEO_GYEONGO("youngwong_jilseo_gyeongo", 67400103, "영웅 질서의 젬 : 견고"),
    YOUNGWONG_JILSEO_ANJEONG("youngwong_jilseo_anjeong", 67400003, "영웅 질서의 젬 : 안정");

    private final String name;
    private final int id;
    private final String visualName;

    public String getTypeName() {
        return "gem";
    }

    public int getCategoryCode() {
        return 50000;
    }

    public int getItemTier() {
        return 0;
    }

    public String getItemGrade() {
        return "";
    }

    public String getItemName() {
        return "의 젬";
    }

}
