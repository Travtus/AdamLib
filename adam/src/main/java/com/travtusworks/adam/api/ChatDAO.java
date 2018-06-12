package com.travtusworks.adam.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by teodora on 12.06.2018.
 */

public class ChatDAO {

    @SerializedName("id")
    private int chatID;

    public int getChatID() {
        return chatID;
    }
}
