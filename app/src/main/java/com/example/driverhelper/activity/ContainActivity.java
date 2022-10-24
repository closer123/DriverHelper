package com.example.driverhelper.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.driverhelper.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContainActivity extends AppCompatActivity {
    private NavHostFragment navHostFragment;
    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private AppBarConfiguration configuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contain);
        bottmInitView();


    }
    private void bottmInitView() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();
        configuration = new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}