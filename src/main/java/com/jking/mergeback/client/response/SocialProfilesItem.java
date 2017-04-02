package com.jking.mergeback.client.response;

/**
 * Created by JacksonGenerator on 3/31/17.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SocialProfilesItem {
    @JsonProperty("url")
    private String url;
    @JsonProperty("network")
    private String network;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocialProfilesItem that = (SocialProfilesItem) o;

        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        return network != null ? network.equals(that.network) : that.network == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (network != null ? network.hashCode() : 0);
        return result;
    }
}