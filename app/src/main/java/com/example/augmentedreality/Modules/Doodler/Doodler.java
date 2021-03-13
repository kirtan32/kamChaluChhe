package com.example.augmentedreality.Modules.Doodler;

import androidx.fragment.app.Fragment;


import com.example.augmentedreality.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Doodler extends Fragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_doodler, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
