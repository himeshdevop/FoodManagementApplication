package com.example.foodapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Nutrient.class}, version = 1)
public abstract class NutrientDatabase extends RoomDatabase {
    public abstract NutrientDAO nutrientDAO();
}