package com.hussainaaliya.layoutexperiment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button push;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        push = findViewById(R.id.button);
    }

    public void push1(View view) {
        setContentView(R.layout.layout2);
        push = findViewById(R.id.Button2);
    }

    public void push2(View view) {
        setContentView(R.layout.layout3);
        push = findViewById(R.id.button3);
    }

    public void push3(View view) {
        setContentView(R.layout.layout4);
        push = findViewById(R.id.button4);
    }

    public void push4(View view) {
        setContentView(R.layout.layout5);
        push = findViewById(R.id.button5);
    }

    public void push5(View view) {
        setContentView(R.layout.layout6);
        push = findViewById(R.id.button6);
    }

    public void push6(View view) {
        setContentView(R.layout.layout7);
        push = findViewById(R.id.button7);
    }

    public void push7(View view) {
        setContentView(R.layout.layout);
        push = findViewById(R.id.button);
    }
}