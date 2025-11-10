package com.example.foodapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal_table")
public class Meal {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String foodName;
    private double portionSize;
    private int calories;
    private String mealType;

    public Meal(String foodName, double portionSize, int calories, String mealType) {
        this.foodName = foodName;
        this.portionSize = portionSize;
        this.calories = calories;
        this.mealType = mealType;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(double portionSize) {
        this.portionSize = portionSize;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }
}
