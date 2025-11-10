package com.example.foodapp;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MealRepository {

    private MealDao mealDao;
    private LiveData<List<Meal>> allMeals;
    private ExecutorService executorService;

    public MealRepository(Application application) {
        MealDatabase db = MealDatabase.getDatabase(application);
        mealDao = db.mealDao();
        allMeals = mealDao.getAllMeals();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Meal>> getAllMeals() {
        return allMeals;
    }
    public void deleteAllMeals() {
        executorService.execute(() -> mealDao.deleteAllMeals());
    }



    public void insert(Meal meal) {

        executorService.execute(() -> mealDao.insert(meal));
    }

}
