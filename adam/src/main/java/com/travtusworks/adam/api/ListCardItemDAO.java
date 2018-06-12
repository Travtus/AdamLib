package com.travtusworks.adam.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by teodora on 12.06.2018.
 */

public class ListCardItemDAO {

    @SerializedName("title")
    private String title;

    @SerializedName("text")
    private String text;

    @SerializedName("image")
    private String image;

    @SerializedName("interactionText")
    private String interactionText;

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        if (image == null)
            return "";
        return image;
    }

    public String getInteractionText() {
        return interactionText;
    }
}
