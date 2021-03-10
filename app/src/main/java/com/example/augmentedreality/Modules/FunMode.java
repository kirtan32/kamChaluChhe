package com.example.augmentedreality.Modules;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.augmentedreality.R;

public class FunMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_mode);
        getSupportActionBar().hide();
    }
}
