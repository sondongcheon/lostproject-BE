package com.lostark.root.auction.db.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SearchFinalRes {

    private SearchResultRes[] searchResultRes;
    private List<SearchResultRes>[][] result;
//    private List<SearchResultRes> necklace;
//    private List<SearchResultRes> earring1;
//    private List<SearchResultRes> earring2;
//    private List<SearchResultRes> ring1;
//    private List<SearchResultRes> ring2;
}

