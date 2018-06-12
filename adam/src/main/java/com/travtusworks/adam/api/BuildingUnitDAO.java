package com.travtusworks.adam.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by teodora on 12.06.2018.
 */

public class BuildingUnitDAO implements ScopeItem {

    @SerializedName("id")
    private int unitID;

    @SerializedName("unit")
    private String unit;

    boolean selected = false;

    @Override
    public int getInt() {
        return unitID;
    }

    @Override
    public String getName() {
        return unit;
    }

    @Override
    public void setSelected(boolean val) {
        selected = val;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }
}
