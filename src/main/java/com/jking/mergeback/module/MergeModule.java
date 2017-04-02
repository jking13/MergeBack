package com.jking.mergeback.module;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.jking.mergeback.client.CircleBackClient;
import com.jking.mergeback.config.PropertiesConfig;
import com.jking.mergeback.io.EmailSigReaderWriter;
import com.jking.mergeback.merge.ContactsItemMerger;
import com.jking.mergeback.merge.PhoneNumbersItemMerger;
import com.jking.mergeback.worker.MergeWorker;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

/**
 * Created by john on 4/1/17.
 */
public class MergeModule extends AbstractModule {
    @Override
    protected void configure() {
    }

    @Provides
    CircleBackClient circleBackClient(PropertiesConfig propertiesConfig) {
        String API_BASE_URL = "https://api.circleback.com";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain ->
            chain.proceed(chain.request()
                        .newBuilder()
                        .addHeader("X-CB-ApiKey", propertiesConfig.config().getString("api.key"))
                        .build())
        );

        if(propertiesConfig.config().getBoolean("log.requests.enabled")) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(interceptor);
        }

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()
                                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)));

        Retrofit retrofit = builder
                .client(httpClient.build())
                .build();


        return retrofit.create(CircleBackClient.class);
    }
}
