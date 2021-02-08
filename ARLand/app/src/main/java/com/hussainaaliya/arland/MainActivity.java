package com.hussainaaliya.arland;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArFragment frag;
    Spinner spinner;

    ArrayList<String> options = new ArrayList<>(Arrays.asList("grammophone", "flowerpot", "pikachu", "telescope", "pizza"));
    ArrayList<String> paths = new ArrayList<>(Arrays.asList("grammophone/model.gltf", "flowerpot/flowerpot.gltf", "pikachu/model.gltf", "telescope/model.gltf", "pizza/model.gltf"));

    String cur_option = "";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!checkSupport(this))
            return;
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, options);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        frag = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        frag.setOnTapArPlaneListener(
                (HitResult hitresult, Plane plane, MotionEvent motionevent) -> {
                    if (plane.getType() != Plane.Type.HORIZONTAL_UPWARD_FACING)
                        return;
                    Anchor anchor = hitresult.createAnchor();
                    placeObject(frag, anchor, Uri.parse(cur_option));
                }
        );
    }

    private void placeObject(ArFragment fragment, Anchor anchor, Uri uri) {
        ModelRenderable.builder()
                .setSource(fragment.getContext(), uri)
                .setIsFilamentGltf(true)
                .build()
                .thenAccept(modelRenderable -> addNodeToScene(fragment, anchor, modelRenderable))
                .exceptionally(throwable -> {
                            Toast.makeText(fragment.getContext(), "Something is not right:" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                            return null;
                        }

                );

    }

    private void addNodeToScene(ArFragment fragment, Anchor anchor, Renderable renderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode node = new TransformableNode(fragment.getTransformationSystem());
        node.setRenderable(renderable);
        node.setParent(anchorNode);
        fragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();
    }

    public static boolean checkSupport(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            String openGlVersion = ((ActivityManager) Objects.requireNonNull(activity.getSystemService(Context.ACTIVITY_SERVICE))).getDeviceConfigurationInfo().getGlEsVersion();

            if (Double.parseDouble(openGlVersion) >= 3.0) {
                return true;
            } else {
                Toast.makeText(activity, "OpenGl Version Not Compatible", Toast.LENGTH_SHORT).show();
                activity.finish();
                return false;
            }
        } else {
            Toast.makeText(activity, "Build Version Not Compatible", Toast.LENGTH_SHORT).show();
            activity.finish();
            return false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        cur_option = paths.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        cur_option = paths.get(0);
    }
}