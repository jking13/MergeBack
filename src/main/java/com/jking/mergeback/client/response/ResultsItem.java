package com.jking.mergeback.client.response;

/**
 * Created by JacksonGenerator on 3/31/17.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResultsItem {
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("message_id")
    private String messageId;
    @JsonProperty("result_code")
    private String resultCode;
    @JsonProperty("signature_blocks")
    private List<String> signatureBlocks;
    @JsonProperty("contacts")
    private List<ContactsItem> contacts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultsItem that = (ResultsItem) o;
        return contacts != null
                ? contacts.stream()
                .anyMatch(thisContact ->
                        that.contacts.stream()
                                .anyMatch(thatContact -> thisContact.equals(thatContact)))
                : that.contacts == null;
    }

    @Override
    public int hashCode() {
        return (contacts != null ? contacts.hashCode() : 0);
    }
}