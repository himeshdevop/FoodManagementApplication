package com.example.foodapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MealDao {

    @Insert
    void insert(Meal meal);

    @Query("SELECT * FROM meal_table")
    LiveData<List<Meal>> getAllMeals();

    @Query("DELETE FROM meal_table")
    void deleteAllMeals();
}
