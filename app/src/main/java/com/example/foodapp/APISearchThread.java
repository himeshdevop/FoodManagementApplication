package com.example.foodapp;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import org.json.JSONArray;
import org.json.JSONObject;

public class APISearchThread extends Thread {

    private String searchKey;
    private RemoteUtilities remoteUtilities;
    private NutritionViewModel viewModel;
    private  AppViewModel appViewModel;

    public APISearchThread(String searchKey, Activity uiActivity, NutritionViewModel viewModel, AppViewModel appViewModel) {
        this.searchKey = searchKey;
        remoteUtilities = RemoteUtilities.getInstance(uiActivity);
        this.viewModel = viewModel;
        this.appViewModel = appViewModel;
    }

    public void run() {
        String endpoint = "https://trackapi.nutritionix.com/v2/natural/nutrients";
        HttpURLConnection connection = remoteUtilities.openConnection(endpoint);

        if (connection != null) {
            String jsonInputString = "{\"query\": \"" + searchKey + "\"}";
            remoteUtilities.writeRequestBody(connection, jsonInputString);

            if (remoteUtilities.isConnectionOkay(connection)) {
                String response = remoteUtilities.getResponseString(connection);
                connection.disconnect();
                parseResponse(response, searchKey);
            } else {
                Log.e("APISearchThread", "Connection failed");
            }
        }
    }

    private void parseResponse(String response, String searchItem) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONArray foodsArray = jsonResponse.getJSONArray("foods");

            if (foodsArray.length() > 0) {
                JSONObject foodItem = foodsArray.getJSONObject(0); // Getting the first food item

                double calories = foodItem.getDouble("nf_calories");
                double totalFat = foodItem.getDouble("nf_total_fat");
                double protein = foodItem.getDouble("nf_protein");
                double sugar = foodItem.getDouble("nf_sugars");
                double totalCarbohydrate = foodItem.getDouble("nf_total_carbohydrate");
                double dietaryFiber = foodItem.getDouble("nf_dietary_fiber");





/*Adding nutrient to the DB*/
                Nutrient nutrient = new Nutrient(calories, protein, totalCarbohydrate, totalFat, dietaryFiber, searchItem, sugar);
                NutrientDAO nutrientDAO = appViewModel.getNutrientDAO();
                nutrientDAO.insertNutrient(nutrient);

                /*...........*/
                NutritionData nutritionData = new NutritionData(calories, protein, totalCarbohydrate, totalFat, dietaryFiber, searchItem, sugar);
                viewModel.setNutritionData(nutritionData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("APISearchThread", "Error parsing response: " + e.getMessage());
        }
    }
}