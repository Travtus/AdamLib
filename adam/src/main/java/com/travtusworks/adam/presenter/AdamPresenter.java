package com.travtusworks.adam.presenter;

import android.content.Context;
import android.util.Log;

import com.travtusworks.adam.R;
import com.travtusworks.adam.api.BuildingDAO;
import com.travtusworks.adam.api.ErrorDAO;
import com.travtusworks.adam.api.SentMessageDAO;
import com.travtusworks.adam.rest.RestApi;
import com.travtusworks.adam.utils.ErrorUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by teodora on 12.06.2018.
 */

public class AdamPresenter implements AdamContract.Presenter {

    private final String TAG = "AdamPresenter";

    @Inject
    Retrofit retrofit;

    WeakReference<AdamContract.View> mView;

    @Inject
    Context mContext;

    @Inject
    public AdamPresenter(Retrofit retrofit, AdamContract.View mView){

        this.retrofit = retrofit;
        this.mView = new WeakReference<AdamContract.View>(mView);

    }


    @Override
    public void startBook(int unitID) {

        Log.i(TAG,"start book");

        Call<Void> response = retrofit.create(RestApi.class).startBook(unitID);
        response.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (mView.get() != null){
                    if ((response.code() == 200) || (response.code() == 204)){
                        mView.get().bookStarted();
                    } /*else if (response.code() == 400){
                        ErrorDAO error = ErrorUtils.parseError(response, retrofit);
                        mView.get().showErrorMessage(error.getMessage());
                    } else {
                        mView.get().showErrorMessage(null);
                    }*/
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i(TAG,"ERROR " + t.getMessage());
                mView.get().bookStarted();
            }
        });

    }

    @Override
    public void getBuildings() {

        Log.i(TAG, "get buildings");

        Call<ArrayList<BuildingDAO>> response = retrofit.create(RestApi.class).getBuildings();
        response.enqueue(new Callback<ArrayList<BuildingDAO>>() {
            @Override
            public void onResponse(Call<ArrayList<BuildingDAO>> call, Response<ArrayList<BuildingDAO>> response) {
                if (mView.get() != null){
                    if (response.code() == 200){
                        mView.get().buildingsReceived(response.body());
                    } else if (response.code() == 400){
                        ErrorDAO error = ErrorUtils.parseError(response, retrofit);
                        mView.get().showErrorMessage(error.getMessage());
                    } else {
                        mView.get().showErrorMessage(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<BuildingDAO>> call, Throwable t) {
                Log.i(TAG,"ERROR " + t.getMessage());
                mView.get().showErrorMessage(mContext.getResources().getString(R.string.network_error));
            }
        });

    }

    @Override
    public void getUnits(int buildingID) {

        Log.i(TAG,"get units");

        Call<BuildingDAO> response = retrofit.create(RestApi.class).getUnits(buildingID);
        response.enqueue(new Callback<BuildingDAO>() {
            @Override
            public void onResponse(Call<BuildingDAO> call, Response<BuildingDAO> response) {
                if (mView.get() != null){
                    if (response.code() == 200){
                        mView.get().unitsReceived(response.body());
                    } else if (response.code() == 400){
                        ErrorDAO error = ErrorUtils.parseError(response, retrofit);
                        mView.get().showErrorMessage(error.getMessage());
                    } else {
                        mView.get().showErrorMessage(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<BuildingDAO> call, Throwable t) {
                Log.i(TAG,"ERROR " + t.getMessage());
                mView.get().showErrorMessage(mContext.getResources().getString(R.string.network_error));
            }
        });

    }

    @Override
    public void sendFirstMessage(String message) {

        Log.i(TAG,"send first message " + message);

        Call<SentMessageDAO> response = retrofit.create(RestApi.class).sendFirstMessage(message);
        response.enqueue(new Callback<SentMessageDAO>() {
            @Override
            public void onResponse(Call<SentMessageDAO> call, Response<SentMessageDAO> response) {

                if (mView.get() != null){
                    if (response.code() == 200){
                        mView.get().messageSent(response.body());
                    } else if (response.code() == 400){
                        ErrorDAO error = ErrorUtils.parseError(response, retrofit);
                        mView.get().showErrorMessage(error.getMessage());
                    } else {
                        mView.get().showErrorMessage(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<SentMessageDAO> call, Throwable t) {
                Log.i(TAG,"ERROR " + t.getMessage());
                mView.get().showErrorMessage(mContext.getResources().getString(R.string.network_error));
            }
        });

    }

    @Override
    public void sendMessage(String message, int chatID, boolean book) {

        Log.i(TAG,"send message " + message + " " + chatID);

        int bookVal = 0;
        if (book)
            bookVal = 1;

        Call<SentMessageDAO> response = retrofit.create(RestApi.class).sendMessage(message,chatID, bookVal);
        response.enqueue(new Callback<SentMessageDAO>() {
            @Override
            public void onResponse(Call<SentMessageDAO> call, Response<SentMessageDAO> response) {

                if (mView.get() != null){
                    if (response.code() == 200){
                        mView.get().messageSent(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<SentMessageDAO> call, Throwable t) {
                Log.i(TAG,"ERROR " + t.getMessage());
                mView.get().showErrorMessage(mContext.getResources().getString(R.string.network_error));
            }
        });

    }

    @Override
    public void sendImageMessage(String keyBucket, int chatID, boolean book) {

        Log.i(TAG,"send image message " + keyBucket + " " + chatID);

        int bookVal = 0;
        if (book)
            bookVal = 1;

        Call<SentMessageDAO> response = retrofit.create(RestApi.class).sendImageMessage(keyBucket,chatID, bookVal);
        response.enqueue(new Callback<SentMessageDAO>() {
            @Override
            public void onResponse(Call<SentMessageDAO> call, Response<SentMessageDAO> response) {

                if (mView.get() != null){
                    if (response.code() == 200){
                        mView.get().messageSent(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<SentMessageDAO> call, Throwable t) {
                Log.i(TAG,"ERROR " + t.getMessage());
                mView.get().showErrorMessage(mContext.getResources().getString(R.string.network_error));
            }
        });

    }
}
