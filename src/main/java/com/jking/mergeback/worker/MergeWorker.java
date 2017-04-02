package com.jking.mergeback.worker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.jking.mergeback.client.CircleBackClient;
import com.jking.mergeback.client.request.EmailSigRequest;
import com.jking.mergeback.client.response.ContactsItem;
import com.jking.mergeback.client.response.EmailSigResponse;
import com.jking.mergeback.config.PropertiesConfig;
import com.jking.mergeback.io.EmailSigReaderWriter;
import com.jking.mergeback.merge.ContactsItemMerger;
import retrofit2.Response;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by john on 3/31/17.
 */
public class MergeWorker implements Runnable {

    private final CircleBackClient client;
    private final ContactsItemMerger merger;
    private final EmailSigReaderWriter readerWriter;
    private final PropertiesConfig propertiesConfig;

    @Inject
    public MergeWorker(CircleBackClient client,
                       ContactsItemMerger merger,
                       EmailSigReaderWriter readerWriter,
                       PropertiesConfig propertiesConfig) {
        this.client = client;
        this.merger = merger;
        this.readerWriter = readerWriter;
        this.propertiesConfig = propertiesConfig;
    }

    @Override
    public void run() {
        EmailSigResponse resp = getResponse();
        List<ContactsItem> mergedContacts = merge(resp);
        readerWriter.writeContacts(mergedContacts);
    }

    private List<ContactsItem> merge(EmailSigResponse resp){
        List<ContactsItem> createdContacts = resp.getResults().stream()
                .flatMap(result -> result.getContacts().stream())
                .collect(Collectors.toList());
        return merger.merge(readerWriter.readContacts(), createdContacts);
    }

    private EmailSigResponse getResponse(){
        if(propertiesConfig.config().getBoolean("input.test.response.enabled")){
            return readerWriter.readTestOut();
        }
        else {
            try {
                EmailSigRequest req = new EmailSigRequest();
                req.setMessages(readerWriter.readEmails());
                Response<EmailSigResponse> resp = client.scanEmails(req).execute();
                if (resp.isSuccessful()) {
                    return resp.body();
                } else {
                    throw new RuntimeException("api call to circleback was unsuccessful");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
