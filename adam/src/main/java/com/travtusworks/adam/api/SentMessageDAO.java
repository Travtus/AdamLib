package com.travtusworks.adam.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by teodora on 12.06.2018.
 */

public class SentMessageDAO {

    @SerializedName("to")
    private MessageDAO sentMessage;

    @SerializedName("response")
    private MessageDAO receivedMessage;

    public MessageDAO getSentMessage() {
        return sentMessage;
    }

    public MessageDAO getReceivedMessage() {
        return receivedMessage;
    }

}
