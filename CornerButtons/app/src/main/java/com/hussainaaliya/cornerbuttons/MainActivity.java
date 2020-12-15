package com.hussainaaliya.cornerbuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {
    TextView tr;
    TextView tl;
    TextView br;
    TextView bl;
    TextView tban;
    TextView bban;
    Button ban;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    int tr_c = 0;
    int tl_c = 0;
    int br_c = 0;
    int bl_c = 0;
    int text = 10;
    int rating = 0;
    SeekBar skb;
    RatingBar rban;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tr = findViewById(R.id.tr);
        tl = findViewById(R.id.tl);
        br = findViewById(R.id.br);
        bl = findViewById(R.id.bl);
        skb = findViewById(R.id.skb);
        tban = findViewById(R.id.stat);
        bban = findViewById(R.id.dir);
        ban = findViewById(R.id.banana);
        rban = findViewById(R.id.rate);
        sharedPreferences = getSharedPreferences("Pref", Context.MODE_PRIVATE);
        tr_c = sharedPreferences.getInt("tr", 0);
        tl_c = sharedPreferences.getInt("tl", 0);
        br_c = sharedPreferences.getInt("br", 0);
        bl_c = sharedPreferences.getInt("bl", 0);
        text = sharedPreferences.getInt("text", 12);
        rating = sharedPreferences.getInt("rating", 0);
        tr.setText(String.valueOf(sharedPreferences.getInt("tr", 0)));
        tl.setText(String.valueOf(sharedPreferences.getInt("tl", 0)));
        br.setText(String.valueOf(sharedPreferences.getInt("br", 0)));
        bl.setText(String.valueOf(sharedPreferences.getInt("bl", 0)));
        tr.setTextSize(text);
        tl.setTextSize(text);
        br.setTextSize(text);
        bl.setTextSize(text);
        tban.setTextSize(text);
        bban.setTextSize(text);
        ban.setTextSize(text);
        skb.setProgress(text);
        rban.setRating(rating);
        rban.setNumStars(5);
        skb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                text = i;
                tr.setTextSize(text);
                tl.setTextSize(text);
                br.setTextSize(text);
                bl.setTextSize(text);
                tban.setTextSize(text);
                bban.setTextSize(text);
                ban.setTextSize(text);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor = sharedPreferences.edit();
                editor.putInt("text", text);
                editor.apply();
            }
        });
        rban.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if (tban.getText().toString().equals("No Banana!")){
                    bban.setText(R.string.w_ban);
                }
                else {
                    rating = (int) ratingBar.getRating();
                    editor = sharedPreferences.edit();
                    editor.putInt("rating", rating);
                    editor.apply();
                }
            }
        });
    }

    public void click(View view) {
        int id = view.getId();
        editor = sharedPreferences.edit();
        if (id == R.id.tr){
            tr_c++;
            editor.putInt("tr", tr_c);
            editor.apply();
            tr.setText(String.valueOf(sharedPreferences.getInt("tr", 0)));
            makeText(getApplicationContext(), "Top right button clicked " + tr_c + " time(s)!", LENGTH_SHORT).show();
        }
        if (id == R.id.tl){
            tl_c++;
            editor.putInt("tl", tl_c);
            editor.apply();
            tl.setText(String.valueOf(sharedPreferences.getInt("tl", 0)));
            makeText(getApplicationContext(), "Top left button clicked " + tl_c + " time(s)!", LENGTH_SHORT).show();
        }
        if (id == R.id.br){
            br_c++;
            editor.putInt("br", br_c);
            editor.apply();
            br.setText(String.valueOf(sharedPreferences.getInt("br", 0)));
            makeText(getApplicationContext(), "Bottom right button clicked " + br_c + " time(s)!", LENGTH_SHORT).show();
        }
        if (id == R.id.bl){
            bl_c++;
            editor.putInt("bl", bl_c);
            editor.apply();
            bl.setText(String.valueOf(sharedPreferences.getInt("bl", 0)));
            makeText(getApplicationContext(), "Bottom left button clicked " + bl_c + " time(s)!", LENGTH_SHORT).show();
        }
    }

    public void push(View view) {
        tban.setText(R.string.g_ban);
        bban.setText(R.string.r_ban);
    }
}