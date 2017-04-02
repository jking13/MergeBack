package com.jking.mergeback.client.response;

/**
 * Created by JacksonGenerator on 3/31/17.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EmailSigResponse {
    @JsonProperty("contact_count")
    private Integer contactCount;
    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("results")
    private List<ResultsItem> results;
}