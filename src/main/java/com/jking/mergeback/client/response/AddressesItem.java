package com.jking.mergeback.client.response;

/**
 * Created by JacksonGenerator on 3/31/17.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressesItem {
    @JsonProperty("zip")
    private String zip;
    @JsonProperty("country")
    private String country;
    @JsonProperty("city")
    private String city;
    @JsonProperty("street1")
    private String street1;
    @JsonProperty("street2")
    private String street2;
    @JsonProperty("state")
    private String state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressesItem that = (AddressesItem) o;

        if (zip != null ? !zip.equals(that.zip) : that.zip != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (street1 != null ? !street1.equals(that.street1) : that.street1 != null) return false;
        if (street2 != null ? !street2.equals(that.street2) : that.street2 != null) return false;
        return state != null ? state.equals(that.state) : that.state == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street1 != null ? street1.hashCode() : 0);
        result = 31 * result + (street2 != null ? street2.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}