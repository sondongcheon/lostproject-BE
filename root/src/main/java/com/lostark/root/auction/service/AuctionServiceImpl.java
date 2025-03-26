package com.lostark.root.auction.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lostark.root.auction.db.dto.OptionDisplay;
import com.lostark.root.auction.db.dto.OptionValueEnum;
import com.lostark.root.auction.db.dto.StatMinMaxEnum;
import com.lostark.root.auction.db.dto.req.APIreq.ApiAuctionReq;
import com.lostark.root.auction.db.dto.req.SelectOptionReq;
import com.lostark.root.auction.db.dto.res.APIres.ApiAuctionRes;
import com.lostark.root.auction.db.dto.res.APIres.ApiEquipmentRes;
import com.lostark.root.auction.db.dto.res.EquipmentRes;
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
import org.springframework.http.HttpMethod;
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
        if (key == null || key.length() < 10) key = apikey;
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
        List<SearchResultRes>[][] lists = new List[6][1];

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

    @Override
    public List<SearchResultRes> getAuctionNextPage(SelectOptionReq selectOptionReq, int type, String key) {
        log.info("Get NextPage");
        //공용키 전환
        if (key == null || key.length() < 10) key = apikey;

        ApiAuctionRes response = requestAuction(ApiAuctionReq.fromSelectOption(selectOptionReq), key);
        List<ApiAuctionRes.Item> filterRes = new ArrayList<>();

        int[] cnt = new int[] {selectOptionReq.getPageNo(), selectOptionReq.getNumberCount()};

        makeFilterList(filterRes, response, selectOptionReq, cnt, key);

        return IntStream.range(0, filterRes.size())
                .mapToObj(j -> SearchResultRes.fromApiRes(filterRes, j, type, cnt[1], cnt[0]))
                .toList();
    }

    @Override
    public EquipmentRes[] getEquipment(String key, String name) {
        //공용키 전환
        if (key.isEmpty() || key.length() < 10) key = apikey;
        ApiEquipmentRes apiEquipmentRes = requestEquipment(key, name);
        if( apiEquipmentRes == null ) {
            throw new CustomException(ErrorCode.NONE_CHARACTOR);
        }
        log.info("search Character");
        String itemLevel = apiEquipmentRes.getProfile().getItemAvgLevel();
        EquipmentRes[] equipmentRes = new EquipmentRes[] {new EquipmentRes(itemLevel), new EquipmentRes(itemLevel),new EquipmentRes(itemLevel),new EquipmentRes(itemLevel),new EquipmentRes(itemLevel)};
        int i = 0;
        for (ApiEquipmentRes.ArmoryEquipment res : apiEquipmentRes.getEquipment()) {
            ObjectMapper objectMapper = new ObjectMapper();
            if(res.getType().equals("목걸이") || res.getType().equals("귀걸이") || res.getType().equals("반지")){
                equipmentRes[i].setType(res.getType());
                equipmentRes[i].setGrade(res.getGrade());
                String result;
                JsonNode tooltipNode;
                try {
                    tooltipNode = objectMapper.readTree(res.getTooltip());
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                JsonNode findTier = tooltipNode.get("Element_001");
                JsonNode findTierValue = findTier.get("value").get("leftStr2");
                String tierStr = findTierValue.asText().replaceAll("</FONT>", "").replaceAll("<FONT SIZE='14'>아이템 티어 ", "");
                int tier = Integer.parseInt(tierStr);
                JsonNode element = tooltipNode.get("Element_005");

                JsonNode value = element.get("value");
                result = value.get("Element_001").asText();
                result = result.replaceAll("<img.*?></img>", "");

                String[] tmp = result.split("<BR>");

                for (String optionFull : tmp) {
                    
                    String[] tmp2 = optionFull.split(" \\+");
                    equipmentRes[i].getOption().add(tmp2[0]);
                    equipmentRes[i].getValue().add(tmp2[1]);
                    if(tmp2[0].contains("공격력") && !tmp2[0].contains("강화") && tmp2[1].contains("%")) {
                        tmp2[0] += " ";
                    }
                    OptionDisplay nowDisplay = OptionDisplay.getByName(tmp2[0]);
                    OptionValueEnum nowAcc = OptionValueEnum.getByDisplayValue(nowDisplay.getOption(), tmp2[1], tier);
                    equipmentRes[i].addValueLevel(nowAcc.getValueLevel());

                }
                equipmentRes[i].setTier(tier);

                if(tier == 4) {
                    //스텟 추출
                    JsonNode statNode = tooltipNode.get("Element_004");
                    String statText = statNode.get("value").get("Element_001").asText()
                            .replaceAll("<FONT COLOR='.*?'>", "")
                            .replaceAll("힘 \\+\\d+", "")
                            .replaceAll("</FONT>", "")
                            .replaceAll("<BR>", "")
                            .replaceAll("민첩 \\+", "")
                            .replaceAll("지능 \\+\\d+", "")
                            .replaceAll("체력 \\+\\d+", "");
                    //스텟 수치와 부위를 넣어서 스텟, 퍼센트, 등급을 한번에 처리
                    equipmentRes[i].setStats(Double.parseDouble(statText), res.getType());
                }
                i++;
            }
        }
        return equipmentRes;
    }

    //특수 추적 -> DTO 로  했어야하나 ?
    private void specialSearch(int i, int[] selectNum, List<Integer> boxNumber, List<int[]> numOption, List<int[]> permList, SearchResultRes[] searchResultRes, List<SearchResultRes>[][] lists, ApiAuctionRes[][] searchList, int type) {
        //결과 없음 백트래킹
        log.info("SpecialSearch {}", i);
        if (selectNum[0] == defaultNumber && selectNum[1] == defaultNumber ) {
            searchResultRes[boxNumber.get(i)] = SearchResultRes.NoneResult();
            return;
        }
        //같은 부위 일경우 만료 날짜를 기반으로 다음 매물을 채택
        if( i > 0 && searchList[numOption.get(selectNum[1])[i]][permList.get(selectNum[0])[i]].getFirstItemEndDate().equals(searchList[numOption.get(selectNum[1])[i-1]][permList.get(selectNum[0])[i-1]].getFirstItemEndDate())) {
            searchResultRes[boxNumber.get(i)] = SearchResultRes.fromApiRes( searchList[numOption.get(selectNum[1])[i]][permList.get(selectNum[0])[i]].getItems(), 1, type );
        } else {
            searchResultRes[boxNumber.get(i)] = SearchResultRes.fromApiRes(searchList[numOption.get(selectNum[1])[i]][permList.get(selectNum[0])[i]].getItems(), 0, type);
        }

        List<SearchResultRes> searchResultResList = new ArrayList<>();
        for (int j = 0; j < searchList[numOption.get(selectNum[1])[i]][permList.get(selectNum[0])[i]].getItems().size(); j++) {
            searchResultResList.add( SearchResultRes.fromApiRes( searchList[numOption.get(selectNum[1])[i]][permList.get(selectNum[0])[i]].getItems(), j, type) );
        }
        lists[boxNumber.get(i)][0] = searchResultResList;
    }

    //일반 탐색
    private void normalSearch(int i, boolean[] isExampleBool, List<SelectOptionReq> selectOptionReqList, SearchResultRes[] searchResultRes, List<SearchResultRes>[][] lists ,String key, int type) {
        SelectOptionReq selectOptionReq = selectOptionReqList.get(i);
        if (isExampleBool[i] || (selectOptionReq.getCategoryCode() != 200000 && selectOptionReq.getOptionFromList(0) == 0 && selectOptionReq.getOptionFromList(1) == 0 && selectOptionReq.getOptionFromList(2) == 0)) return;

        ApiAuctionRes response = requestAuction(ApiAuctionReq.fromSelectOption(selectOptionReq), key);

        // 조회 결과에서 스텟을 가져와서 필터합니다.
        List<ApiAuctionRes.Item> filterRes = new ArrayList<>();
        // 조회 결과를 돌면서 10개의 결과를 가진 리스트를 채움 cnt[0]은 전달된 페이지 위치, [1]은 몇번째 까지 고려했는지
        int[] cnt = new int[] {selectOptionReq.getPageNo(), selectOptionReq.getNumberCount()};
        makeFilterList(filterRes, response, selectOptionReq, cnt, key);

        if (filterRes.isEmpty()) {
            searchResultRes[i] = SearchResultRes.NoneResult();
            return;
        }

        // 같은 옵션 다음 매물 체크
        int duplication = accDuplicateCheck(searchResultRes, filterRes.getFirst(), i);
        searchResultRes[i] = SearchResultRes.fromApiRes(filterRes, duplication, type, cnt[1], cnt[0]);

        lists[i][0] = IntStream.range(0, filterRes.size())
                .mapToObj(j -> SearchResultRes.fromApiRes(filterRes, j, type))
                .toList();

    }

    //조회 결과들에서 수동필터 반복, cnt[0]은 전달된 페이지 위치, [1]은 몇번째 까지 고려했는지
    private void makeFilterList(List<ApiAuctionRes.Item> filterRes, ApiAuctionRes response, SelectOptionReq selectOptionReq, int[] cnt, String key) {
        while(filterRes.size() < 10 && response.getItems() != null) {
            if ( response.getItems().size() <= cnt[1]) break;
            else if( filterStat(response.getStat(cnt[1]), selectOptionReq.getCategoryCode(), response.getCntUpgrade(cnt[1]), selectOptionReq.getStatPercentage())) {
                filterRes.add(response.getItems().get(cnt[1]));
            }
            // 0~9 순환
            if (cnt[1] == 9) {
                ++cnt[0];
                cnt[1] = 0;
                response = requestAuction(ApiAuctionReq.fromSelectOption(selectOptionReq, cnt[0]), key);
            }
            else ++cnt[1];
        }
    }

    //힘민지 필터
    private boolean filterStat(double currentStat, int categoryCode, int upgrade, int per) {
        StatMinMaxEnum stat = StatMinMaxEnum.getByCategory(categoryCode, upgrade);
        return (currentStat - stat.getMinValue()) / (stat.getMaxValue() - stat.getMinValue()) * 100 >= per;
    }

    private int accDuplicateCheck (SearchResultRes[] searchResultRes, ApiAuctionRes.Item filterRes, int i) {
        Map<Integer, Integer> indexMap = Map.of(
                1, 2,
                2, 1,
                3, 4,
                4, 3
        );
        // 검사 대상이 없는 경우 0 반환
        if (!indexMap.containsKey(i)) return 0;
        int target = indexMap.get(i);
        if( searchResultRes[target] != null && searchResultRes[target].getAuctionEndDate().equals(Objects.requireNonNull(filterRes).getEndDate())) {
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

    private ApiEquipmentRes requestEquipment(String key, String name) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
//            headers.set("Content-Type", "application/json");
            headers.set("accept", "application/json");
            headers.set("Authorization", "bearer " + key);
            String baseUrl = "https://developer-lostark.game.onstove.com/armories/characters/" + name + "?filters=profiles+equipment";
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            return restTemplate.exchange(
                    baseUrl,
                    HttpMethod.GET,
                    requestEntity,
                    ApiEquipmentRes.class
            ).getBody();

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
                rank[i][k] = selectOptionReqList.get(boxNumber.get(i)).getValue(k);
            }
            ApiAuctionReq apiAuctionReq = ApiAuctionReq.fromSelectOption(selectOptionReqList.get(boxNumber.get(i)));
            for(int j = 0; j < 3; j ++) {
                // 부위, Option 효과 설정
                apiAuctionReq.setAccInfo(j, type);

                for (int k = 0; k < 2; k++) {
                    // 상상 중중 하하 백트래킹 필요
                    if(rank[i][k%2] != 0) {
                        int value =  OptionValueEnum.getByOptionTierValueLevel(apiAuctionReq.getSecondOption(0), SelectOptionReq.filterTier(apiAuctionReq.getItemTier(), apiAuctionReq.getItemGrade()), rank[i][k % 2]).getValue();
                        apiAuctionReq.setValue(0, value);
                    } else {
                        apiAuctionReq.getEtcOptions().getFirst().setFirstOption(null);
                    }

                    if(rank[i][(k+1)%2] != 0) {
                        int value =  OptionValueEnum.getByOptionTierValueLevel(apiAuctionReq.getSecondOption(1), SelectOptionReq.filterTier(apiAuctionReq.getItemTier(), apiAuctionReq.getItemGrade()), rank[i][(k+1)%2]).getValue();
                        apiAuctionReq.setValue(1, value);
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

