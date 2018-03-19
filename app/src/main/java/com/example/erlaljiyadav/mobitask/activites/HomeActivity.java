package com.example.erlaljiyadav.mobitask.activites;

import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.erlaljiyadav.mobitask.R;
import com.example.erlaljiyadav.mobitask.fragments.Fragment_profile;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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

                }
                return false;
            }
        });
    }


    private void loadProfileFragment() {

        Fragment_profile fragment = Fragment_profile.newInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, fragment);
        ft.commit();
    }
}
