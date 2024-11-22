package com.lostark.root.action.service;

import com.lostark.root.action.db.dto.req.SearchOptionReq;
import com.lostark.root.action.db.dto.res.APIres.ApiAuctionResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ActionServiceImpl implements ActionService {

    @Override
    public ApiAuctionResponse getActionResult(SearchOptionReq searchOptionReq) {
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

        ResponseEntity<ApiAuctionResponse> response = restTemplate.postForEntity(baseUrl, requestEntity, ApiAuctionResponse.class);
        System.out.println("Response: " + response.getBody());

        return response.getBody();
    }

}
