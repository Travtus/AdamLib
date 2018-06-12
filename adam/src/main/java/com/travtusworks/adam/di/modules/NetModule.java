package com.travtusworks.adam.di.modules;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by teodora on 12.06.2018.
 */

@Module
public class NetModule {

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient().newBuilder()
                .addInterceptor(logging)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build();
    }

    @Provides
    @Inject
    @Singleton
    Retrofit provideRetrofit(OkHttpClient httpClient){

        //final String apiBaseUrl = "http://api.travtus.com/";
        //final String apiBaseUrl = "http://apiuat.travtus.com/";
        final String apiBaseUrl = "http://apidev.travtus.com/";

        return new Retrofit.Builder()
                .baseUrl(apiBaseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.newBuilder()
                        .build())
                .build();
    }

}
