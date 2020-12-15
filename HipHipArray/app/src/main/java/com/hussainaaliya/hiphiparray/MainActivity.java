package com.hussainaaliya.hiphiparray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button name_button;
    TextView name_display;
    EditText name_field;
    Button hello_button;
    TextView hello_display;
    Resources res;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name_button = findViewById(R.id.name_button);
        name_display = findViewById(R.id.name_display);
        name_field = findViewById(R.id.name_field);
        hello_button = findViewById(R.id.hello_button);
        hello_display = findViewById(R.id.hello_display);
        res = getResources();
        i = 0;
    }

    public void name_click(View view) {
        String name = name_field.getText().toString();
        String[] secret = res.getStringArray(R.array.name_color);
        String[] messages = res.getStringArray(R.array.color_texts);
        if (name.toLowerCase().equals(secret[0])){
            name_button.setBackgroundColor(Color.RED);
            name_display.setTextColor(Color.RED);
            name_field.setTextColor(Color.RED);
            hello_display.setTextColor(Color.RED);
            hello_button.setBackgroundColor(Color.RED);
            name_display.setText(messages[0]);
        }
        else{
            if (name.toLowerCase().equals(secret[1])){
                name_button.setBackgroundColor(Color.BLUE);
                name_display.setTextColor(Color.BLUE);
                name_field.setTextColor(Color.BLUE);
                hello_display.setTextColor(Color.BLUE);
                hello_button.setBackgroundColor(Color.BLUE);
                name_display.setText(messages[1]);
            }
            else{
                if (name.toLowerCase().equals(secret[2])){
                    name_button.setBackgroundColor(Color.GREEN);
                    name_display.setTextColor(Color.GREEN);
                    name_field.setTextColor(Color.GREEN);
                    hello_display.setTextColor(Color.GREEN);
                    hello_button.setBackgroundColor(Color.GREEN);
                    name_display.setText(messages[2]);
                }
                else{
                    name_display.setText(name);
                }
            }
        }
    }

    public void hello_click(View view) {
        String[] hellos = res.getStringArray(R.array.hellos);
        if (i < hellos.length){
            hello_display.setText(hellos[i]);
            i++;
        }
        else{
            i = 1;
            hello_display.setText(hellos[0]);
        }
    }
}