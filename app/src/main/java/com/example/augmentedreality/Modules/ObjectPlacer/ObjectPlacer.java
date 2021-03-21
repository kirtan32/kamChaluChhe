package com.example.augmentedreality.Modules.ObjectPlacer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.example.augmentedreality.R;
import com.example.augmentedreality.RecycleViewAdapter;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.ArrayList;

public class ObjectPlacer extends AppCompatActivity implements View.OnClickListener{

    private ArFragment arFragment;
    private RecyclerView recyclerView;
    private ArrayList<Integer> objectCycle;
    private LinearLayoutManager linearLayoutManager;
    private String s = "coffee.sfb";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_placer);
        getSupportActionBar().hide();

        initData();
        initRecyleView();

        //Fetchting the instance of arFragment ID in XML
        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.arFragment);

        //OnTap Action Listener
        arFragment.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            //Crating an Anchor on location where user has tapped
            Anchor anchor = hitResult.createAnchor();
            ModelRenderable.builder()
                    .setSource(this, Uri.parse(s))
                    .build()
                    .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable ));
        }));

    }

    private void initData() {

        objectCycle = new ArrayList<>();
        objectCycle.add(R.drawable.burger);
        objectCycle.add(R.drawable.oldcar);
        objectCycle.add(R.drawable.ufo);
        objectCycle.add(R.drawable.coffee);
        objectCycle.add(R.drawable.burger);
        objectCycle.add(R.drawable.oldcar);
        objectCycle.add(R.drawable.ufo);

    }

    private void initRecyleView() {
        recyclerView = findViewById(R.id.recyclerIcon);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecycleViewAdapter adapter = new RecycleViewAdapter(objectCycle);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //Method to Render Model On Real World
        private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode transformableNode = new TransformableNode((arFragment.getTransformationSystem()));
        transformableNode.setLocalScale(new Vector3(15f,15f,15f));
        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
    }

    @Override
    public void onClick(View v) {

    }
}
