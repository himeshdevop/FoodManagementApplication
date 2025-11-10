package com.example.foodapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NutritionAdapter extends RecyclerView.Adapter<NutrientViewHolder> {
    List<Nutrient> data;
    LifecycleOwner lifecycleOwner;
    public NutritionAdapter(List<Nutrient> data, LifecycleOwner lifecycleOwner){
        this.data = data;
        this.lifecycleOwner = lifecycleOwner;
    }
    @NonNull
    @Override
    public NutrientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.nutrient_item, parent, false);
        NutrientViewHolder myDataVHolder = new NutrientViewHolder(view);
        return myDataVHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NutrientViewHolder holder, int position) {
       Nutrient nutrientData = data.get(position);
       holder.caloriesTextView.setText("Calories: "+nutrientData.getCalories()+" kcal");
       holder.proteinTextView.setText("Protein: "+nutrientData.getProtein()+" grams ");
       holder.totalFatTextView.setText("Total Fat: "+nutrientData.getTotalFat()+" grams");
       holder.dietaryFiberTextView.setText("Fiber: "+nutrientData.getDietaryFiber()+" grams");
       holder.totalCarbohydrateTextView.setText("Carbohydrate: "+nutrientData.getCarbohydrates()+" grams");
       holder.item.setText("Item: "+nutrientData.getFoodItem());
       holder.sugars.setText("Sugars: "+nutrientData.getSugar()+" grams");



    }

    @Override
    public int getItemCount() {

        return data.size();
    }


}
