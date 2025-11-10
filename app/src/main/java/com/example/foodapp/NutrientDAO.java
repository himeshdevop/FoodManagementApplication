package com.example.foodapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NutrientDAO {

    @Insert
    void insertNutrient(Nutrient... nutrient);

    @Update
    void updateNutrient(Nutrient... nutrient);

    @Delete
    void deleteNutrient(Nutrient... nutrient);

    @Query("SELECT * FROM nutrients")
    List<Nutrient> getAllNutrients();

    @Query("SELECT * FROM nutrients WHERE id = :nutrientId")
    Nutrient getNutrientByID(int nutrientId);
}
