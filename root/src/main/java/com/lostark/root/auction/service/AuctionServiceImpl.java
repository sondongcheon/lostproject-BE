package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.OptionValueEnum;
import com.lostark.root.auction.db.dto.req.APIreq.ApiAuctionReq;
import com.lostark.root.auction.db.dto.req.SelectOptionReq;
import com.lostark.root.auction.db.dto.res.APIres.ApiAuctionRes;
import com.lostark.root.auction.db.dto.res.SearchFinalRes;
import com.lostark.root.auction.db.dto.res.SearchResultRes;
import com.lostark.root.common.db.repository.LogCountRepository;
import com.lostark.root.exception.CustomException;
import com.lostark.root.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuctionServiceImpl implements AuctionService {

    private final LogCountRepository logCountRepository;
    private final int defaultNumber = 999;

    @Value("${api.public}")
    private String apikey;

    @Override
    public SearchFinalRes getAuctionResult(List<SelectOptionReq> selectOptionReqList, int type, String key) {
        log.info("Start Search");

        //공용키 전환
        if (key.isEmpty() || key == null) key = apikey;
        //조회수
        logCountRepository.incrementCountByName("totalSearch");
        logCountRepository.incrementCountByName("todaySearch");
        boolean[] isExampleBool = new boolean[5];
        List<Integer> boxNumber =
                IntStream.range(0, 5)
                        .filter(i -> selectOptionReqList.get(i).getCategoryCode() == 200000)
                        .peek(i -> isExampleBool[i] = true)
                        .boxed()// int -> integer 변환
                        .collect(Collectors.toList());
        int size = boxNumber.size();

        //경우의 수 받아오기
        ApiAuctionRes[][] searchList = getSearchList(size, selectOptionReqList, boxNumber, type, key);

        List<int[]> permList = new ArrayList<>();

        //특수추적 시 각 등급이 배치될 수 있는 경우의 수 리스트
        perm(boxNumber.stream().mapToInt(Integer::intValue).toArray(), new int[size], new boolean[size], 0, size, size, permList);

        // 부위별 특옵이 2종류, 2가지만 2부위를 DFS 하기 위한 숫자 배열 만
        int[] example = {0, 2, 2, 4, 4};
        List<int[]> numOption = generateCombinations(example, isExampleBool);

        int[] selectNum = searchLowPrice(permList, numOption, size, searchList);

        SearchResultRes[] searchResultRes = new SearchResultRes[5];
        List<SearchResultRes>[] lists = new List[6];

        // 특수추적
        for (int i = 0; i < size; i++) {
            specialSearch(i, selectNum, boxNumber, numOption, permList, searchResultRes, lists, searchList, type);
        }

        // 일반탐색
        for (int i = 0; i < 5; i++) {
            normalSearch(i, isExampleBool, selectOptionReqList, searchResultRes, lists, key, type);
        }

        return new SearchFinalRes(searchResultRes, lists);
    }

    //특수 추적 -> DTO 로  했어야하나 ?
    private void specialSearch(int i, int[] selectNum, List<Integer> boxNumber, List<int[]> numOption, List<int[]> permList, SearchResultRes[] searchResultRes, List<SearchResultRes>[] lists, ApiAuctionRes[][] searchList, int type) {
        //결과 없음 백트래킹
        if (selectNum[0] == defaultNumber && selectNum[1] == defaultNumber ) {
            searchResultRes[boxNumber.get(i)] = SearchResultRes.NoneResult();
            return;
        }
        //같은 부위 일경우 만료 날짜를 기반으로 다음 매물을 채택
        if( i > 0 && searchList[numOption.get(selectNum[1])[i]][permList.get(selectNum[0])[i]].getFirstItemEndDate().equals(searchList[numOption.get(selectNum[1])[i-1]][permList.get(selectNum[0])[i-1]].getFirstItemEndDate())) {
            searchResultRes[boxNumber.get(i)] = SearchResultRes.fromApiRes( searchList[numOption.get(selectNum[1])[i]][permList.get(selectNum[0])[i]], 1, type );
        } else {
            searchResultRes[boxNumber.get(i)] = SearchResultRes.fromApiRes(searchList[numOption.get(selectNum[1])[i]][permList.get(selectNum[0])[i]], 0, type);
        }

        List<SearchResultRes> searchResultResList = new ArrayList<>();
        for (int j = 0; j < searchList[numOption.get(selectNum[1])[i]][permList.get(selectNum[0])[i]].getItems().size(); j++) {
            searchResultResList.add( SearchResultRes.fromApiRes( searchList[numOption.get(selectNum[1])[i]][permList.get(selectNum[0])[i]], j, type) );
        }
        lists[boxNumber.get(i)] = searchResultResList;
    }

    //일반 탐색
    private void normalSearch(int i, boolean[] isExampleBool, List<SelectOptionReq> selectOptionReqList, SearchResultRes[] searchResultRes, List<SearchResultRes>[] lists ,String key, int type) {
        if (isExampleBool[i] || (selectOptionReqList.get(i).getCategoryCode() != 200000 && selectOptionReqList.get(i).getOptionFromList(0) == 0 && selectOptionReqList.get(i).getOptionFromList(1) == 0 && selectOptionReqList.get(i).getOptionFromList(2) == 0)) return;

        ApiAuctionRes response = requestAuction(ApiAuctionReq.fromSelectOption(selectOptionReqList.get(i)), key);
        if (response.getItems() == null) {
            searchResultRes[i] = SearchResultRes.NoneResult();
            return;
        }

        // 같은 옵션 다음 매물 체크
        int duplication = accDuplicateCheck(searchResultRes, response, i);
        searchResultRes[i] = SearchResultRes.fromApiRes(Objects.requireNonNull(response), duplication, type);

        lists[i] = IntStream.range(0, response.getItems().size())
                .mapToObj(j -> SearchResultRes.fromApiRes(Objects.requireNonNull(response), j, type))
                .toList();

    }

    private int accDuplicateCheck (SearchResultRes[] searchResultRes, ApiAuctionRes response, int i) {
        Map<Integer, Integer> indexMap = Map.of(
                1, 2,
                2, 1,
                3, 4,
                4, 3
        );
        // 검사 대상이 없는 경우 0 반환
        if (!indexMap.containsKey(i)) return 0;
        int target = indexMap.get(i);
        if( searchResultRes[target] != null && searchResultRes[target].getAuctionEndDate().equals(Objects.requireNonNull(response).getFirstItemEndDate())) {
            return 1;
        }
        return 0;
    }

    private ApiAuctionRes requestAuction(ApiAuctionReq apiAuctionReq, String key) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("accept", "application/json");
            headers.set("Authorization", "bearer " + key);
            String baseUrl = "https://developer-lostark.game.onstove.com/auctions/items";
            HttpEntity<ApiAuctionReq> requestEntity = new HttpEntity<>(apiAuctionReq, headers);

            return restTemplate.postForEntity(baseUrl, requestEntity, ApiAuctionRes.class).getBody();

        } catch (HttpStatusCodeException exception) {
            throw ApiErrorHandle(exception);
        }
    }

    private CustomException ApiErrorHandle(HttpStatusCodeException exception) {
        int statusCode = exception.getStatusCode().value();
        log.error(exception.getMessage());
        log.error("statusCode : {}", statusCode);
        return switch (statusCode) {
            case 400 -> new CustomException(ErrorCode.NO_PARAMETER);
            case 401 -> new CustomException(ErrorCode.API_KEY_ERROR);
            case 429 -> new CustomException(ErrorCode.TOO_MANY_API_REQUEST);
            case 503 -> new CustomException(ErrorCode.SERVICE_UNAVAILABLE);

            default -> throw new RuntimeException(exception);
        };
    }

    //검색 정보 획득
    private ApiAuctionRes[][] getSearchList(int size, List<SelectOptionReq> selectOptionReqList, List<Integer> boxNumber, int type, String key) {
        // 0 목걸이 ㅣ 1 귀걸이 ㅣ 2 반지
        // 0 미지정 ㅣ 1 하 ㅣ 2 중 ㅣ 3 상
        ApiAuctionRes[][] searchList = new ApiAuctionRes[6][5];
        int[][] rank = new int[size][2];
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < 2; k++) {
                rank[i][k] = selectOptionReqList.get(boxNumber.get(i)).getEtcOptionList().get(k).getValue();
            }
            ApiAuctionReq apiAuctionReq = ApiAuctionReq.fromSelectOption(selectOptionReqList.get(boxNumber.get(i)));
            for(int j = 0; j < 3; j ++) {
                /*  41추피% 42적주피%
                 *   45공% 46무공%
                 *   49치적% 50 치피%
                 */
                apiAuctionReq.setCategoryCode(200010 + (j * 10));
                apiAuctionReq.getEtcOptions().getFirst().setSecondOption(41 + type + (j * 4));
                apiAuctionReq.getEtcOptions().get(1).setSecondOption(42 + type + (j * 4));

                for (int k = 0; k < 2; k++) {
                    // 상상 중중 하하 백트래킹 필요
                    if(rank[i][k%2] != 0) {
                        int value =  OptionValueEnum.getByOptionTierValueLevel(apiAuctionReq.getEtcOptions().getFirst().getSecondOption(), SelectOptionReq.filterTier(apiAuctionReq.getItemTier(), apiAuctionReq.getItemGrade()), rank[i][k % 2]).getValue();
                        apiAuctionReq.getEtcOptions().getFirst().setFirstOption(7);
                        apiAuctionReq.getEtcOptions().getFirst().setMinValue(value);
                        apiAuctionReq.getEtcOptions().getFirst().setMaxValue(value);
                    } else {
                        apiAuctionReq.getEtcOptions().getFirst().setFirstOption(null);
                    }

                    if(rank[i][(k+1)%2] != 0) {

                        int value =  OptionValueEnum.getByOptionTierValueLevel(apiAuctionReq.getEtcOptions().get(1).getSecondOption(), SelectOptionReq.filterTier(apiAuctionReq.getItemTier(), apiAuctionReq.getItemGrade()), rank[i][(k+1)%2]).getValue();
                        apiAuctionReq.getEtcOptions().get(1).setFirstOption(7);
                        apiAuctionReq.getEtcOptions().get(1).setMinValue(value);
                        apiAuctionReq.getEtcOptions().get(1).setMaxValue(value);
                    } else {
                        apiAuctionReq.getEtcOptions().get(1).setFirstOption(null);

                    }

                    searchList[j*2 + k][boxNumber.get(i)] = requestAuction(apiAuctionReq, key);

                }
            }
        }

        return searchList;
    }

    //최저가 탐색
    private int[] searchLowPrice(List<int[]> permList, List<int[]> numOption, int size, ApiAuctionRes[][] searchList) {
        int total = Integer.MAX_VALUE;
        int[] selectNum = new int[] {defaultNumber, defaultNumber};
        for (int i = 0; i < permList.size(); i++) {

            int[] tmpPerm = permList.get(i);
            for (int j = 0; j < numOption.size(); j++) {
                int[] tmpOption = numOption.get(j);
                int tmp = 0;
                for (int k = 0; k < size; k++) {
                    if(searchList[tmpOption[k]][tmpPerm[k]].getItems() == null) {

                        tmp = 99999999;
                        continue;
                    }
                    if( k > 0 && searchList[tmpOption[k-1]][tmpPerm[k-1]].getItems() != null && searchList[tmpOption[k]][tmpPerm[k]].getFirstItemEndDate().equals(searchList[tmpOption[k-1]][tmpPerm[k-1]].getFirstItemEndDate())) {
                        tmp += searchList[tmpOption[k]][tmpPerm[k]].getItems().size() < 2 ? 99999999 : searchList[tmpOption[k]][tmpPerm[k]].getBuyPrice(1);
                    } else {
                        tmp += searchList[tmpOption[k]][tmpPerm[k]].getBuyPrice(0);
                    }
                }
                if( total > tmp) {
                    selectNum[0] = i;
                    selectNum[1] = j;
                    total = tmp;
                }
            }

        }
        if( total > 99999998) {
            selectNum[0] = defaultNumber;
            selectNum[1] = defaultNumber;
        }
        return selectNum;
    }

    // [0,1] , new, new, 0, 2, 2, new
    private void perm(int[] arr, int[] output, boolean[] visited, int depth, int n, int r, List<int[]> result) {
        if (depth == r) {
            result.add(Arrays.copyOf(output, r));
            return;
        }

        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = arr[i];
                perm(arr, output, visited, depth + 1, n, r, result);
                visited[i] = false;
            }
        }
    }

    private static List<int[]> generateCombinations(int[] example, boolean[] isExampleBool) {
        List<int[]> result = new ArrayList<>();
        generateCombinationsRecursive(example, isExampleBool, new ArrayList<>(), 0, result);

        return result;
    }

    private static void generateCombinationsRecursive(int[] example, boolean[] isExampleBool, List<Integer> current, int index, List<int[]> result) {
        // 모든 자리를 처리한 경우
        if (index == example.length) {
            result.add(current.stream().mapToInt(i -> i).toArray());
            return;
        }

        // 현재 자리가 사용되지 않는 경우
        if (!isExampleBool[index]) {
            generateCombinationsRecursive(example, isExampleBool, current, index + 1, result);
            return;
        }

        // 현재 자리가 사용되는 경우: 가능한 조합을 모두 탐색
        if (index == 0) { // 첫 번째 자리
            current.add(0);
            generateCombinationsRecursive(example, isExampleBool, current, index + 1, result);
            current.removeLast();

            current.add(1);
            generateCombinationsRecursive(example, isExampleBool, current, index + 1, result);
            current.removeLast();
        } else if (index == 1 || index == 2) { // 두 번째와 세 번째 자리
            current.add(2);
            generateCombinationsRecursive(example, isExampleBool, current, index + 1, result);
            current.removeLast();

            current.add(3);
            generateCombinationsRecursive(example, isExampleBool, current, index + 1, result);
            current.removeLast();
        } else if (index == 3 || index == 4) { // 네 번째와 다섯 번째 자리
            current.add(4);
            generateCombinationsRecursive(example, isExampleBool, current, index + 1, result);
            current.removeLast();

            current.add(5);
            generateCombinationsRecursive(example, isExampleBool, current, index + 1, result);
            current.removeLast();
        }
    }



}

