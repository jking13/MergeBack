package com.jking.mergeback.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.jking.mergeback.client.request.MessagesItem;
import com.jking.mergeback.client.response.ContactsItem;
import com.jking.mergeback.client.response.EmailSigResponse;
import com.jking.mergeback.config.PropertiesConfig;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by john on 3/31/17.
 */
public class EmailSigReaderWriter {

    private final PropertiesConfig config;
    private final ObjectMapper mapper;

    @Inject
    public EmailSigReaderWriter(PropertiesConfig config) {
        this.config = config;
        this.mapper = new ObjectMapper();
    }

    public List<MessagesItem> readEmails(){
        return readFile(config.config().getString("input.emails.file"),
                new TypeReference<List<MessagesItem>>(){});
    }

    public List<ContactsItem> readContacts(){
        return readFile(config.config().getString("input.contacts.file"),
                new TypeReference<List<ContactsItem>>(){});
    }

    public EmailSigResponse readTestOut(){
        return readFile(config.config().getString("input.test.response.file"),
                new TypeReference<EmailSigResponse>(){});
    }

    public void writeContacts(List<ContactsItem> contacts){
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(config.config().getString("output.contacts.file")), contacts);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T readFile(String fileName, TypeReference<T> type){
        try {
            return mapper.readValue(new File(fileName),type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
