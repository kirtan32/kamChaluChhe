package com.example.augmentedreality.Modules.ObjectPlacer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.augmentedreality.R;
import com.example.augmentedreality.RecycleViewAdapter;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.ArrayList;

public class ObjectPlacer extends Fragment implements View.OnClickListener{

    private ArFragment arFragment;
    private RecyclerView recyclerView;
    private ArrayList<Integer> objectCycle;
    //    private Button btnC, btnChair, btn3;
    private String s = "coffee.sfb";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("getActivity", ">>" + getActivity());
        Log.d("getActivity", ">>" + getActivity());


            // Inflate the layout for this fragment
            View view  = inflater.inflate(R.layout.fragment_object_placer, container, false);

//            initData();
//         initRecyleView();
            //Fetchting the instance of arFragment ID in XML
            arFragment = (ArFragment) getFragmentManager().findFragmentById(R.id.arFragment);
            try {
                //OnTap Action Listener
                arFragment.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
                    //Crating an Anchor on location where user has tapped
                    Anchor anchor = hitResult.createAnchor();
                    ModelRenderable.builder()
                            .setSource(getActivity(), Uri.parse(s))
                            .build()
                            .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable));
                }));
            }

            catch(Exception e){

            }

            return view;

    }

//    private void initData() {
//        objectCycle = new ArrayList<>();
//
//        objectCycle.add(R.drawable.burger);
//        objectCycle.add(R.drawable.oldcar);
//        objectCycle.add(R.drawable.ufo);
//        objectCycle.add(R.drawable.coffee);
//        objectCycle.add(R.drawable.burger);
//        objectCycle.add(R.drawable.oldcar);
//        objectCycle.add(R.drawable.ufo);
//
//    }






//    private void initRecyleView() {
//
//        recyclerView = getActivity().findViewById(R.id.recyclerIcon);
//        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        RecycleViewAdapter adapter = new RecycleViewAdapter(objectCycle);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//
//    }

    //Method to Render Model On Real World
    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {

        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode transformableNode = new TransformableNode((arFragment.getTransformationSystem()));
//        transformableNode.setWorldScale(new Vector3(0.005f,0.005f,0.005f));
        transformableNode.setLocalScale(new Vector3(15f,15f,15f));
        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
    }

    @Override
    public void onClick(View v) {

//        switch (v.getId()){
//            case R.id.btnC:
//                s = "coffee.sfb";
//                break;
//            case R.id.btnChair:
//                s = "chairobj.sfb";
//                break;
//            case R.id.btn3:
//                s = "earth_ball.sfb";
//                break;
//        }

    }
}



//public class ObjectPlacer extends AppCompatActivity implements View.OnClickListener{
//
//    private ArFragment arFragment;
//    private RecyclerView recyclerView;
//    private ArrayList<Integer> objectCycle;
//    private LinearLayoutManager linearLayoutManager;
////    private Button btnC, btnChair, btn3;
//    private String s = "coffee.sfb";
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_object_placer);
//        getSupportActionBar().hide();
//
////        btnC = findViewById(R.id.btnC);
////        btnChair = findViewById(R.id.btnChair);
////        btn3 = findViewById(R.id.btn3);
////
////
////        btnC.setOnClickListener(this);
////        btnChair.setOnClickListener(this);
////        btn3.setOnClickListener(this);
//
//        initData();
//        initRecyleView();
//
//        //Fetchting the instance of arFragment ID in XML
//        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.arFragment);
//
//        //OnTap Action Listener
//        arFragment.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
//            //Crating an Anchor on location where user has tapped
//            Anchor anchor = hitResult.createAnchor();
//            ModelRenderable.builder()
//                    .setSource(this, Uri.parse(s))
//                    .build()
//                    .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable ));
//        }));
//
//    }
//
//    private void initData() {
//        objectCycle = new ArrayList<>();
//
//        objectCycle.add(R.drawable.burger);
//        objectCycle.add(R.drawable.oldcar);
//        objectCycle.add(R.drawable.ufo);
//        objectCycle.add(R.drawable.coffee);
//        objectCycle.add(R.drawable.burger);
//        objectCycle.add(R.drawable.oldcar);
//        objectCycle.add(R.drawable.ufo);
//
//    }
//
//    private void initRecyleView() {
//
//        recyclerView = findViewById(R.id.recyclerIcon);
//        linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        RecycleViewAdapter adapter = new RecycleViewAdapter(objectCycle);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//
//
//    }
//
//    //Method to Render Model On Real World
//        private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
//
//        AnchorNode anchorNode = new AnchorNode(anchor);
//        TransformableNode transformableNode = new TransformableNode((arFragment.getTransformationSystem()));
////        transformableNode.setWorldScale(new Vector3(0.005f,0.005f,0.005f));
//        transformableNode.setLocalScale(new Vector3(15f,15f,15f));
//        transformableNode.setParent(anchorNode);
//        transformableNode.setRenderable(modelRenderable);
//        arFragment.getArSceneView().getScene().addChild(anchorNode);
//        transformableNode.select();
//    }
//
//    @Override
//    public void onClick(View v) {
//
////        switch (v.getId()){
////            case R.id.btnC:
////                s = "coffee.sfb";
////                break;
////            case R.id.btnChair:
////                s = "chairobj.sfb";
////                break;
////            case R.id.btn3:
////                s = "earth_ball.sfb";
////                break;
////        }
//
//    }
//}
