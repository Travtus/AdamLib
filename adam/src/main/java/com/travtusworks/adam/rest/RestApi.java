package com.travtusworks.adam.rest;

import com.travtusworks.adam.api.BuildingDAO;
import com.travtusworks.adam.api.SentMessageDAO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by teodora on 12.06.2018.
 */

public interface RestApi {

    @Multipart
    @POST("api/v1/chat/start/book")
    Call<Void> startBook(@Part("unitId") int unitID);

    @GET("api/v1/company/buildings/list")
    Call<ArrayList<BuildingDAO>> getBuildings();

    @GET("api/v1/portfolio/building/{id}")
    Call<BuildingDAO> getUnits(@Path("id") int buildingID);

    @Multipart
    @POST("api/v1/chat/send/content")
    Call<SentMessageDAO> sendFirstMessage(@Part("content") String message);

    @Multipart
    @POST("api/v1/chat/send/content")
    Call<SentMessageDAO> sendMessage(@Part("content") String message,
                                     @Part("chat_id") int chatID,
                                     @Part("book") int book);

    @Multipart
    @POST("api/v1/chat/send/content")
    Call<SentMessageDAO> sendImageMessage(@Part("key_bucket") String keyBucket,
                                          @Part("chat_id") int chatID,
                                          @Part("book") int book);

}
