package com.lostark.root.common.staticMethod;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class ValueAutoWired {

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.public}")
    private String apiPublicKey;

    public String getApiKey() {
        return apiKey;
    }

    public String getPublicKey() {
        return apiPublicKey;
    }
}
