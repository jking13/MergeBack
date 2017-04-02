package com.jking.mergeback.client.response;

/**
 * Created by JacksonGenerator on 3/31/17.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class PhoneNumbersItem {
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("type")
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumbersItem that = (PhoneNumbersItem) o;

        return (phoneNumber != null ? phoneNumbersEqual(that.phoneNumber) : that.phoneNumber == null);
    }

    public boolean phoneNumbersEqual(String number){
        return normalize(phoneNumber).equals(normalize(number));
    }

    private String normalize(String number){
        if(StringUtils.countMatches(number, "-") == 3){
            return number.substring(number.indexOf('-') + 1);
        }
        else{
            return number;
        }
    }

    @Override
    public int hashCode() {
        int result = (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}