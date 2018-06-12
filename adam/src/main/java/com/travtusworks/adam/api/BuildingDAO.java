package com.travtusworks.adam.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by teodora on 12.06.2018.
 */

public class BuildingDAO implements ScopeItem {

    @SerializedName("id")
    private int buildingID;

    @SerializedName("name")
    private String buildingName;

    @SerializedName("building_units")
    private ArrayList<BuildingUnitDAO> units;

    boolean selected = false;

    public ArrayList<BuildingUnitDAO> getUnits() {
        return units;
    }

    @Override
    public int getInt() {
        return buildingID;
    }

    @Override
    public String getName() {
        return buildingName;
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
