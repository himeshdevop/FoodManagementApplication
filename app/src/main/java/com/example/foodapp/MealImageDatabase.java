package com.example.foodapp;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MealImage.class}, version = 1)
public abstract class MealImageDatabase extends RoomDatabase {

    public abstract MealImageDAO mealImageDao();

    private static volatile MealImageDatabase INSTANCE;

    public static MealImageDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MealImageDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MealImageDatabase.class, "mealImage_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
