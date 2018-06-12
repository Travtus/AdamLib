package com.travtusworks.adam.presenter;

import com.travtusworks.adam.api.BuildingDAO;
import com.travtusworks.adam.api.SentMessageDAO;

import java.util.ArrayList;

/**
 * Created by teodora on 12.06.2018.
 */

public interface AdamContract {

    interface View {

        void buildingsReceived(ArrayList<BuildingDAO> buildings);

        void unitsReceived(BuildingDAO building);

        void bookStarted();

        void messageSent(SentMessageDAO sentMessage);

        void showErrorMessage(String message);

    }

    interface Presenter {

        void startBook(int unitID);

        void getBuildings();

        void getUnits(int buildingID);

        void sendFirstMessage(String message);

        void sendMessage(String message, int chatID, boolean book);

        void sendImageMessage(String keyBucket, int chatID, boolean book);

    }

}
