package com.lostark.root.auction.db.dto.res.APIres;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tooltip {

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Value")
    private String value;
}
