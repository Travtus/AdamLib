package com.travtusworks.adam;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.travtusworks.adam.api.BuildingDAO;
import com.travtusworks.adam.api.SentMessageDAO;
import com.travtusworks.adam.di.components.DaggerAdamComponent;
import com.travtusworks.adam.di.modules.AdamModule;
import com.travtusworks.adam.di.modules.ContextModule;
import com.travtusworks.adam.presenter.AdamContract;
import com.travtusworks.adam.presenter.AdamPresenter;
import com.travtusworks.adam.utils.AdamListener;

import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by teodora on 11.06.2018.
 */

public class AdamActivity extends AppCompatActivity implements AdamListener, AdamContract.View{

    private static final String TAG = "AdamActivity";

    private ProgressDialog progress;
    private AdamChatFragment adamChatFragment;

    @Inject
    AdamPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity);

        DaggerAdamComponent.builder()
                .adamModule(new AdamModule(this))
                .contextModule(new ContextModule(this))
                .build().inject(this);

        progress = new ProgressDialog(this);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);

        startAdamFragment();

    }

    private void startAdamFragment(){   // A

        AdamFragment fragment = new AdamFragment();
        fragment.setListener(this);
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.activity, fragment);
        ft.addToBackStack(TAG);
        ft.commit();
        getSupportFragmentManager().executePendingTransactions();

    }

    @Override
    public void openAdamChatFragment() {

        adamChatFragment = new AdamChatFragment();
        adamChatFragment.setListener(this);
        /*adamChatFragment.setCurrentUser(preferences.getInt(Constants.USER_ID,0));
        adamChatFragment.setCurrentUserName(preferences.getString(Constants.USER_NAME,""));*/
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.activity, adamChatFragment);
        ft.addToBackStack(null);
        ft.commit();
        getSupportFragmentManager().executePendingTransactions();

    }

    @Override
    public void openBuildingSelectionFragment() {

    }

    @Override
    public void openUnitSelectionFragment(int buildingID) {

    }

    @Override
    public void startBook(int unitID) {

    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void uploadImage(File file, String key, Uri uri) {

    }

    @Override
    public void buildingsReceived(ArrayList<BuildingDAO> buildings) {

    }

    @Override
    public void unitsReceived(BuildingDAO building) {

    }

    @Override
    public void bookStarted() {

    }

    @Override
    public void messageSent(SentMessageDAO sentMessage) {

    }

    @Override
    public void showErrorMessage(String message) {

    }
}
