package com.example.foodapp;

import android.content.Context;
import androidx.room.Room;

public class NutrientDBInstance {
    private static NutrientDatabase database;

    public static NutrientDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context,
                            NutrientDatabase.class, "nutrient_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    }
}

