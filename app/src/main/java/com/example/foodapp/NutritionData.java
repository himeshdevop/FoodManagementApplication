package com.example.foodapp;

public class NutritionData {
    private String foodItem;
    private double calories;
    private double protein;
    private double carbohydrates;
    private double totalFat;
    private double dietaryFiber;
    private double sugar;

    public NutritionData(double calories, double protein, double carbohydrates, double totalFat, double dietaryFiber, String foodItem, double sugar ) {
        this.foodItem = foodItem;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.totalFat = totalFat;
        this.dietaryFiber = dietaryFiber;
        this.sugar = sugar;
    }

    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public double getDietaryFiber() {
        return dietaryFiber;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public double getSugar() {
        return sugar;
    }
}