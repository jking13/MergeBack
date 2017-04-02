package com.jking.mergeback.merge;

import com.google.inject.Inject;
import com.jking.mergeback.client.response.ContactsItem;

/**
 * Created by john on 3/31/17.
 */
public class ContactsItemMerger extends Merger<ContactsItem> {

    private final PhoneNumbersItemMerger phoneNumbersItemMerger;

    @Inject
    public ContactsItemMerger(PhoneNumbersItemMerger phoneNumbersItemMerger) {
        this.phoneNumbersItemMerger = phoneNumbersItemMerger;
    }

    @Override
    public ContactsItem merge(ContactsItem from, ContactsItem to) {
        ContactsItem item = new ContactsItem();
        item.setCompany(mergeString(from.getCompany(), to.getCompany()));
        item.setFirstName(mergeString(from.getFirstName(), to.getFirstName()));
        item.setFullName(mergeString(from.getFullName(), to.getFullName()));
        item.setLastName(mergeString(from.getLastName(), to.getLastName()));
        item.setMiddleName(mergeString(from.getMiddleName(), to.getMiddleName()));
        item.setNickname(mergeString(from.getNickname(), to.getNickname()));
        item.setPrefix(mergeString(from.getPrefix(), to.getPrefix()));
        item.setSuffix(mergeString(from.getSuffix(), to.getSuffix()));
        item.setTitle(mergeString(from.getTitle(), to.getTitle()));
        item.setAddresses(mergeList(from.getAddresses(), to.getAddresses()));
        item.setEmails(mergeList(from.getEmails(), to.getEmails()));
        item.setPhoneNumbers(phoneNumbersItemMerger.merge(from.getPhoneNumbers(), to.getPhoneNumbers()));
        item.setUrls(mergeList(from.getUrls(), to.getUrls()));
        item.setSocialProfiles(mergeList(from.getSocialProfiles(), to.getSocialProfiles()));
        item.setIms(mergeList(from.getIms(), to.getIms()));
        return item;
    }
}
