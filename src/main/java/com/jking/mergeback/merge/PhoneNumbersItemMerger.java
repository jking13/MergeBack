package com.jking.mergeback.merge;

import com.jking.mergeback.client.response.PhoneNumbersItem;

/**
 * Created by john on 4/2/17.
 */
public class PhoneNumbersItemMerger extends Merger<PhoneNumbersItem> {
    @Override
    public PhoneNumbersItem merge(PhoneNumbersItem from, PhoneNumbersItem to) {
        PhoneNumbersItem item = new PhoneNumbersItem();
        item.setPhoneNumber(mergeString(from.getPhoneNumber(), to.getPhoneNumber()));
        item.setType(mergeString(from.getType(), to.getType()));
        return item;
    }
}
