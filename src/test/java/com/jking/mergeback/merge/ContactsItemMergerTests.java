package com.jking.mergeback.merge;

import com.jking.mergeback.client.response.AddressesItem;
import com.jking.mergeback.client.response.ContactsItem;
import com.jking.mergeback.client.response.PhoneNumbersItem;
import com.jking.mergeback.client.response.SocialProfilesItem;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by john on 4/1/17.
 */
@Test
public class ContactsItemMergerTests {

    ContactsItemMerger merger = new ContactsItemMerger(new PhoneNumbersItemMerger());

    @Test
    public void testEmptyLists(){
        //given
        List<ContactsItem> from = new ArrayList<>();
        List<ContactsItem> to = new ArrayList<>();
        //when
        List<ContactsItem> merged = merger.merge(from, to);
        //then
        assertTrue(merged.isEmpty());
    }

    @Test
    public void testFromEmpty(){
        //given
        List<ContactsItem> from = new ArrayList<>();
        List<ContactsItem> to = Arrays.asList(to());
        //when
        List<ContactsItem> merged = merger.merge(from, to);
        //then
        assertEquals(merged.size(), 1);
        ContactsItem item = merged.get(0);
        assertEquals(item.getFirstName(), "firstName");
        assertEquals(item.getLastName(), "lastName");
        assertEquals(item.getPhoneNumbers().size(), 1);
        assertEquals(item.getPhoneNumbers().get(0).getPhoneNumber(), "555-732-7689");
    }

    @Test
    public void testToEmpty(){
        //given
        List<ContactsItem> from = Arrays.asList(from());
        List<ContactsItem> to = new ArrayList<>();
        //when
        List<ContactsItem> merged = merger.merge(from, to);
        //then
        assertEquals(merged.size(), 1);
        ContactsItem item = merged.get(0);
        assertEquals(item.getFirstName(), "firstName");
        assertEquals(item.getLastName(), "lastName");
        assertEquals(item.getPhoneNumbers().size(), 1);
        assertEquals(item.getPhoneNumbers().get(0).getPhoneNumber(), "555-732-7688");
    }

    @Test
    public void testSameContact(){
        //given
        List<ContactsItem> from = Arrays.asList(to());
        List<ContactsItem> to = Arrays.asList(to());
        //when
        List<ContactsItem> merged = merger.merge(from, to);
        //then
        assertEquals(merged.size(), 1);
        ContactsItem item = merged.get(0);
        assertEquals(item.getFirstName(), "firstName");
        assertEquals(item.getLastName(), "lastName");
        assertEquals(item.getPhoneNumbers().size(), 1);
        assertEquals(item.getPhoneNumbers().get(0).getPhoneNumber(), "555-732-7689");
    }

    @Test
    public void testEqualsPhoneNumber(){
        assertTrue(from().equals(to()));
    }

    @Test
    public void testHashcode(){
        assertEquals(from().getPhoneNumbers().get(0).hashCode(), from().getPhoneNumbers().get(0).hashCode());
    }

    @Test
    public void testDifferentPhoneNumber(){
        //given
        List<ContactsItem> from = Arrays.asList(from());
        List<ContactsItem> to = Arrays.asList(to());
        //when
        List<ContactsItem> merged = merger.merge(from, to);
        //then
        assertEquals(merged.size(), 1);
        ContactsItem item = merged.get(0);
        assertEquals(item.getFirstName(), "firstName");
        assertEquals(item.getLastName(), "lastName");
        assertEquals(item.getPhoneNumbers().size(), 2);
        assertTrue(item.getPhoneNumbers().stream().anyMatch(number -> number.getPhoneNumber().equals("555-732-7688")));
        assertTrue(item.getPhoneNumbers().stream().anyMatch(number -> number.getPhoneNumber().equals("555-732-7689")));
    }

    @Test
    public void testDifferentEmails(){
        //given
        List<ContactsItem> from = Arrays.asList(from());
        List<ContactsItem> to = Arrays.asList(to());
        to.get(0).setPhoneNumbers(from.get(0).getPhoneNumbers());
        to.get(0).setEmails(Arrays.asList("newEmail"));
        //when
        List<ContactsItem> merged = merger.merge(from, to);
        //then
        assertEquals(merged.size(), 1);
        ContactsItem item = merged.get(0);
        assertEquals(item.getFirstName(), "firstName");
        assertEquals(item.getLastName(), "lastName");
        assertEquals(item.getPhoneNumbers().size(), 1);
        assertTrue(item.getPhoneNumbers().stream().anyMatch(number -> number.getPhoneNumber().equals("555-732-7688")));
        assertEquals(item.getEmails().size(), 2);
        assertTrue(item.getEmails().stream().anyMatch(email -> email.equals("email")));
        assertTrue(item.getEmails().stream().anyMatch(email -> email.equals("newEmail")));
    }

    @Test
    public void testPhoneNumberEquals(){
        //given
        PhoneNumbersItem from = from().getPhoneNumbers().get(0);
        PhoneNumbersItem to = to().getPhoneNumbers().get(0);
        to.setPhoneNumber("1-" + from.getPhoneNumber());
        //when
        Boolean equal = from.equals(to);
        //then
        assertTrue(equal);
    }

