package com.example.foodapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    APISearchFragment apiSearchFragment = new APISearchFragment();
    HomeFragment homeFragment = new HomeFragment();
    DisplaySearchNutrientFragment displaySearchNutrientFragment = new DisplaySearchNutrientFragment();
    MealFragment mealFragment = new MealFragment();
    SummaryFragment summaryFragment = new SummaryFragment();
    PhotoCaptureFragment photoCaptureFragment = new PhotoCaptureFragment();
    PhotoListFragment photoListFragment = new PhotoListFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NutrientDAO nutrientDAO = NutrientDBInstance.
                getDatabase(getApplicationContext()).nutrientDAO();




        AppViewModel viewModel = new ViewModelProvider(this).get(AppViewModel.class);
        viewModel.setNutrientDAO(nutrientDAO);

       loadHomeFragment();

        viewModel.fragmentClicked.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                if (viewModel.getFragmentClicked() == 1) {
                    loadHomeFragment();
                }
                else if(viewModel.getFragmentClicked() == 2){
                    loadAPISearchFragment();
                }
                else if(viewModel.getFragmentClicked() == 3){
                    loadDisplayNutrientFragment();
                }
                else if(viewModel.getFragmentClicked()==4){
                    loadMealFragment();
                }else if(viewModel.getFragmentClicked()==5){
                    loadSummaryFragment();
                }else if(viewModel.getFragmentClicked()==6){
                    loadPhotoCaptureFragment();
                }else if(viewModel.getFragmentClicked()==7){
                    loadPhotoListFragment();
                }

            }});


    }

    private void loadAPISearchFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.mainFrame);
        if(frag == null) {
            fm.beginTransaction().add(R.id.mainFrame,apiSearchFragment).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.mainFrame,apiSearchFragment).commit();
        }
    }

    private void loadDisplayNutrientFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.mainFrame);
        if(frag == null) {
            fm.beginTransaction().add(R.id.mainFrame,displaySearchNutrientFragment).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.mainFrame,displaySearchNutrientFragment).commit();
        }
    }

    private void loadHomeFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.mainFrame);
        if(frag == null) {
            fm.beginTransaction().add(R.id.mainFrame,homeFragment).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.mainFrame,homeFragment).commit();
        }
    }

    private void loadMealFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.mainFrame);
        if(frag == null) {
            fm.beginTransaction().add(R.id.mainFrame,mealFragment).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.mainFrame,mealFragment).commit();
        }
    }

    private void loadSummaryFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.mainFrame);
        if(frag == null) {
            fm.beginTransaction().add(R.id.mainFrame,summaryFragment).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.mainFrame,summaryFragment).commit();
        }
    }

    private void loadPhotoCaptureFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.mainFrame);
        if(frag == null) {
            fm.beginTransaction().add(R.id.mainFrame,photoCaptureFragment).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.mainFrame,photoCaptureFragment).commit();
        }
    }

    private void loadPhotoListFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.mainFrame);
        if(frag == null) {
            fm.beginTransaction().add(R.id.mainFrame,photoListFragment).commit();
        }
        else {
            fm.beginTransaction().replace(R.id.mainFrame,photoListFragment).commit();
        }
    }



}