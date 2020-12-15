package com.hussainaaliya.lifecycleofanactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int onCreate_all = 0;
    int onCreate_cur = 0;
    int onStart_all = 0;
    int onStart_cur = 0;
    int onResume_all = 0;
    int onResume_cur = 0;
    int onPause_all = 0;
    int onPause_cur = 0;
    int onStop_all = 0;
    int onStop_cur = 0;
    int onRestart_all = 0;
    int onRestart_cur = 0;
    int onDestroy_all = 0;
    int onDestroy_cur = 0;

    TextView all;
    TextView cur;
    TextView onCreate_dall;
    TextView onCreate_dcur;
    TextView onStart_dall;
    TextView onStart_dcur;
    TextView onResume_dall;
    TextView onResume_dcur;
    TextView onPause_dall;
    TextView onPause_dcur;
    TextView onStop_dall;
    TextView onStop_dcur;
    TextView onRestart_dall;
    TextView onRestart_dcur;
    TextView onDestroy_dall;
    TextView onDestroy_dcur;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("Counts", Context.MODE_PRIVATE);

        all = findViewById(R.id.all);
        cur = findViewById(R.id.cur);

        onCreate_dall = findViewById(R.id.onCreate_all);
        onCreate_dcur = findViewById(R.id.onCreate_cur);
        onStart_dall = findViewById(R.id.onStart_all);
        onStart_dcur = findViewById(R.id.onStart_cur);
        onResume_dall = findViewById(R.id.onResume_all);
        onResume_dcur = findViewById(R.id.onResume_cur);
        onPause_dall = findViewById(R.id.onPause_all);
        onPause_dcur = findViewById(R.id.onPause_cur);
        onStop_dall = findViewById(R.id.onStop_all);
        onStop_dcur = findViewById(R.id.onStop_cur);
        onRestart_dall = findViewById(R.id.onRestart_all);
        onRestart_dcur = findViewById(R.id.onRestart_cur);
        onDestroy_dall = findViewById(R.id.onDestroy_all);
        onDestroy_dcur = findViewById(R.id.onDestroy_cur);

        onCreate_all = sharedPreferences.getInt("onCreate", 0);
        onStart_all = sharedPreferences.getInt("onStart", 0);
        onResume_all = sharedPreferences.getInt("onResume", 0);
        onPause_all = sharedPreferences.getInt("onPause", 0);
        onStop_all = sharedPreferences.getInt("onStop", 0);
        onRestart_all = sharedPreferences.getInt("onRestart", 0);
        onDestroy_all = sharedPreferences.getInt("onDestroy", 0);

        Editor editor = sharedPreferences.edit();
        onCreate_all++;
        onCreate_cur++;
        editor.putInt("onCreate", onCreate_all);
        editor.apply();

        onCreate_dall.setText(new String("onCreate() called "+onCreate_all+" time(s)"));
        onCreate_dcur.setText(new String("onCreate() called "+onCreate_cur+" time(s)"));
        onStart_dall.setText(new String("onStart() called "+onStart_all+" time(s)"));
        onStart_dcur.setText(new String("onStart() called "+onStart_cur+" time(s)"));
        onResume_dall.setText(new String("onResume() called "+onResume_all+" time(s)"));
        onResume_dcur.setText(new String("onResume() called "+onResume_cur+" time(s)"));
        onPause_dall.setText(new String("onPause() called "+onPause_all+" time(s)"));
        onPause_dcur.setText(new String("onPause() called "+onPause_cur+" time(s)"));
        onStop_dall.setText(new String("onStop() called "+onStop_all+" time(s)"));
        onStop_dcur.setText(new String("onStop() called "+onStop_cur+" time(s)"));
        onRestart_dall.setText(new String("onRestart() called "+onRestart_all+" time(s)"));
        onRestart_dcur.setText(new String("onRestart() called "+onRestart_cur+" time(s)"));
        onDestroy_dall.setText(new String("onDestroy() called "+onDestroy_all+" time(s)"));
        onDestroy_dcur.setText(new String("onDestroy() called "+onDestroy_cur+" time(s)"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        onStart_all++;
        onStart_cur++;
        Editor editor = sharedPreferences.edit();
        editor.putInt("onStart", onStart_all);
        editor.apply();
        onStart_dall.setText(new String("onStart() called "+onStart_all+" time(s)"));
        onStart_dcur.setText(new String("onStart() called "+onStart_cur+" time(s)"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        onResume_all++;
        onResume_cur++;
        Editor editor = sharedPreferences.edit();
        editor.putInt("onResume", onResume_all);
        editor.apply();
        onResume_dall.setText(new String("onResume() called "+onResume_all+" time(s)"));
        onResume_dcur.setText(new String("onResume() called "+onResume_cur+" time(s)"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause_all++;
        onPause_cur++;
        Editor editor = sharedPreferences.edit();
        editor.putInt("onPause", onPause_all);
        editor.apply();
        onPause_dall.setText(new String("onPause() called "+onPause_all+" time(s)"));
        onPause_dcur.setText(new String("onPause() called "+onPause_cur+" time(s)"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        onStop_all++;
        onStop_cur++;
        Editor editor = sharedPreferences.edit();
        editor.putInt("onStop", onStop_all);
        editor.apply();
        onStop_dall.setText(new String("onStop() called "+onStop_all+" time(s)"));
        onStop_dcur.setText(new String("onStop() called "+onStop_cur+" time(s)"));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        onRestart_all++;
        onRestart_cur++;
        Editor editor = sharedPreferences.edit();
        editor.putInt("onRestart", onRestart_all);
        editor.apply();
        onRestart_dall.setText(new String("onRestart() called "+onRestart_all+" time(s)"));
        onRestart_dcur.setText(new String("onRestart() called "+onRestart_cur+" time(s)"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroy_all++;
        onDestroy_cur++;
        Editor editor = sharedPreferences.edit();
        editor.putInt("onDestroy", onDestroy_all);
        editor.apply();
        onDestroy_dall.setText(new String("onDestroy() called "+onDestroy_all+" time(s)"));
        onDestroy_dcur.setText(new String("onDestroy() called "+onDestroy_cur+" time(s)"));
    }
}