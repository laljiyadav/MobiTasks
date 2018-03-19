package com.example.erlaljiyadav.mobitask.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.erlaljiyadav.mobitask.R;

/**
 * Created by Er Lalji Yadav on 27-01-2018.
 */

public class Fragment_profile extends Fragment {
    public static Fragment_profile newInstance() {

        return new Fragment_profile();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }
}
