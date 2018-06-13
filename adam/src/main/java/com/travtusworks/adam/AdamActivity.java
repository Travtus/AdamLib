package com.travtusworks.adam;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.travtusworks.adam.api.BuildingDAO;
import com.travtusworks.adam.api.MessageDAO;
import com.travtusworks.adam.api.SentMessageDAO;
import com.travtusworks.adam.di.components.DaggerAdamComponent;
import com.travtusworks.adam.di.modules.AdamModule;
import com.travtusworks.adam.di.modules.ContextModule;
import com.travtusworks.adam.presenter.AdamContract;
import com.travtusworks.adam.presenter.AdamPresenter;
import com.travtusworks.adam.utils.AdamListener;
import com.travtusworks.adam.utils.Constants;

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
    private AdamBuildingSelectionFragment buildingSelectionFragment;
    private AdamUnitSelectionFragment unitSelectionFragment;
    private int chatID;
    private String newMessage = "";
    private boolean bookVal = false;
    private int offset = 0;
    private int unitID = -1;

    private int userID = -1;
    private String userName = "";
    private int companyID = -1;

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

        userID = getIntent().getIntExtra(Constants.EXTRA_USER_ID, -1);
        userName = getIntent().getStringExtra(Constants.EXTRA_USER_NAME);
        companyID = getIntent().getIntExtra(Constants.EXTRA_COMPANY_ID, -1);

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
            adamChatFragment.setCurrentUser(userID);
            adamChatFragment.setCurrentUserName(userName);
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.activity, adamChatFragment);
            ft.addToBackStack(null);
            ft.commit();
            getSupportFragmentManager().executePendingTransactions();



    }

    private void uploadData(File file, final String key){

        /*BasicAWSCredentials credentials = new BasicAWSCredentials(Constants.AMAZON_KEY,Constants.AMAZON_SECRET);
        AmazonS3Client s3Client = new AmazonS3Client(credentials);

        TransferUtility transferUtility =
                TransferUtility.builder()
                        .context(getApplicationContext())
                        .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                        .s3Client(s3Client)
                        .build();

        TransferObserver uploadObserver =
                transferUtility.upload(
                        Constants.BUCKET,
                        key,
                        file);

        uploadObserver.setTransferListener(new TransferListener(){

            @Override
            public void onStateChanged(int id, TransferState state) {
                Log.i(TAG, "statechange :" + state);
                if (state.toString().equals("COMPLETED")){
                    if (progress.isShowing())
                        progress.dismiss();
                    presenter.sendImageMessage(key, chatID, bookVal);
                }
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                int percentage = (int) (bytesCurrent/bytesTotal * 100);
                Log.i(TAG, "percentage : " + percentage);

            }

            @Override
            public void onError(int id, Exception ex) {
                Log.i(TAG,"error : " + ex.getMessage());
            }

        });*/

    }

    public void showAlertDialog(String message) {

        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setMessage(message);
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, getResources().getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
        dialog.show();

    }

    @Override
    public void onBackPressed() {

        int size = getSupportFragmentManager().getBackStackEntryCount();
        Log.i(TAG, "size = " + size);
        if (size > 1) {
            getSupportFragmentManager().popBackStack(TAG, 0);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void openBuildingSelectionFragment() {

        buildingSelectionFragment = new AdamBuildingSelectionFragment();
        buildingSelectionFragment.setListener(this);
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.activity, buildingSelectionFragment);
        ft.addToBackStack(null);
        ft.commit();
        getSupportFragmentManager().executePendingTransactions();

        presenter.getBuildings();
        progress.show();
        //progress.setContentView(R.layout.my_progress);

    }

    @Override
    public void openUnitSelectionFragment(int buildingID) {

        unitSelectionFragment = new AdamUnitSelectionFragment();
        unitSelectionFragment.setListener(this);

        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.activity, unitSelectionFragment);
        ft.addToBackStack(null);
        ft.commit();
        getSupportFragmentManager().executePendingTransactions();

        presenter.getUnits(buildingID);
        progress.show();
        //progress.setContentView(R.layout.my_progress);

    }

    @Override
    public void startBook(int unitID) {

        openAdamChatFragment();

        presenter.startBook(unitID);
        progress.show();
//        progress.setContentView(R.layout.my_progress);

    }

    @Override
    public void sendMessage(String message) {

        if (message.length() > 0) {

            presenter.sendMessage(message, chatID, bookVal);

            adamChatFragment.sendMessage(new MessageDAO(message, userID));

        }

    }

    @Override
    public void uploadImage(File file, String key, Uri uri) {

        adamChatFragment.sendMessage(new MessageDAO(uri, userID));

        uploadData(file, key);
        progress.show();
//        progress.setContentView(R.layout.my_progress);

    }

    @Override
    public void buildingsReceived(ArrayList<BuildingDAO> buildings) {

        Log.i(TAG,"buildings received");

        if (progress.isShowing()){
            progress.dismiss();
        }

        buildingSelectionFragment.setBuildings(buildings);

    }

    @Override
    public void unitsReceived(BuildingDAO building) {

        Log.i(TAG,"units received");

        if (progress.isShowing()){
            progress.dismiss();
        }

        unitSelectionFragment.setUnits(building.getUnits());

    }

    @Override
    public void bookStarted() {

    }

    @Override
    public void messageSent(SentMessageDAO sentMessage) {

        Log.i(TAG, "sent message");

        if (progress.isShowing())
            progress.dismiss();

        if (chatID == 0){
            chatID = sentMessage.getSentMessage().getChat().getChatID();
        }

        adamChatFragment.setSentMessage(sentMessage);

    }

    @Override
    public void showErrorMessage(String message) {

        if (progress.isShowing())
            progress.dismiss();

        if (message == null)
            message = getResources().getString(R.string.generic_error);

        showAlertDialog(message);

    }
}
