package com.lostark.root.action.service;

import com.lostark.root.action.db.dto.req.APIreq.ApiAuctionReq;
import com.lostark.root.action.db.dto.req.SearchOptionReq;
import com.lostark.root.action.db.dto.req.SelectOptionReq;
import com.lostark.root.action.db.dto.res.APIres.ApiAuctionRes;
import com.lostark.root.action.db.dto.res.SearchResultRes;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


@Service
public class ActionServiceImpl implements ActionService {

    @Override
    public ApiAuctionRes getActionResult(SearchOptionReq searchOptionReq) {
        System.out.println("확인용");
        System.out.println(searchOptionReq.toString());
        System.out.println("확인용");
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("accept", "application/json");
        headers.set("Authorization", "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAxNzcyMzYifQ.WuwGUQh2MPRf0I_Z8mX8RGE5a3qhVAgAg8yTDT4URZ2NpTe_23SIlJwQjxidQxEOsJTrZLh7hrSD4ZpE1I-_bt_qC5SqkDvT7nXV13wlp_Jgpm8YgdmfJkZ1vFIIsNISJHrIKUh27i8qg7o_Zayip7kC0vbjaWLGK5gCMLLqfr40toc40zv31aT7irrkwnfL6W8TjtD9zVrJxPOrGGMBNpoeseUKb-ZCGp9-_D7oPwKXuJhs0XDGQji6aJoZFh3Mzo_EuH9EEThgw3lMleT7uPZEvyv-KxM-x2YBBVQ7T26MesZ2P4_OAUkU9h2D3_sj4QkiKdmv1H8zIiYq8B10ZQ");
        String baseUrl = "https://developer-lostark.game.onstove.com/auctions/items";
        HttpEntity<SearchOptionReq> requestEntity = new HttpEntity<>(searchOptionReq, headers);

        ResponseEntity<ApiAuctionRes> response = restTemplate.postForEntity(baseUrl, requestEntity, ApiAuctionRes.class);
        System.out.println("Response: " + response.getBody());

        return response.getBody();
    }

    @Override
    public ApiAuctionRes getActionResult2(List<SearchOptionReq> searchOptionReqList) {
        System.out.println("searchOptionReqList.size() = " + searchOptionReqList.size());
        System.out.println("searchOptionReqList = " + searchOptionReqList.getFirst().toString());
        System.out.println("searchOptionReqList = " + searchOptionReqList.getLast().toString());
        int size = searchOptionReqList.size();
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i ++) {
            arr[i] = i;
        }
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

        List<int[]> permList = new ArrayList<>();
        perm(arr, new int[size], new boolean[size], 0, size, size, permList);
        for (int i = 0 ; i < permList.size(); i++) {
            System.out.println(i + " : " +Arrays.toString(permList.get(i)));
        }


        List<ApiAuctionRes> responseList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("accept", "application/json");
            headers.set("Authorization", "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAxNzcyMzYifQ.WuwGUQh2MPRf0I_Z8mX8RGE5a3qhVAgAg8yTDT4URZ2NpTe_23SIlJwQjxidQxEOsJTrZLh7hrSD4ZpE1I-_bt_qC5SqkDvT7nXV13wlp_Jgpm8YgdmfJkZ1vFIIsNISJHrIKUh27i8qg7o_Zayip7kC0vbjaWLGK5gCMLLqfr40toc40zv31aT7irrkwnfL6W8TjtD9zVrJxPOrGGMBNpoeseUKb-ZCGp9-_D7oPwKXuJhs0XDGQji6aJoZFh3Mzo_EuH9EEThgw3lMleT7uPZEvyv-KxM-x2YBBVQ7T26MesZ2P4_OAUkU9h2D3_sj4QkiKdmv1H8zIiYq8B10ZQ");
            String baseUrl = "https://developer-lostark.game.onstove.com/auctions/items";
            HttpEntity<SearchOptionReq> requestEntity = new HttpEntity<>(searchOptionReqList.get(i), headers);

