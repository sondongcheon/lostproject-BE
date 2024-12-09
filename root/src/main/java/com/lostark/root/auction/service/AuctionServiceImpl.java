package com.lostark.root.auction.service;

import com.lostark.root.auction.db.dto.req.APIreq.ApiAuctionReq;
import com.lostark.root.auction.db.dto.req.SelectOptionReq;
import com.lostark.root.auction.db.dto.res.APIres.ApiAuctionRes;
import com.lostark.root.auction.db.dto.res.SearchFinalRes;
import com.lostark.root.auction.db.dto.res.SearchResultRes;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
public class AuctionServiceImpl implements AuctionService {

    @Override
    public SearchFinalRes getAuctionResult(List<SelectOptionReq> selectOptionReqList, int type) {
        boolean[] isExampleBool = new boolean[5];
        List<Integer> boxNumber =
                IntStream.range(0, 5)
                        .filter(i -> selectOptionReqList.get(i).getCategoryCode() == 200000)
                        .peek(i -> isExampleBool[i] = true)
                        .boxed()// int를 Integer로 변환
                        .collect(Collectors.toList());
        int size = boxNumber.size();

        ApiAuctionRes[][] searchList = getSearchList(size, selectOptionReqList, boxNumber, type);

        List<int[]> permList = new ArrayList<>();

        perm(boxNumber.stream().mapToInt(Integer::intValue).toArray(), new int[size], new boolean[size], 0, size, size, permList);

        int[] example = {0, 2, 2, 4, 4};
        List<int[]> numOption = generateCombinations(example, isExampleBool);

        int[] selectNum = searchLowPrice(permList, numOption, size, searchList);

        SearchResultRes[] searchResultRes = new SearchResultRes[5];
        List<SearchResultRes>[] lists = new List[6];

        for (int i = 0; i < size; i++) {
            if( i > 0 && searchList[numOption.get(selectNum[1])[i]][permList.get(selectNum[0])[i]].getItems().getFirst().getAuctionInfo().getEndDate().equals(searchList[numOption.get(selectNum[1])[i-1]][permList.get(selectNum[0])[i-1]].getItems().getFirst().getAuctionInfo().getEndDate())) {
                searchResultRes[boxNumber.get(i)] = SearchResultRes.fromApiRes( searchList[numOption.get(selectNum[1])[i]][permList.get(selectNum[0])[i]], 1 );
            } else {
                searchResultRes[boxNumber.get(i)] = SearchResultRes.fromApiRes(searchList[numOption.get(selectNum[1])[i]][permList.get(selectNum[0])[i]], 0);
            }

            List<SearchResultRes> searchResultResList = new ArrayList<>();
            for (int j = 0; j < searchList[numOption.get(selectNum[1])[i]][permList.get(selectNum[0])[i]].getItems().size(); j++) {
                searchResultResList.add( SearchResultRes.fromApiRes( searchList[numOption.get(selectNum[1])[i]][permList.get(selectNum[0])[i]], j) );
            }
            lists[boxNumber.get(i)] = searchResultResList;
        }

        for (int i = 0; i < 5; i++) {
            if (isExampleBool[i] || (selectOptionReqList.get(i).getCategoryCode() != 200000 && selectOptionReqList.get(i).getEtcOptionList().get(0).getOption() == 0 && selectOptionReqList.get(i).getEtcOptionList().get(1).getOption() == 0 && selectOptionReqList.get(i).getEtcOptionList().get(2).getOption() == 0)) continue;

            ApiAuctionRes response = requestAuction(ApiAuctionReq.fromSelectOption(selectOptionReqList.get(i)));

            int duplication = 0;
            if( i == 1 && searchResultRes[2] != null && searchResultRes[2].getAuctionInfo().getEndDate().equals(Objects.requireNonNull(response).Items.getFirst().getAuctionInfo().getEndDate())) {
                duplication = 1;
            } else if (i == 2 && searchResultRes[1] != null && searchResultRes[1].getAuctionInfo().getEndDate().equals(Objects.requireNonNull(response).Items.getFirst().getAuctionInfo().getEndDate())) {
                duplication = 1;
            } else if (i == 3 && searchResultRes[4] != null && searchResultRes[4].getAuctionInfo().getEndDate().equals(Objects.requireNonNull(response).Items.getFirst().getAuctionInfo().getEndDate())) {
                duplication = 1;
            } else if (i == 4 && searchResultRes[3] != null && searchResultRes[3].getAuctionInfo().getEndDate().equals(Objects.requireNonNull(response).Items.getFirst().getAuctionInfo().getEndDate())) {
                duplication = 1;
            }
            searchResultRes[i] = SearchResultRes.fromApiRes(Objects.requireNonNull(response), duplication);

            List<SearchResultRes> searchResultResList = new ArrayList<>();
            for (int j = 0; j < response.getItems().size(); j++) {
                searchResultResList.add( SearchResultRes.fromApiRes( Objects.requireNonNull(response), j) );
            }
            lists[i] = searchResultResList;

        }

        return new SearchFinalRes(searchResultRes, lists);
    }




