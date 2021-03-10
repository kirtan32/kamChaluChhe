package com.example.augmentedreality.Modules.ObjectPlacer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.augmentedreality.R;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.ArrayList;

public class ObjectPlacer extends AppCompatActivity implements View.OnClickListener{

    private ArFragment arFragment;
//    private RecyclerView recyclerView;
//    private ArrayList<String> viewCycle;
    private Button btnC, btnChair, btn3;
    private String s = "coffee.sfb";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_placer);
        getSupportActionBar().hide();

        btnC = findViewById(R.id.btnC);
        btnChair = findViewById(R.id.btnChair);
        btn3 = findViewById(R.id.btn3);


        btnC.setOnClickListener(this);
        btnChair.setOnClickListener(this);
        btn3.setOnClickListener(this);



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


        //Method to Render Model On Real World


    }


        private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {

        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode transformableNode = new TransformableNode((arFragment.getTransformationSystem()));
        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnC:
                s = "coffee.sfb";
                break;
            case R.id.btnChair:
                s = "chairobj.sfb";
                break;
            case R.id.btn3:
                s = "earth_ball.sfb";
                break;
        }

    }
}
