package com.example.erlaljiyadav.mobitask.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.erlaljiyadav.mobitask.R;

/**
 * Created by ErLalji on 3/11/2018.
 */

public class Today_Task extends Fragment {
    public static Today_Task newInstance() {

        return new Today_Task();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_home, container, false);
    }
}
