package com.hussainaaliya.buttonfun;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button increaser;
    Button decreaser;
    Button ch_col;
    TextView display;
    EditText color;
    int presses = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        increaser = findViewById(R.id.increaser);
        decreaser = findViewById(R.id.decreaser);
        ch_col = findViewById(R.id.change);
        display = findViewById(R.id.greeting);
        color = findViewById(R.id.ent);
        color.setHint(R.string.enter_color);
        increaser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("+1");
                Log.i("inc", "plus plus");
                display.setText("Presses: "+(++presses));
            }
        });
    }

    public void dec(View view) {
        System.out.println("-1");
        Log.i("dec", "minus minus");
        display.setText("Presses: "+(--presses));
    }

    public void change_color(View view) {
        String fav_color = color.getText().toString().toLowerCase();
        if (fav_color.equals("red")) {
            increaser.setBackgroundColor(Color.RED);
            decreaser.setBackgroundColor(Color.RED);
            ch_col.setBackgroundColor(Color.RED);
            display.setTextColor(Color.RED);
            display.setText(R.string.right_color);
            color.setTextColor(Color.RED);
        }
        else{
            display.setText(R.string.wrong_color);
        }
    }
}