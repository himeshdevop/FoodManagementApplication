package com.example.foodapp;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class MealViewModel extends AndroidViewModel {

    private MealRepository repository;
    private LiveData<List<Meal>> allMeals;

    public MealViewModel(@NonNull Application application) {
        super(application);
        repository = new MealRepository(application);
        allMeals = repository.getAllMeals();
    }



    public LiveData<List<Meal>> getAllMeals() {
        return allMeals;
    }

    public void insert(Meal meal) {
        repository.insert(meal);
    }

    public void deleteAllMeals() {
        repository.deleteAllMeals();
    }
}
