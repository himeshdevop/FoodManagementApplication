package com.example.foodapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchResponseViewModel extends ViewModel {
    private final MutableLiveData<String> response;

    public SearchResponseViewModel() {
        response = new MutableLiveData<>();
    }

    public MutableLiveData<String> getNutritionResponse() {
        return response;
    }

    public void setResponse(String value) {
        response.postValue(value);
    }
}
