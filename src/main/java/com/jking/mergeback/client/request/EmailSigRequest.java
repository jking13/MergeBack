package com.jking.mergeback.client.request;

/**
 * Created by JacksonGenerator on 3/31/17.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EmailSigRequest {
    @JsonProperty("messages")
    private List<MessagesItem> messages;
    @JsonProperty("request_id")
    private String requestId;
}