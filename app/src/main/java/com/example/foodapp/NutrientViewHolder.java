package com.example.foodapp;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class NutrientViewHolder extends RecyclerView.ViewHolder {

    public TextView caloriesTextView;
    public TextView totalFatTextView;
    public TextView proteinTextView;
    public TextView totalCarbohydrateTextView;
    public TextView dietaryFiberTextView;
    public  TextView sugars;
    public TextView item;

    public NutrientViewHolder(View itemView) {
        super(itemView);
        caloriesTextView = itemView.findViewById(R.id.caloriesTextView);
        totalFatTextView = itemView.findViewById(R.id.totalFatTextView);
        proteinTextView = itemView.findViewById(R.id.proteinTextView);
        totalCarbohydrateTextView = itemView.findViewById(R.id.totalCarbohydrateTextView);
        dietaryFiberTextView = itemView.findViewById(R.id.dietaryFiberTextView);
        sugars = itemView.findViewById(R.id.sugarTextView);
        item = itemView.findViewById(R.id.itemTextView);
    }


}
