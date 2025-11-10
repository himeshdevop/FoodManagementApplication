package com.example.foodapp;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.R;
import com.example.foodapp.Meal;
import com.example.foodapp.MealAdapter;
import com.example.foodapp.MealViewModel;
import java.util.List;

public class SummaryFragment extends Fragment {

    private TextView tvTotalCalories, tvDailyGoal;
    private RecyclerView recyclerView;
    private MealAdapter mealAdapter;
    private MealViewModel mealViewModel;
    private static final int DAILY_GOAL = 2000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        AppViewModel viewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);
        Button back = view.findViewById(R.id.summaryBack);
        Button resetCalorie = view.findViewById(R.id.resetCalorie);

        resetCalorie.setText("Reset Total Calories");
        back.setText("Back");

        tvTotalCalories = view.findViewById(R.id.tvTotalCalories);
        tvDailyGoal = view.findViewById(R.id.tvDailyGoal);
        recyclerView = view.findViewById(R.id.recyclerView);

        mealAdapter = new MealAdapter();
        recyclerView.setAdapter(mealAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mealViewModel = new ViewModelProvider(this).get(MealViewModel.class);
        mealViewModel.getAllMeals().observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                mealAdapter.setMeals(meals);
                updateTotalCalories(meals);
            }
        });

        tvDailyGoal.setText("Daily Goal: " + DAILY_GOAL + " kcal");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setFragmentClicked(1);
            }
        });

        resetCalorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mealViewModel.deleteAllMeals();
            }
        });

        return view;
    }

    private void updateTotalCalories(List<Meal> meals) {
        int totalCalories = 0;
        for (Meal meal : meals) {
            totalCalories += meal.getCalories();
        }
        tvTotalCalories.setText("Total Calories: " + totalCalories + " kcal");
    }
}