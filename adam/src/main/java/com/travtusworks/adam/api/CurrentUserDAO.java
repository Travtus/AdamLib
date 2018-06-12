package com.travtusworks.adam.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by teodora on 12.06.2018.
 */

public class CurrentUserDAO {

    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String userName;

    public CurrentUserDAO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}
