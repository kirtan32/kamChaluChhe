package com.example.augmentedreality;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.augmentedreality.Modules.Doodler.Doodler;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LogMainActivity", ">>" + this);
        Log.d("LogMainActivity", ">>" + this);
        setContentView(R.layout.activity_main);
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.host, new Doodler());
        transaction.commit();

    }
}
