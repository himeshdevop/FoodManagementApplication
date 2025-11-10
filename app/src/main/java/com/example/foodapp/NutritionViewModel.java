package com.example.foodapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NutritionViewModel extends ViewModel {
    public MutableLiveData<NutritionData> nutritionData;

    public NutritionViewModel() {
        nutritionData = new MutableLiveData<>();
    }

    public MutableLiveData<NutritionData> getNutritionData() {
        return nutritionData;
    }


    public NutritionData getNutritionDataValue() {
        return nutritionData.getValue();
    }

    public void setNutritionData(NutritionData data) {
        nutritionData.postValue(data);
    }
}
