package com.example.augmentedreality.Modules.FunMode;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Display;
import android.widget.Button;
import android.widget.TextView;

import com.example.augmentedreality.R;
import com.google.ar.sceneform.Camera;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;

import com.google.ar.sceneform.collision.Ray;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.MaterialFactory;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ShapeFactory;
import com.google.ar.sceneform.rendering.Texture;

import java.util.Random;

public class FunMode extends AppCompatActivity {


    private Scene scene;
    private Camera camera;
    private ModelRenderable bulletRenderable;
    private boolean timerFlag = true;
    private  int objectLeft = 15;
    private Point point;
    private TextView objectLeftTxt;
    private SoundPool soundPool;
    private int sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_mode);
        getSupportActionBar().hide();
        Display display = getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getRealSize(point);

        customFragment arFragment = (customFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);

        scene = arFragment.getArSceneView().getScene();
        camera = scene.getCamera();

        objectLeftTxt = findViewById(R.id.ufoCount);
        loadSoundPool();
        Button shoot = findViewById(R.id.btnShoot);
        shoot.setOnClickListener( view -> {
                if(timerFlag)
                {
                    startTimer();
                    timerFlag = false;
                }
                
                
                shoot();
        });

        addUFOToScene();
        buildBulletModel();
    }

    private void loadSoundPool() {

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(audioAttributes)
                .build();

        sound = soundPool.load(this,R.raw.assets_blop_sound,1);

    }

    private void shoot() {

        Ray ray = camera.screenPointToRay(point.x/2f, point.y/2f );
        Node node = new Node();
        node.setRenderable(bulletRenderable);
        scene.addChild(node);

        new Thread( () -> {
            for(int i =0 ; i < 200 ; i++){
                int finalI = i;
                runOnUiThread( () -> {
                    Vector3 vector3 = ray.getPoint( finalI * 0.1f);
                    node.setWorldPosition(vector3);

                    Node nodeInContact = scene.overlapTest(node);
                    if(node != null){
                        objectLeft--;
                        objectLeftTxt.setText("Balloons Left : "+objectLeft);
                        scene.removeChild(nodeInContact);

                        soundPool.play(sound, 1f,1f,1,0,1f);
                    }
                });
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            runOnUiThread( () -> scene.removeChild(node));
        }).start();
    }

    private void startTimer() {

        TextView timer = findViewById(R.id.timer);

        new Thread( () -> {
            int second = 0;

            while(objectLeft > 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                second++;
                int minutesPassed = second/60;
                int secondsPassed = second %60;

                runOnUiThread(
                        ()-> timer.setText(minutesPassed + ":" + secondsPassed));
            }
        }).start();

    }



    private void buildBulletModel() {
        Texture
                .builder()
                .setSource(this,R.drawable.texture)
                .build()
                .thenAccept(texture -> {
                    MaterialFactory
                            .makeOpaqueWithTexture(this,texture)
                            .thenAccept(material -> {
                              bulletRenderable = ShapeFactory
                                        .makeSphere(0.02f,
                                                new Vector3(0f,0f,0f),
                                                material);
                            });
                });
    }

    private void addUFOToScene() {

        ModelRenderable
                .builder()
                .setSource(this, Uri.parse("balloon.sfb"))
                .build()
                .thenAccept( renderable -> {
                    Node node = new Node();
                    node.setRenderable(renderable);
                    scene.addChild(node);

                    Random random = new Random();
                    int x = random.nextInt(13);
                    int y = random.nextInt(13);
                    int z = random.nextInt(30);

                    z = -z;

                    node.setWorldPosition(new Vector3((float)x, (float)y/10f,(float)z));

                });
    }

}
