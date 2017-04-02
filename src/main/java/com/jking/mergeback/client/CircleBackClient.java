package com.jking.mergeback.client;

import com.jking.mergeback.client.request.EmailSigRequest;
import com.jking.mergeback.client.response.EmailSigResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by john on 3/31/17.
 */
public interface CircleBackClient {

    @POST("service/sig-capture/scan")
    public Call<EmailSigResponse> scanEmails(@Body EmailSigRequest request);
}
