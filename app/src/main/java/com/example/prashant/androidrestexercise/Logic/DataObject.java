package com.example.prashant.androidrestexercise.Logic;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataObject implements Serializable {


    @SerializedName("title")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("imageHref")
    private String image;

    public String getmName() {
        return name;
    }

    public void setmName(String name) {
        this.name = name;
    }

    public String getmDescription() {
        return description;
    }

    public void setmDescription(String description) {
        this.description = description;
    }

    public String getmImage() {
        return image;
    }

    public void setmImage(String image) {
    }
}
