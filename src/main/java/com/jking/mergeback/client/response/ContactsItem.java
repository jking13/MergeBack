package com.jking.mergeback.client.response;

/**
 * Created by JacksonGenerator on 3/31/17.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContactsItem {
    @JsonProperty("addresses")
    private List<AddressesItem> addresses = new ArrayList<>();
    @JsonProperty("prefix")
    private String prefix;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("middle_name")
    private String middleName;
    @JsonProperty("suffix")
    private String suffix;
    @JsonProperty("title")
    private String title;
    @JsonProperty("emails")
    private List<String> emails = new ArrayList<>();
    @JsonProperty("phone_numbers")
    private List<PhoneNumbersItem> phoneNumbers = new ArrayList<>();
    @JsonProperty("social_profiles")
    private List<SocialProfilesItem> socialProfiles = new ArrayList<>();
    @JsonProperty("urls")
    private List<String> urls = new ArrayList<>();
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("company")
    private String company;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("ims")
    private List<String> ims = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactsItem that = (ContactsItem) o;

        if (lastName != null ? !lastName.equalsIgnoreCase(that.lastName) : that.lastName != null) return false;
        if (firstName != null ? !firstName.equalsIgnoreCase(that.firstName) : that.firstName != null) return false;
        return ((emails != null && that.emails != null ? emails.stream()
                .anyMatch(thisEmail -> that.emails.stream()
                        .anyMatch(thatEmail -> thisEmail.equalsIgnoreCase(thatEmail)))
                : false)
                || (phoneNumbers != null && that.phoneNumbers != null ? phoneNumbers.stream()
                .anyMatch(thisNumber -> that.phoneNumbers.stream()
                        .anyMatch(thatNumber -> thisNumber.equals(thatNumber)))
                : false));
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (emails != null ? emails.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneNumbers != null ? phoneNumbers.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        return result;
    }
}