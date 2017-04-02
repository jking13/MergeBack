package com.jking.mergeback.client.request;

/**
 * Created by JacksonGenerator on 3/31/17.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class From {
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
}