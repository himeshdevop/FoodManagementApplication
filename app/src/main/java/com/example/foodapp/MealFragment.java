package com.example.foodapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.foodapp.R;
import com.example.foodapp.Meal;
import com.example.foodapp.MealViewModel;

import java.util.ArrayList;

public class MealFragment extends Fragment {

    private EditText etFoodName, etPortionSize, etCalories;
    private Spinner spMealType;
    private Button btnAddMeal;
    private MealViewModel mealViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal, container, false);

        Button back = view.findViewById(R.id.mealBack);
        AppViewModel viewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);
        NutritionViewModel nutritionViewModel = new ViewModelProvider(getActivity()).get(NutritionViewModel.class);;

        back.setText("Back");


        etFoodName = view.findViewById(R.id.etFoodName);
        etPortionSize = view.findViewById(R.id.etPortionSize);
        etCalories = view.findViewById(R.id.etCalories);
        spMealType = view.findViewById(R.id.spMealType);
        btnAddMeal = view.findViewById(R.id.btnAddMeal);

        btnAddMeal.setText("Add Meal");

        mealViewModel = new ViewModelProvider(this).get(MealViewModel.class);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setFragmentClicked(1);
            }
        });



        btnAddMeal.setOnClickListener(v -> {
            String foodName = etFoodName.getText().toString().trim();
            String portionSizeStr = etPortionSize.getText().toString().trim();
            String caloriesStr = etCalories.getText().toString().trim();
            String mealType = spMealType.getSelectedItem().toString();

            if (TextUtils.isEmpty(foodName) || TextUtils.isEmpty(portionSizeStr) || TextUtils.isEmpty(caloriesStr)) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double portionSize = Double.parseDouble(portionSizeStr);
            int calories = Integer.parseInt(caloriesStr);

            /*API*/
            String query = foodName;
            if (!query.isEmpty()) {
                //progressBar.setVisibility(View.VISIBLE);
                new APISearchThread(query, getActivity(), nutritionViewModel, viewModel).start();
            } else {
                Toast.makeText(getActivity(), "Please enter a query", Toast.LENGTH_SHORT).show();
            }
            /**/

            Meal meal = new Meal(foodName, portionSize, calories, mealType);
            mealViewModel.insert(meal);



            Toast.makeText(getContext(), "Meal added successfully", Toast.LENGTH_SHORT).show();
            // Clear the fields after adding
            etFoodName.setText("");
            etPortionSize.setText("");
            etCalories.setText("");

        });

        nutritionViewModel.nutritionData.observe(getViewLifecycleOwner(), response -> {
            // Handle the response and update UI
            if (response != null) {
              //  progressBar.setVisibility(View.INVISIBLE);
                Log.d("TAG", ""+nutritionViewModel.getNutritionData().getValue().getCalories()+"..."+nutritionViewModel.getNutritionData().getValue().getCarbohydrates());
                ArrayList<NutritionData> nutrientList = viewModel.getNutrientList();
                if (nutrientList == null) {
                    nutrientList = new ArrayList<NutritionData>();
                }
               /* resultText.setText(
                        "Item: "+nutritionViewModel.getNutritionDataValue().getFoodItem()+"\n"+
                                "Calories: "+nutritionViewModel.getNutritionDataValue().getCalories()+" kcal"+"\n"+
                                "Total Fat: "+nutritionViewModel.getNutritionDataValue().getTotalFat()+" grams"+"\n"+
                                "Protein: "+nutritionViewModel.getNutritionDataValue().getProtein()+" grams"+"\n"+
                                "Carbohydrates: "+nutritionViewModel.getNutritionDataValue().getCarbohydrates()+" grams"+"\n"+
                                "Sugars: "+nutritionViewModel.getNutritionDataValue().getSugar()+" grams"+"\n"+
                                "Fiber: "+nutritionViewModel.getNutritionDataValue().getDietaryFiber()+" grams"+"\n"

                );*/
                nutrientList.add(nutritionViewModel.getNutritionDataValue());


                viewModel.setNutrientList(nutrientList);
            }
        });

        return view;
    }
}