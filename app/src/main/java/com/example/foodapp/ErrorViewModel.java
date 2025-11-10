package com.example.foodapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ErrorViewModel extends ViewModel {
    private final MutableLiveData<Integer> errorCode;

    public ErrorViewModel() {
        errorCode = new MutableLiveData<>(0);
    }

    public Integer getErrorCode() {
        return errorCode.getValue();
    }

    public void setErrorCode(Integer value) {
        errorCode.postValue(value);
    }
}
