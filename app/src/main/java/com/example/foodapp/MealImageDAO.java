package com.example.foodapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface MealImageDAO {

    @Insert
    void insert(MealImage mealImage);

    @Update
    void update(MealImage mealImage);

    @Query("SELECT * FROM mealImage_table")
    LiveData<List<MealImage>> getAllMeals();

    @Query("SELECT * FROM mealImage_table WHERE id = :id LIMIT 1")
    MealImage getMealById(int id);

    @Query("UPDATE mealImage_table SET cloudUrl = :cloudUrl WHERE id = :id")
    void updateMealById(int id, String cloudUrl);
}
