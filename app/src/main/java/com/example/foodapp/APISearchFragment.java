package com.example.foodapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link APISearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class APISearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public APISearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment APISearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static APISearchFragment newInstance(String param1, String param2) {
        APISearchFragment fragment = new APISearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a_p_i_search, container, false);

        AppViewModel viewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);

        Button backButton = view.findViewById(R.id.searchBack);
        backButton.setText("Back");
        ProgressBar progressBar = view.findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setFragmentClicked(1);
            }
        });


        EditText queryEditText;
        Button searchButton;

        NutritionViewModel nutritionViewModel = new ViewModelProvider(getActivity()).get(NutritionViewModel.class);;
        AppViewModel appViewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);
       TextView resultText;

        queryEditText = view.findViewById(R.id.queryEditText);
        searchButton = view.findViewById(R.id.searchButton);
        searchButton.setText("Search");
        resultText = view.findViewById(R.id.searchResult);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = queryEditText.getText().toString().trim();
                if (!query.isEmpty()) {
                    progressBar.setVisibility(View.VISIBLE);
                    new APISearchThread(query, getActivity(), nutritionViewModel, appViewModel).start();
                } else {
                    Toast.makeText(getActivity(), "Please enter a query", Toast.LENGTH_SHORT).show();
                }
            }
        });

        nutritionViewModel.nutritionData.observe(getViewLifecycleOwner(), response -> {
            // Handle the response and update UI
            if (response != null) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.d("TAG", ""+nutritionViewModel.getNutritionData().getValue().getCalories()+"..."+nutritionViewModel.getNutritionData().getValue().getCarbohydrates());
                ArrayList<NutritionData> nutrientList = viewModel.getNutrientList();
                if (nutrientList == null) {
                    nutrientList = new ArrayList<NutritionData>();
                }
                resultText.setText(
                        "Item: "+nutritionViewModel.getNutritionDataValue().getFoodItem()+"\n"+
                        "Calories: "+nutritionViewModel.getNutritionDataValue().getCalories()+" kcal"+"\n"+
                                "Total Fat: "+nutritionViewModel.getNutritionDataValue().getTotalFat()+" grams"+"\n"+
                                "Protein: "+nutritionViewModel.getNutritionDataValue().getProtein()+" grams"+"\n"+
                                "Carbohydrates: "+nutritionViewModel.getNutritionDataValue().getCarbohydrates()+" grams"+"\n"+
                                "Sugars: "+nutritionViewModel.getNutritionDataValue().getSugar()+" grams"+"\n"+
                                "Fiber: "+nutritionViewModel.getNutritionDataValue().getDietaryFiber()+" grams"+"\n"

                        );
                nutrientList.add(nutritionViewModel.getNutritionDataValue());


                viewModel.setNutrientList(nutrientList);
            }
        });

        return view;
    }
}