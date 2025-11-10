package com.example.foodapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "nutrients")
public class Nutrient {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "food_item")
    private String foodItem;

    @ColumnInfo(name = "calories")
    private double calories;

    @ColumnInfo(name = "protein")
    private double protein;

    @ColumnInfo(name = "carbohydrates")
    private double carbohydrates;

    @ColumnInfo(name = "total_fat")
    private double totalFat;

    @ColumnInfo(name = "dietary_fiber")
    private double dietaryFiber;

    @ColumnInfo(name = "sugar")
    private double sugar;


    public Nutrient(double calories, double protein, double carbohydrates, double totalFat, double dietaryFiber, String foodItem, double sugar) {
        this.foodItem = foodItem;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.totalFat = totalFat;
        this.dietaryFiber = dietaryFiber;
        this.sugar = sugar;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }

    public double getDietaryFiber() {
        return dietaryFiber;
    }

    public void setDietaryFiber(double dietaryFiber) {
        this.dietaryFiber = dietaryFiber;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }
}