    private ApiAuctionRes requestAuction(ApiAuctionReq apiAuctionReq) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("accept", "application/json");
        headers.set("Authorization", "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAxNzcyMzYifQ.WuwGUQh2MPRf0I_Z8mX8RGE5a3qhVAgAg8yTDT4URZ2NpTe_23SIlJwQjxidQxEOsJTrZLh7hrSD4ZpE1I-_bt_qC5SqkDvT7nXV13wlp_Jgpm8YgdmfJkZ1vFIIsNISJHrIKUh27i8qg7o_Zayip7kC0vbjaWLGK5gCMLLqfr40toc40zv31aT7irrkwnfL6W8TjtD9zVrJxPOrGGMBNpoeseUKb-ZCGp9-_D7oPwKXuJhs0XDGQji6aJoZFh3Mzo_EuH9EEThgw3lMleT7uPZEvyv-KxM-x2YBBVQ7T26MesZ2P4_OAUkU9h2D3_sj4QkiKdmv1H8zIiYq8B10ZQ");
        String baseUrl = "https://developer-lostark.game.onstove.com/auctions/items";
        HttpEntity<ApiAuctionReq> requestEntity = new HttpEntity<>(apiAuctionReq, headers);

        return restTemplate.postForEntity(baseUrl, requestEntity, ApiAuctionRes.class).getBody();
    }

    //검색 정보 획득
    private ApiAuctionRes[][] getSearchList(int size, List<SelectOptionReq> selectOptionReqList, List<Integer> boxNumber, int type) {
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
//                int maxValue = searchOptionReqList.get(i).getEtcOptions().getFirst().getMaxValue();
                //중복 조회 방지
//                if (searchList[j][rank[i][0]][rank[i][1]] != null) continue;
//                if ( ( j == 0 && !isExampleBool[0]) || ( j == 1 && !isExampleBool[1] && !isExampleBool[2] ) || ( j == 2 && !isExampleBool[3] && !isExampleBool[4] )) continue;

                /*  41추피% 42적주피%
                 *   45공% 46무공%
                 *   49치적% 50 치피%
                 */
                apiAuctionReq.setCategoryCode(200010 + (j * 10));
                apiAuctionReq.getEtcOptions().getFirst().setSecondOption(41 + type + (j * 4));
                apiAuctionReq.getEtcOptions().getLast().setSecondOption(42 + type + (j * 4));


                for (int k = 0; k < 2; k++) {
                    // 상상 중중 하하 백트래킹 필요
                    if(rank[i][k%2] != 0) {
                        apiAuctionReq.getEtcOptions().getFirst().setFirstOption(7);
                        apiAuctionReq.getEtcOptions().getFirst().setMinValue(rank[i][k % 2]);
                        apiAuctionReq.getEtcOptions().getFirst().setMaxValue(rank[i][k % 2]);
                    } else {
                        apiAuctionReq.getEtcOptions().getFirst().setFirstOption(null);
                    }

                    if(rank[i][(k+1)%2] != 0) {
                        apiAuctionReq.getEtcOptions().getLast().setFirstOption(7);
                        apiAuctionReq.getEtcOptions().getLast().setMinValue(rank[i][(k+1)%2]);
                        apiAuctionReq.getEtcOptions().getLast().setMaxValue(rank[i][(k+1)%2]);
                    } else {
                        apiAuctionReq.getEtcOptions().getLast().setFirstOption(null);
                    }

                    searchList[j*2 + k][boxNumber.get(i)] = requestAuction(apiAuctionReq);
                }
            }
        }

        return searchList;
    }

    //최저가 탐색
    private int[] searchLowPrice(List<int[]> permList, List<int[]> numOption, int size, ApiAuctionRes[][] searchList) {
        int total = Integer.MAX_VALUE;
        int[] selectNum = new int[] {999, 999};
        for (int i = 0; i < permList.size(); i++) {

            int[] tmpPerm = permList.get(i);
            for (int j = 0; j < numOption.size(); j++) {
                int[] tmpOption = numOption.get(j);
                int tmp = 0;
                for (int k = 0; k < size; k++) {
                    if( k > 0 && searchList[tmpOption[k]][tmpPerm[k]].getItems().getFirst().getAuctionInfo().getEndDate().equals(searchList[tmpOption[k-1]][tmpPerm[k-1]].getItems().getFirst().getAuctionInfo().getEndDate())) {
                        tmp += searchList[tmpOption[k]][tmpPerm[k]].getItems().get(1).getAuctionInfo().getBuyPrice();
                    } else {
                        tmp += searchList[tmpOption[k]][tmpPerm[k]].getItems().getFirst().getAuctionInfo().getBuyPrice();
                    }
                }
                if( total > tmp) {
                    selectNum[0] = i;
                    selectNum[1] = j;
                    total = tmp;
                }
            }

        }
        return selectNum;
    }

    // [0,1] , new, new, 0, 2, 2, new
    private void perm(int[] arr, int[] output, boolean[] visited, int depth, int n, int r, List<int[]> result) {
        if (depth == r) {
//            System.out.println("output = " + Arrays.toString(output));
            result.add(Arrays.copyOf(output, r));
            return;
        }

        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = arr[i];
                perm(arr, output, visited, depth + 1, n, r, result);
//                output[depth] = 0;
                visited[i] = false;
            }
        }
    }

    private static List<int[]> generateCombinations(int[] example, boolean[] isExampleBool) {
        List<int[]> result = new ArrayList<>();

        // 조합 생성 (DFS 방식)
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
        int value = example[index];
        if (index == 0) { // 첫 번째 자리
            current.add(0);
            generateCombinationsRecursive(example, isExampleBool, current, index + 1, result);
            current.remove(current.size() - 1);

            current.add(1);
            generateCombinationsRecursive(example, isExampleBool, current, index + 1, result);
            current.remove(current.size() - 1);
        } else if (index == 1 || index == 2) { // 두 번째와 세 번째 자리
            current.add(2);
            generateCombinationsRecursive(example, isExampleBool, current, index + 1, result);
            current.remove(current.size() - 1);

            current.add(3);
            generateCombinationsRecursive(example, isExampleBool, current, index + 1, result);
            current.remove(current.size() - 1);
        } else if (index == 3 || index == 4) { // 네 번째와 다섯 번째 자리
            current.add(4);
            generateCombinationsRecursive(example, isExampleBool, current, index + 1, result);
            current.remove(current.size() - 1);

            current.add(5);
            generateCombinationsRecursive(example, isExampleBool, current, index + 1, result);
            current.remove(current.size() - 1);
        }
    }



}

