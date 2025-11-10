package com.example.foodapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PhotoListFragment extends Fragment {

    private RecyclerView recyclerView;
    private PhotoListAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_list, container, false);
        Button back = view.findViewById(R.id.imageListBack);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AppViewModel viewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);
        adapter = new PhotoListAdapter(getContext(), viewModel);
        recyclerView.setAdapter(adapter);
        back.setText("Back");


        MealImageDatabase.getDatabase(getContext()).mealImageDao().getAllMeals().observe(getViewLifecycleOwner(), new Observer<List<MealImage>>() {
            @Override
            public void onChanged(List<MealImage> meals) {
                adapter.setMeals(meals);
            }
        });

        back.setOnClickListener(v ->
                viewModel.setFragmentClicked(1)
        );




        return view;
    }
}
