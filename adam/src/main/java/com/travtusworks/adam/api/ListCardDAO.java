package com.travtusworks.adam.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by teodora on 12.06.2018.
 */

public class ListCardDAO {

    @SerializedName("items")
    private ArrayList<ListCardItemDAO> items;

    @SerializedName("title")
    private String title;

    public ArrayList<ListCardItemDAO> getItems() {
        return items;
    }

    public String getTitle() {
        return title;
    }
}
