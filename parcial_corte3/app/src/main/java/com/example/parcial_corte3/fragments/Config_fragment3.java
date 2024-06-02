package com.example.parcial_corte3.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parcial_corte3.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Config_fragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Config_fragment3 extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment3_config, container, false);
    }
}