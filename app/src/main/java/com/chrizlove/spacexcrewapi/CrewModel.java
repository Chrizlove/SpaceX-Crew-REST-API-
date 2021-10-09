package com.chrizlove.spacexcrewapi;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "crew_table")
public class CrewModel {

    @PrimaryKey @NonNull @ColumnInfo(name = "id") @SerializedName("id") private String id;

    @ColumnInfo(name = "name") @SerializedName("name") private String name;

    @ColumnInfo(name = "agency") @SerializedName("agency") private String agency;

    @ColumnInfo(name = "image") @SerializedName("image") private String image;

    @ColumnInfo(name = "wikipedia") @SerializedName("wikipedia") private String wikipedia;

    @ColumnInfo(name = "status") @SerializedName("status") private String status;

    public CrewModel(@NonNull String id, String name, String agency, String image, String wikipedia, String status) {
        this.id = id;
        this.name = name;
        this.agency = agency;
        this.image = image;
        this.wikipedia = wikipedia;
        this.status = status;
    }


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
