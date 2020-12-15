package com.hussainaaliya.funnyfragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button yes;
    Button no;
    TextView face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        face = findViewById(R.id.face);
    }

    public void no(View view) {
        face.setText(getString(R.string.sad));
    }

    public void yes(View view) {
        face.setText(getString(R.string.happy));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MainFragment frag = new MainFragment();
        ft.replace(R.id.placeholder, frag);
        ft.commit();
    }
}