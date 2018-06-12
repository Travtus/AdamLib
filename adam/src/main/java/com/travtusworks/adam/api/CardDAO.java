package com.travtusworks.adam.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by teodora on 12.06.2018.
 */

public class CardDAO {

    @SerializedName("title")
    String title;

    @SerializedName("text")
    String text;

    @SerializedName("image")
    String image;

    @SerializedName("interactionText")
    String interactionText;

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }

    public String getInteractionText() {
        return interactionText;
    }
}
