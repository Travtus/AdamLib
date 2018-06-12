package com.travtusworks.adam.utils;

import android.net.Uri;

import java.io.File;

/**
 * Created by teodora on 11.06.2018.
 */

public interface AdamListener {

    void openAdamChatFragment();

    void openBuildingSelectionFragment();

    void openUnitSelectionFragment(int buildingID);

    void startBook(int unitID);

    void sendMessage(String message);

    void loadMore();

    void uploadImage(File file, String key, Uri uri);

    void showErrorMessage(String message);

}
