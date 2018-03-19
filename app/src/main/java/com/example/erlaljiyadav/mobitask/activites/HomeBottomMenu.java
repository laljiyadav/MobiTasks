package com.example.erlaljiyadav.mobitask.activites;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.erlaljiyadav.mobitask.R;
import com.example.erlaljiyadav.mobitask.fragments.Fragment_Explore_News;
import com.example.erlaljiyadav.mobitask.fragments.Fragment_home;
import com.example.erlaljiyadav.mobitask.fragments.Fragment_profile;
import com.example.erlaljiyadav.mobitask.fragments.Today_Task;

public class HomeBottomMenu extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_bottom_menu);

        setupBottomNavigation();

        if (savedInstanceState == null) {

           loadProfileFragment();
        }
    }
    private void setupBottomNavigation() {

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_home:
                        loadProfileFragment();
                        return true;

                    case R.id.action_news:
                        loadFragmentExploreNews();
                        return true;
                    case R.id.action_quiz:
                        loadNewsFragment();
                        return true;
                }
                return false;
            }
        });
    }


    private void loadProfileFragment() {

        Today_Task fragment = Today_Task.newInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, fragment);
        ft.commit();
    }

    private void loadNewsFragment()
    {
        Fragment_profile fragment = Fragment_profile.newInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, fragment);
        ft.commit();
    }
    private void loadFragmentExploreNews()
    {
        Fragment_Explore_News fragment = Fragment_Explore_News.newInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, fragment);
        ft.commit();
    }


}
