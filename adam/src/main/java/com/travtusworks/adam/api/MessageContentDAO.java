package com.travtusworks.adam.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by teodora on 12.06.2018.
 */

public class MessageContentDAO {

    @SerializedName("content")
    String messageContent;

    @SerializedName("suggestions")
    ArrayList<String> suggestions;

    @SerializedName("cards")
    ArrayList<CardDAO> cards;

    @SerializedName("list_cards")
    ListCardDAO listCard;

    public MessageContentDAO(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public ArrayList<CardDAO> getCards() {
        return cards;
    }

    public ArrayList<String> getSuggestions() {
        return suggestions;
    }

    public ListCardDAO getListCard() {
        return listCard;
    }
}
