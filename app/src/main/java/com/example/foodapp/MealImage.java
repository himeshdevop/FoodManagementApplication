package com.example.foodapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mealImage_table")
public class MealImage {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String imageUri;
    private String cloudUrl=" ";


    public MealImage(String imageUri) {
        this.imageUri = imageUri;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }


    public String getCloudUrl() {
        return cloudUrl;
    }


    public void setCloudUrl(String cloudUrl) {
        this.cloudUrl = cloudUrl;
    }
}