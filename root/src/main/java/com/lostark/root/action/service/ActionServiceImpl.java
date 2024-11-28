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

import java.util.*;


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
        List<Integer> boxNumber = new ArrayList<>();
        double tmpNumbering = 0;
        for (int i = 0; i < 5; i++) {
            if ( selectOptionReqList.get(i).getCategoryCode() == 200000 ) {
                isExampleBool[i] = true;
                numbering.add( (int) Math.ceil(tmpNumbering));
                boxNumber.add(i);
            }
            tmpNumbering += 0.5;
        }
        int size = numbering.size();
        // 0 목걸이 ㅣ 1 귀걸이 ㅣ 2 반지
        // 0 미지정 ㅣ 1 하 ㅣ 2 중 ㅣ 3 상
        ApiAuctionRes[][][] searchList = new ApiAuctionRes[3][4][4];
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
                    if(rank[i][k%2] != 0) {
                        apiAuctionReq.getEtcOptions().getFirst().setMinValue(rank[i][k % 2]);
                        apiAuctionReq.getEtcOptions().getFirst().setMaxValue(rank[i][k % 2]);
                    } else {
                        apiAuctionReq.getEtcOptions().getFirst().setFirstOption(null);
                    }

                    if(rank[i][(k+1)%2] != 0) {
                        apiAuctionReq.getEtcOptions().getLast().setMinValue(rank[i][(k+1)%2]);
                        apiAuctionReq.getEtcOptions().getLast().setMaxValue(rank[i][(k+1)%2]);
                    } else {
                        apiAuctionReq.getEtcOptions().getLast().setFirstOption(null);
                    }

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
            if( total > tmp) {
                selectNum = i;
                total = tmp;
            }
        }

        SearchResultRes[] searchResultRes = new SearchResultRes[5];

        int num = 0;
        for (int i = 0; i < 5; i++) {
            if(!isExampleBool[i]) continue;
            searchResultRes[i] = SearchResultRes.fromApiRes( searchList[numbering.get(num)][permList.get(selectNum)[num]/10][permList.get(selectNum)[num]%10], 0 );
            num++;
        }

        for (int i = 0; i < 5; i++) {
            if (isExampleBool[i] || selectOptionReqList.get(i).getCategoryCode() == 0) continue;
            
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("accept", "application/json");
            headers.set("Authorization", "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAxNzcyMzYifQ.WuwGUQh2MPRf0I_Z8mX8RGE5a3qhVAgAg8yTDT4URZ2NpTe_23SIlJwQjxidQxEOsJTrZLh7hrSD4ZpE1I-_bt_qC5SqkDvT7nXV13wlp_Jgpm8YgdmfJkZ1vFIIsNISJHrIKUh27i8qg7o_Zayip7kC0vbjaWLGK5gCMLLqfr40toc40zv31aT7irrkwnfL6W8TjtD9zVrJxPOrGGMBNpoeseUKb-ZCGp9-_D7oPwKXuJhs0XDGQji6aJoZFh3Mzo_EuH9EEThgw3lMleT7uPZEvyv-KxM-x2YBBVQ7T26MesZ2P4_OAUkU9h2D3_sj4QkiKdmv1H8zIiYq8B10ZQ");
            String baseUrl = "https://developer-lostark.game.onstove.com/auctions/items";
            HttpEntity<ApiAuctionReq> requestEntity = new HttpEntity<>(ApiAuctionReq.fromSelectOption(selectOptionReqList.get(i)), headers);

            ResponseEntity<ApiAuctionRes> response = restTemplate.postForEntity(baseUrl, requestEntity, ApiAuctionRes.class);

            searchResultRes[i] = SearchResultRes.fromApiRes(Objects.requireNonNull(response.getBody()), 0);
        }

        //고정값도 넣어줘야 함

        return searchResultRes;
    }

    @Override
    public SearchResultRes[] getActionResult5(List<SelectOptionReq> selectOptionReqList) {
        boolean[] isExampleBool = new boolean[5];
        List<Integer> numbering = new ArrayList<>();
        List<Integer> boxNumber = new ArrayList<>();
        double tmpNumbering = 0;
        for (int i = 0; i < 5; i++) {
            if ( selectOptionReqList.get(i).getCategoryCode() == 200000 ) {
                isExampleBool[i] = true;
                numbering.add( (int) Math.ceil(tmpNumbering));
                boxNumber.add(i);
            }
            tmpNumbering += 0.5;
        }
        int size = numbering.size();
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

                    RestTemplate restTemplate = new RestTemplate();
                    HttpHeaders headers = new HttpHeaders();
                    headers.set("Content-Type", "application/json");
                    headers.set("accept", "application/json");
                    headers.set("Authorization", "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAxNzcyMzYifQ.WuwGUQh2MPRf0I_Z8mX8RGE5a3qhVAgAg8yTDT4URZ2NpTe_23SIlJwQjxidQxEOsJTrZLh7hrSD4ZpE1I-_bt_qC5SqkDvT7nXV13wlp_Jgpm8YgdmfJkZ1vFIIsNISJHrIKUh27i8qg7o_Zayip7kC0vbjaWLGK5gCMLLqfr40toc40zv31aT7irrkwnfL6W8TjtD9zVrJxPOrGGMBNpoeseUKb-ZCGp9-_D7oPwKXuJhs0XDGQji6aJoZFh3Mzo_EuH9EEThgw3lMleT7uPZEvyv-KxM-x2YBBVQ7T26MesZ2P4_OAUkU9h2D3_sj4QkiKdmv1H8zIiYq8B10ZQ");
                    String baseUrl = "https://developer-lostark.game.onstove.com/auctions/items";
                    HttpEntity<ApiAuctionReq> requestEntity = new HttpEntity<>(apiAuctionReq, headers);

                    ResponseEntity<ApiAuctionRes> response = restTemplate.postForEntity(baseUrl, requestEntity, ApiAuctionRes.class);
//                    searchList[j][rank[i][k%2]][rank[i][(k+1)%2]] = response.getBody();

                    System.out.println("j*2 + k = " + ((j * 2) + k) + " boxNumber.get(i)" + boxNumber.get(i));
                    System.out.println("apiAuctionReq.getItemGradeQuality() = " + apiAuctionReq.getItemGradeQuality());
                    searchList[j*2 + k][boxNumber.get(i)] = response.getBody();
                }
            }
        }

        List<int[]> permList = new ArrayList<>();
        int[] options = new int[size];

        for (int i = 0; i < size; i++) {
            options[i] = boxNumber.get(i);
        }
        perm(options, new int[size], new boolean[size], 0, size, size, permList);

        int[] example = {0, 2, 2, 4, 4};
        List<int[]> numOption = generateCombinations(example, isExampleBool);

        int total = Integer.MAX_VALUE;
        int selectNum = 999;
        int selectNum2 = 999;
        for (int i = 0; i < permList.size(); i++) {

//            for (int j = 0; j < numbering.size(); j++) {
//                tmp += searchList[numbering.get(j)][permList.get(i)[j]/10][permList.get(i)[j]%10].getItems().getFirst().getAuctionInfo().getBuyPrice();
//            }
            int[] tmpPerm = permList.get(i);
            for (int j = 0; j < numOption.size(); j++) {
                int[] tmpOption = numOption.get(j);
                int tmp = 0;
                for (int k = 0; k < size; k++) {
                    if( k > 0 && searchList[tmpOption[k]][tmpPerm[k]].getItems().getFirst().getAuctionInfo().getEndDate().equals(searchList[tmpOption[k-1]][tmpPerm[k-1]].getItems().getFirst().getAuctionInfo().getEndDate())) {
                        System.out.println("deduplication! " + searchList[tmpOption[k]][tmpPerm[k]].getItems().getFirst().getAuctionInfo().getBuyPrice());
                        System.out.println("next? " + searchList[tmpOption[k]][tmpPerm[k]].getItems().get(1).getAuctionInfo().getBuyPrice());
                        tmp += searchList[tmpOption[k]][tmpPerm[k]].getItems().get(1).getAuctionInfo().getBuyPrice();
                    } else {
                        tmp += searchList[tmpOption[k]][tmpPerm[k]].getItems().getFirst().getAuctionInfo().getBuyPrice();
                    }
                }
                if( total > tmp) {
                    selectNum = i;
                    selectNum2 = j;
                    total = tmp;
                }
            }

        }

        SearchResultRes[] searchResultRes = new SearchResultRes[5];

        for (int i = 0; i < size; i++) {
            if( i > 0 && searchList[numOption.get(selectNum2)[i]][permList.get(selectNum)[i]].getItems().getFirst().getAuctionInfo().getEndDate().equals(searchList[numOption.get(selectNum2)[i-1]][permList.get(selectNum)[i-1]].getItems().getFirst().getAuctionInfo().getEndDate())) {
                System.out.println("here?");
                System.out.println(searchList[numOption.get(selectNum2)[i]][permList.get(selectNum)[i]].getItems().get(1).getAuctionInfo().getBuyPrice());
                searchResultRes[boxNumber.get(i)] = SearchResultRes.fromApiRes( searchList[numOption.get(selectNum2)[i]][permList.get(selectNum)[i]], 1 );
            } else {
                searchResultRes[boxNumber.get(i)] = SearchResultRes.fromApiRes(searchList[numOption.get(selectNum2)[i]][permList.get(selectNum)[i]], 0);
            }
        }

        for (int i = 0; i < 5; i++) {
            if (isExampleBool[i] || selectOptionReqList.get(i).getCategoryCode() == 0) continue;

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("accept", "application/json");
            headers.set("Authorization", "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAxNzcyMzYifQ.WuwGUQh2MPRf0I_Z8mX8RGE5a3qhVAgAg8yTDT4URZ2NpTe_23SIlJwQjxidQxEOsJTrZLh7hrSD4ZpE1I-_bt_qC5SqkDvT7nXV13wlp_Jgpm8YgdmfJkZ1vFIIsNISJHrIKUh27i8qg7o_Zayip7kC0vbjaWLGK5gCMLLqfr40toc40zv31aT7irrkwnfL6W8TjtD9zVrJxPOrGGMBNpoeseUKb-ZCGp9-_D7oPwKXuJhs0XDGQji6aJoZFh3Mzo_EuH9EEThgw3lMleT7uPZEvyv-KxM-x2YBBVQ7T26MesZ2P4_OAUkU9h2D3_sj4QkiKdmv1H8zIiYq8B10ZQ");
            String baseUrl = "https://developer-lostark.game.onstove.com/auctions/items";
            HttpEntity<ApiAuctionReq> requestEntity = new HttpEntity<>(ApiAuctionReq.fromSelectOption(selectOptionReqList.get(i)), headers);

            ResponseEntity<ApiAuctionRes> response = restTemplate.postForEntity(baseUrl, requestEntity, ApiAuctionRes.class);

            int duplication = 0;
            if( i == 1 && searchResultRes[2] != null && searchResultRes[2].getAuctionInfo().getEndDate().equals(Objects.requireNonNull(response.getBody()).Items.getFirst().getAuctionInfo().getEndDate())) {
                duplication = 1;
            } else if (i == 2 && searchResultRes[1] != null && searchResultRes[1].getAuctionInfo().getEndDate().equals(Objects.requireNonNull(response.getBody()).Items.getFirst().getAuctionInfo().getEndDate())) {
                duplication = 1;
            } else if (i == 3 && searchResultRes[4] != null && searchResultRes[4].getAuctionInfo().getEndDate().equals(Objects.requireNonNull(response.getBody()).Items.getFirst().getAuctionInfo().getEndDate())) {
                duplication = 1;
            } else if (i == 4 && searchResultRes[3] != null && searchResultRes[3].getAuctionInfo().getEndDate().equals(Objects.requireNonNull(response.getBody()).Items.getFirst().getAuctionInfo().getEndDate())) {
                duplication = 1;
            }
            searchResultRes[i] = SearchResultRes.fromApiRes(Objects.requireNonNull(response.getBody()), duplication);

        }

        //고정값도 넣어줘야 함

        return searchResultRes;
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


    @Getter
    @Setter
    static class MoneyList {
        private int[] num;
        private int money;
    }

}

