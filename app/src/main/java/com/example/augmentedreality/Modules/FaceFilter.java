package com.example.augmentedreality.Modules;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.augmentedreality.R;

public class FaceFilter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_filter);
        getSupportActionBar().hide();
    }
}
