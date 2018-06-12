package com.travtusworks.adam.api;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by teodora on 12.06.2018.
 */

public class MessageDAO {

    @SerializedName("id")
    private int messageID;

    @SerializedName("chat")
    private ChatDAO chat;

    @SerializedName("user")
    private CurrentUserDAO user;

    @SerializedName("created")
    private long created;

    @SerializedName("message")
    private MessageContentDAO message;

    private boolean image = false;
    private Uri imageUri;

    public MessageDAO(String text, int userID){
        this.message = new MessageContentDAO(text);
        this.user = new CurrentUserDAO(userID);
        this.image = false;
    }

    public MessageDAO(Uri photo, int userID){
        this.imageUri = photo;
        this.user = new CurrentUserDAO(userID);
        this.image = true;
    }

    public int getMessageID() {
        return messageID;
    }

    public ChatDAO getChat() {
        return chat;
    }

    public int getUserID() {
        return user.getId();
    }

    public long getCreated() {
        return created;
    }

    public String getMessage() {
        return message.getMessageContent();
    }

    public boolean isImage() {
        return image;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public ArrayList<String> getSuggestions(){
        return message.getSuggestions();
    }

    public ArrayList<CardDAO> getCards(){
        return message.getCards();
    }

    public ListCardDAO getListCard(){
        return message.getListCard();
    }

}
