package com.example.foodapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class AppViewModel extends ViewModel {
    public AppViewModel(){
          fragmentClicked.setValue(0);
    }

    public MutableLiveData<ArrayList<NutritionData>> searchNutrientList = new MutableLiveData<ArrayList<NutritionData>>();
    public ArrayList<NutritionData> getNutrientList() {
        return searchNutrientList.getValue();}
    public void setNutrientList(ArrayList<NutritionData> pGameButtonList) {
        this.searchNutrientList.setValue(pGameButtonList);}

    public MutableLiveData<Integer> fragmentClicked = new MutableLiveData<>();
    public int getFragmentClicked() {return this.fragmentClicked.getValue();}
    public void setFragmentClicked(int fragmentClicked) {this.fragmentClicked.setValue(fragmentClicked);}

    public MutableLiveData<NutrientDAO> nutrientDAOLiveData = new MutableLiveData<>();
    public NutrientDAO getNutrientDAO() {
        return this.nutrientDAOLiveData.getValue();}
    public void setNutrientDAO(NutrientDAO nutrientDAO) {
        this.nutrientDAOLiveData.setValue(nutrientDAO);
    }

    public MutableLiveData<String> cloudString = new MutableLiveData<>();
    public String getCloudString() {
        return this.cloudString.getValue();}
    public void setCloudString(String cloudString) {
        this.cloudString.setValue(cloudString);}

    public MutableLiveData<Integer> cloudPosition = new MutableLiveData<>();
    public int getCloudPosition() {
        return this.cloudPosition.getValue();}
    public void setCloudPosition(int cloudPosition) {
        this.cloudPosition.setValue(cloudPosition);
    }


}