            ResponseEntity<ApiAuctionRes> response = restTemplate.postForEntity(baseUrl, requestEntity, ApiAuctionRes.class);
            responseList.add(response.getBody());
        }

        List<MoneyList> moneyLists = new ArrayList<>();
        for (int i = 0; i < permList.size(); i++) {
            MoneyList moneyList = new MoneyList();
            moneyList.setNum(permList.get(i));

            int tmp = 0;
            for (int j = 0; j < permList.get(i).length ; j ++) {
                tmp += responseList.get(j).getItems().getFirst().getAuctionInfo().getBuyPrice();
            }
            moneyList.setMoney(tmp);
            moneyLists.add(moneyList);
        }

        moneyLists.sort(Comparator.comparingInt(MoneyList::getMoney));

        ApiAuctionRes auctionResponse = new ApiAuctionRes();

        List<ApiAuctionRes.Item> items = new ArrayList<>();
        for (int i = 0; i < moneyLists.size(); i++) {
            ApiAuctionRes.Item item = responseList.get(moneyLists.getFirst().getNum()[i]).getItems().getFirst();
            items.add(item);
        }

        auctionResponse.setItems(items);


        return auctionResponse;
    }

    @Override
    public ApiAuctionRes getActionResult3(List<SearchOptionReq> searchOptionReqList) {
        boolean[] isExampleBool = new boolean[] {false, false, false, true, true};
        List<Integer> numbering = new ArrayList<>();
        if (!isExampleBool[0]) numbering.add(0);
        if (!isExampleBool[1]) numbering.add(1);
        if (!isExampleBool[2]) numbering.add(1);
        if (!isExampleBool[3]) numbering.add(2);
        if (!isExampleBool[4]) numbering.add(2);

        System.out.println("numbering.toString() = " + numbering.toString());


        int size = searchOptionReqList.size();
        // 0 목걸이 ㅣ 1 귀걸이 ㅣ 2 반지
        // 0 미지정 ㅣ 1 하 ㅣ 2 중 ㅣ 3 상
        ApiAuctionRes[][][][] searchList = new ApiAuctionRes[3][4][4][4];

        int[] rank = new int[size];
        for (int i = 0; i < size; i++) {
            rank[i] = searchOptionReqList.get(i).getEtcOptions().getFirst().getMaxValue();
            for(int j = 0; j < 3; j ++) {
                int maxValue = searchOptionReqList.get(i).getEtcOptions().getFirst().getMaxValue();
                //중복 조회 방지
                if (searchList[j][maxValue][0][0] != null) continue;
                if ( (j == 0 && isExampleBool[0]) || (j == 1 && isExampleBool[1] && isExampleBool[2] ) || (j == 2 && isExampleBool[3] && isExampleBool[4] )) continue;

                if (j == 0) {
                    searchOptionReqList.get(i).setCategoryCode(200010);
                    searchOptionReqList.get(i).getEtcOptions().getFirst().setSecondOption(41);
                } else if (j == 1) {
                    searchOptionReqList.get(i).setCategoryCode(200020);
                    searchOptionReqList.get(i).getEtcOptions().getFirst().setSecondOption(45);
                } else if (j == 2) {
                    searchOptionReqList.get(i).setCategoryCode(200030);
                    searchOptionReqList.get(i).getEtcOptions().getFirst().setSecondOption(49);
                }

                System.out.println("maxValue = " + maxValue);
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Type", "application/json");
                headers.set("accept", "application/json");
                headers.set("Authorization", "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAxNzcyMzYifQ.WuwGUQh2MPRf0I_Z8mX8RGE5a3qhVAgAg8yTDT4URZ2NpTe_23SIlJwQjxidQxEOsJTrZLh7hrSD4ZpE1I-_bt_qC5SqkDvT7nXV13wlp_Jgpm8YgdmfJkZ1vFIIsNISJHrIKUh27i8qg7o_Zayip7kC0vbjaWLGK5gCMLLqfr40toc40zv31aT7irrkwnfL6W8TjtD9zVrJxPOrGGMBNpoeseUKb-ZCGp9-_D7oPwKXuJhs0XDGQji6aJoZFh3Mzo_EuH9EEThgw3lMleT7uPZEvyv-KxM-x2YBBVQ7T26MesZ2P4_OAUkU9h2D3_sj4QkiKdmv1H8zIiYq8B10ZQ");
                String baseUrl = "https://developer-lostark.game.onstove.com/auctions/items";
                HttpEntity<SearchOptionReq> requestEntity = new HttpEntity<>(searchOptionReqList.get(i), headers);

                ResponseEntity<ApiAuctionRes> response = restTemplate.postForEntity(baseUrl, requestEntity, ApiAuctionRes.class);
                searchList[j][maxValue][0][0] = response.getBody();
            }
        }
        List<int[]> permList = new ArrayList<>();
        perm(rank, new int[size], new boolean[size], 0, size, size, permList);

        int total = Integer.MAX_VALUE;
        int selectNum = 999;
        for (int i = 0; i < permList.size(); i++) {
            int tmp = 0;
            for (int j = 0; j < numbering.size(); j++) {
                tmp += searchList[numbering.get(j)][permList.get(i)[j]][0][0].getItems().getFirst().getAuctionInfo().getBuyPrice();
            }
            System.out.println("tmp = " + tmp);
            if( total > tmp) {
                selectNum = i;
                total = tmp;
            }
            System.out.println("total = " + total);
        }

        ApiAuctionRes auctionResponse = new ApiAuctionRes();

        List<ApiAuctionRes.Item> items = new ArrayList<>();

        for (int i = 0; i < permList.get(selectNum).length; i++) {
            ApiAuctionRes.Item item = searchList[numbering.get(i)][permList.get(selectNum)[i]][0][0].getItems().getFirst();
            items.add(item);
        }

        auctionResponse.setItems(items);


        return auctionResponse;
    }

    @Override
    public SearchResultRes[] getActionResult4(List<SelectOptionReq> selectOptionReqList) {
        boolean[] isExampleBool = new boolean[5];
        List<Integer> numbering = new ArrayList<>();
        double tmpNumbering = 0;
        for (int i = 0; i < 5; i++) {
            if ( selectOptionReqList.get(i).getCategoryCode() == 200000 ) {
                isExampleBool[i] = true;
                numbering.add( (int) Math.ceil(tmpNumbering));
            }
            tmpNumbering += 0.5;
        }
        int size = numbering.size();
        // 0 목걸이 ㅣ 1 귀걸이 ㅣ 2 반지
        // 0 미지정 ㅣ 1 하 ㅣ 2 중 ㅣ 3 상
        ApiAuctionRes[][][] searchList = new ApiAuctionRes[3][4][4];
        int[][] rank = new int[size][2];
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < selectOptionReqList.get(i).getEtcOptionList().size(); k++) {
                rank[i][k] = selectOptionReqList.get(i).getEtcOptionList().get(k).getValue();
            }
            ApiAuctionReq apiAuctionReq = ApiAuctionReq.fromSelectOption(selectOptionReqList.get(i));
            for(int j = 0; j < 3; j ++) {
//                int maxValue = searchOptionReqList.get(i).getEtcOptions().getFirst().getMaxValue();
                //중복 조회 방지
                if (searchList[j][rank[i][0]][rank[i][1]] != null) continue;
                if ( ( j == 0 && !isExampleBool[0]) || ( j == 1 && !isExampleBool[1] && !isExampleBool[2] ) || ( j == 2 && !isExampleBool[3] && !isExampleBool[4] )) continue;

                /*  41추피% 42적주피%
                 *   45공% 46무공%
                 *   49치적% 50 치피%
                 */
                if (j == 0) {
                    apiAuctionReq.setCategoryCode(200010);
                    apiAuctionReq.getEtcOptions().getFirst().setSecondOption(41);
                    apiAuctionReq.getEtcOptions().getLast().setSecondOption(42);
                } else if (j == 1) {
                    apiAuctionReq.setCategoryCode(200020);
                    apiAuctionReq.getEtcOptions().getFirst().setSecondOption(45);
                    apiAuctionReq.getEtcOptions().getLast().setSecondOption(46);
                } else if (j == 2) {
                    apiAuctionReq.setCategoryCode(200030);
                    apiAuctionReq.getEtcOptions().getFirst().setSecondOption(49);
                    apiAuctionReq.getEtcOptions().getLast().setSecondOption(50);
                }

                for (int k = 0; k < 2; k++) {
                    // 상상 중중 하하 백트래킹 필요
                    apiAuctionReq.getEtcOptions().getFirst().setMinValue(rank[i][k%2]);
                    apiAuctionReq.getEtcOptions().getFirst().setMaxValue(rank[i][k%2]);

                    apiAuctionReq.getEtcOptions().getLast().setMinValue(rank[i][(k+1)%2]);
                    apiAuctionReq.getEtcOptions().getLast().setMaxValue(rank[i][(k+1)%2]);

                    RestTemplate restTemplate = new RestTemplate();
                    HttpHeaders headers = new HttpHeaders();
                    headers.set("Content-Type", "application/json");
                    headers.set("accept", "application/json");
                    headers.set("Authorization", "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAxNzcyMzYifQ.WuwGUQh2MPRf0I_Z8mX8RGE5a3qhVAgAg8yTDT4URZ2NpTe_23SIlJwQjxidQxEOsJTrZLh7hrSD4ZpE1I-_bt_qC5SqkDvT7nXV13wlp_Jgpm8YgdmfJkZ1vFIIsNISJHrIKUh27i8qg7o_Zayip7kC0vbjaWLGK5gCMLLqfr40toc40zv31aT7irrkwnfL6W8TjtD9zVrJxPOrGGMBNpoeseUKb-ZCGp9-_D7oPwKXuJhs0XDGQji6aJoZFh3Mzo_EuH9EEThgw3lMleT7uPZEvyv-KxM-x2YBBVQ7T26MesZ2P4_OAUkU9h2D3_sj4QkiKdmv1H8zIiYq8B10ZQ");
                    String baseUrl = "https://developer-lostark.game.onstove.com/auctions/items";
                    HttpEntity<ApiAuctionReq> requestEntity = new HttpEntity<>(apiAuctionReq, headers);

                    ResponseEntity<ApiAuctionRes> response = restTemplate.postForEntity(baseUrl, requestEntity, ApiAuctionRes.class);
                    searchList[j][rank[i][k%2]][rank[i][(k+1)%2]] = response.getBody();
                }
            }
        }

        List<int[]> permList = new ArrayList<>();
        int[] options = new int[size];
        for (int i = 0; i < size; i++) {
            options[i] = rank[i][0]*10 + rank[i][1];
        }
        perm(options, new int[size], new boolean[size], 0, size, size, permList);

        int total = Integer.MAX_VALUE;
        int selectNum = 999;
        for (int i = 0; i < permList.size(); i++) {
            int tmp = 0;
            for (int j = 0; j < numbering.size(); j++) {
                tmp += searchList[numbering.get(j)][permList.get(i)[j]/10][permList.get(i)[j]%10].getItems().getFirst().getAuctionInfo().getBuyPrice();
            }
            System.out.println("tmp = " + tmp);
            if( total > tmp) {
                selectNum = i;
                total = tmp;
            }
            System.out.println("total = " + total);
        }

        SearchResultRes[] searchResultRes = new SearchResultRes[5];

        int num = 0;
        for (int i = 0; i < 5; i++) {
            if(!isExampleBool[i]) continue;
            searchResultRes[i] = SearchResultRes.fromApiRes( searchList[numbering.get(num)][permList.get(selectNum)[num]/10][permList.get(selectNum)[num]%10] );
            num++;
        }

        //고정값도 넣어줘야 함

        return searchResultRes;
    }


    // [0,1] , new, new, 0, 2, 2, new
    private void perm(int[] arr, int[] output, boolean[] visited, int depth, int n, int r, List<int[]> result) {
        if (depth == r) {
            System.out.println("output = " + Arrays.toString(output));
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

    @Getter
    @Setter
    static class MoneyList {
        private int[] num;
        private int money;
    }

}

