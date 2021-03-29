package com.example.augmentedreality;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import com.example.augmentedreality.Modules.Doodler.Doodler;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // setting initial Fragment - Doodler
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.host, new Doodler());
        transaction.commit();

    }
}
