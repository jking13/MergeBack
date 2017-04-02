package com.jking.mergeback.client.request;

/**
 * Created by JacksonGenerator on 3/31/17.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MessagesItem {
    @JsonProperty("user_id")
    private String userId = "not_used";
    @JsonProperty("sent_date")
    private String sentDate;
    @JsonProperty("message_id")
    private String messageId;
    @JsonProperty("from")
    private From from;
    @JsonProperty("to")
    private List<ToItem> to;
    @JsonProperty("body")
    private String body;
}