package com.example.augmentedreality.Modules.Doodler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.augmentedreality.R;

import android.os.Bundle;
import android.view.View;

public class Doodler extends AppCompatActivity implements View.OnClickListener {

    Fragment menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doodler);
        getSupportActionBar().hide();

//        menuHandler();

    }

//    private void menuHandler() {
//        menu.getView().findViewById(R.id.doodler).setOnClickListener(this);
//        menu.getView().findViewById(R.id.objectPlacer).setOnClickListener(this);
//        menu.getView().findViewById(R.id.faceFilter).setOnClickListener(this);
//        menu.getView().findViewById(R.id.funMode).setOnClickListener(this);
//    }



    @Override
    public void onClick(View v) {
//
//        switch (v.getId()) {
//            case R.id.menuButton:
//                break;
//            case R.id.doodler:
//                break;
//            case R.id.objectPlacer:
//                if (true) {
//                    startActivity(new Intent(Doodler.this, ObjectPlacer.class));
//                }
//                break;
//            case R.id.faceFilter:
//                startActivity(new Intent(Doodler.this, FaceFilter.class));
//                break;
//            case R.id.funMode:
//                startActivity(new Intent(Doodler.this, FunMode.class));
//                break;
//        }

    }
}
