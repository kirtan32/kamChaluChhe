package com.example.augmentedreality.menu;

import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.augmentedreality.Modules.Doodler.Doodler;
import com.example.augmentedreality.Modules.FaceFilter;
import com.example.augmentedreality.Modules.FunMode.FunMode;
import com.example.augmentedreality.Modules.ObjectPlacer.ObjectPlacer;
import com.example.augmentedreality.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MenuFragment extends Fragment implements View.OnClickListener {

    FloatingActionButton menuBtn, doodlerBtn, objectPlacerBtn, faceFilterBtn, funModeBtn;
    Float translationY = 40f;
    Boolean isMenuOpen = false;
    View view;
    FragmentTransaction transaction;
    Doodler doodler;
    ObjectPlacer objectPlacer;
    FaceFilter faceFilter;
    FunMode funMode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //inflate layout in a view variable so can use attributes of view
        view =  inflater.inflate(R.layout.fragment_menu, container, false);
        //menu initialization
        initMenu();
        return view;
    }


    private void initMenu() {

        menuBtn = view.findViewById(R.id.menuButton);
        doodlerBtn = view.findViewById(R.id.doodler);
        objectPlacerBtn = view.findViewById(R.id.objectPlacer);
        faceFilterBtn = view.findViewById(R.id.faceFilter);
        funModeBtn = view.findViewById(R.id.funMode);


        //animation for menu icons
        doodlerBtn.setAlpha(0f);
        objectPlacerBtn.setAlpha(0f);
        faceFilterBtn.setAlpha(0f);
        funModeBtn.setAlpha(0f);

        doodlerBtn.setTranslationY(translationY);
        objectPlacerBtn.setTranslationY(translationY);
        faceFilterBtn.setTranslationY(translationY);
        funModeBtn.setTranslationY(translationY);


        menuBtn.setOnClickListener(this);
        doodlerBtn.setOnClickListener(this);
        objectPlacerBtn.setOnClickListener(this);
        faceFilterBtn.setOnClickListener(this);
        funModeBtn.setOnClickListener(this);
    }


    //open - close menu animations
    private void menuChange(){

        //to open menu with animations
        if(!isMenuOpen){
            doodlerBtn.animate().translationY(0f).alpha(1f).setDuration(200).start();
            objectPlacerBtn.animate().translationY(0f).alpha(1f).setDuration(200).start();
            faceFilterBtn.animate().translationY(0f).alpha(1f).setDuration(200).start();
            funModeBtn.animate().translationY(0f).alpha(1f).setDuration(200).start();
            menuBtn.setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(),R.drawable.close));
        }
        //to close menu with animations
        else{
            doodlerBtn.animate().translationY(translationY).alpha(0f).setDuration(200).start();
            objectPlacerBtn.animate().translationY(translationY).alpha(0f).setDuration(200).start();
            faceFilterBtn.animate().translationY(translationY).alpha(0f).setDuration(200).start();
            funModeBtn.animate().translationY(translationY).alpha(0f).setDuration(200).start();
            menuBtn.setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(),R.drawable.menu_icon));
        }


        isMenuOpen = !isMenuOpen;
    }



    @Override
    public void onClick(View v) {

        transaction  = getFragmentManager().beginTransaction();

        switch (v.getId()) {
            case R.id.menuButton:
                menuChange();
                break;
            case R.id.doodler:
//                if(doodler == null)
                    doodler = new Doodler();
                transaction.replace(R.id.host, doodler);
                transaction.commit();
                menuChange();
                break;

            case R.id.objectPlacer:
//                if(objectPlacer == null)
                    objectPlacer = new ObjectPlacer();
                transaction.replace(R.id.host, objectPlacer);
                transaction.commit();
                menuChange();
                break;
            case R.id.faceFilter:
//                if(faceFilter == null)
                    faceFilter = new FaceFilter();
                transaction.replace(R.id.host, faceFilter);
                transaction.commit();
                menuChange();
                break;
            case R.id.funMode:
//                if(funMode == null)
                    funMode = new FunMode();
                transaction.replace(R.id.host, funMode);
                transaction.commit();
                menuChange();
                break;
        }

    }
}