    @Test
    public void testFullMerge(){
        //given
        List<ContactsItem> from = Arrays.asList(fromFull());
        List<ContactsItem> to = Arrays.asList(toFull());
        //when
        List<ContactsItem> merged = merger.merge(from, to);
        //then
        assertEquals(merged.size(), 1);
        ContactsItem item = merged.get(0);
        assertEquals(item.getFirstName(), "firstName");
        assertEquals(item.getLastName(), "lastName");
        assertEquals(item.getFullName(), "fullName");
        assertEquals(item.getCompany(), "company");
        assertEquals(item.getMiddleName(), "middleName");
        assertEquals(item.getNickname(), "nickname");
        assertEquals(item.getPrefix(), "prefix");
        assertEquals(item.getSuffix(), "suffix");
        assertEquals(item.getTitle(), "title2");

        assertEquals(item.getPhoneNumbers().size(), 3);
        assertTrue(item.getPhoneNumbers().stream().anyMatch(number -> number.getPhoneNumber().equals("555-732-7688")));
        assertTrue(item.getPhoneNumbers().stream().anyMatch(number -> number.getPhoneNumber().equals("555-732-7689")));
        assertTrue(item.getPhoneNumbers().stream().anyMatch(number -> number.getPhoneNumber().equals("555-732-7690")));

        assertEquals(item.getEmails().size(), 3);
        assertTrue(item.getEmails().stream().anyMatch(email -> email.equals("email1")));
        assertTrue(item.getEmails().stream().anyMatch(email -> email.equals("email2")));
        assertTrue(item.getEmails().stream().anyMatch(email -> email.equals("email3")));

        assertEquals(item.getUrls().size(), 3);
        assertTrue(item.getUrls().stream().anyMatch(url -> url.equals("url1")));
        assertTrue(item.getUrls().stream().anyMatch(url -> url.equals("url2")));
        assertTrue(item.getUrls().stream().anyMatch(url -> url.equals("url3")));

        assertEquals(item.getAddresses().size(), 2);
        assertTrue(item.getAddresses().stream().anyMatch(address -> address.getCity().equals("city")));
        assertTrue(item.getAddresses().stream().anyMatch(address -> address.getCity().equals("city2")));

        assertEquals(item.getSocialProfiles().size(), 1);
        assertTrue(item.getSocialProfiles().stream().anyMatch(profile -> profile.getUrl().equals("url")));

    }

    public ContactsItem to(){
        ContactsItem item = new ContactsItem();
        item.setLastName("lastName");
        item.setFirstName("firstName");
        PhoneNumbersItem number = new PhoneNumbersItem();
        number.setPhoneNumber("555-732-7689");
        item.setPhoneNumbers(Arrays.asList(number));
        item.setEmails(Arrays.asList("email"));
        return item;
    }

    public ContactsItem fromFull(){
        ContactsItem item = new ContactsItem();
        item.setLastName("lastName");
        item.setFirstName("firstName");
        item.setIms(Arrays.asList("ims1, ims2"));
        item.setSuffix("suffix");
        item.setPrefix("prefix");
        item.setNickname("nickname");
        item.setMiddleName("middleName");
        item.setFullName("fullName");
        item.setCompany("company");
        item.setUrls(Arrays.asList("url1", "url2"));
        AddressesItem addressesItem = new AddressesItem();
        addressesItem.setCity("city");
        addressesItem.setCountry("country");
        addressesItem.setState("state");
        addressesItem.setStreet1("street1");
        addressesItem.setStreet2("street2");
        addressesItem.setZip("zip");
        item.setAddresses(Arrays.asList(addressesItem));
        item.setTitle("title2");
        PhoneNumbersItem number1 = new PhoneNumbersItem();
        number1.setPhoneNumber("555-732-7688");
        PhoneNumbersItem number2 = new PhoneNumbersItem();
        number2.setPhoneNumber("555-732-7689");
        item.setPhoneNumbers(Arrays.asList(number1, number2));
        item.setEmails(Arrays.asList("email1", "email2"));
        return item;
    }

    public ContactsItem toFull(){
        ContactsItem item = new ContactsItem();
        item.setLastName("lastName");
        item.setFirstName("firstName");
        item.setIms(Arrays.asList("ims2, ims3"));
        item.setSuffix("suffix");
        item.setPrefix("prefix");
        item.setNickname("nickname");
        item.setMiddleName("middleName");
        item.setFullName("fullName");
        item.setCompany("company");
        item.setUrls(Arrays.asList("url2", "url3"));
        AddressesItem addressesItem = new AddressesItem();
        addressesItem.setCity("city");
        addressesItem.setCountry("country");
        addressesItem.setState("state");
        addressesItem.setStreet1("street1");
        addressesItem.setStreet2("street2");
        addressesItem.setZip("zip");
        AddressesItem addressesItem2 = new AddressesItem();
        addressesItem2.setCity("city2");
        addressesItem2.setCountry("country2");
        addressesItem2.setState("state2");
        addressesItem2.setStreet1("street1");
        addressesItem2.setStreet2("street2");
        addressesItem2.setZip("zip2");
        item.setAddresses(Arrays.asList(addressesItem, addressesItem2));
        item.setTitle("title2");
        PhoneNumbersItem number1 = new PhoneNumbersItem();
        number1.setPhoneNumber("555-732-7689");
        PhoneNumbersItem number2 = new PhoneNumbersItem();
        number2.setPhoneNumber("555-732-7690");
        item.setPhoneNumbers(Arrays.asList(number1, number2));
        item.setEmails(Arrays.asList("email2", "email3"));
        SocialProfilesItem profilesItem = new SocialProfilesItem();
        profilesItem.setNetwork("network");
        profilesItem.setUrl("url");
        item.setSocialProfiles(Arrays.asList(profilesItem));
        return item;
    }

    public ContactsItem from(){
        ContactsItem item = new ContactsItem();
        item.setLastName("lastName");
        item.setFirstName("firstName");
        PhoneNumbersItem number = new PhoneNumbersItem();
        number.setPhoneNumber("555-732-7688");
        item.setPhoneNumbers(Arrays.asList(number));
        item.setEmails(Arrays.asList("email"));
        return item;
    }
}
