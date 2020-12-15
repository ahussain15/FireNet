package com.hussainaaliya.funnyfragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class MainFragment extends Fragment {

    TextView response;
    EditText enter;
    Button press;
    View view;
    int clicks = 1;

    public MainFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment, container, false);
        String[] text = getResources().getStringArray(R.array.joke);
        response = view.findViewById(R.id.response);
        enter = view.findViewById(R.id.enter);
        press = view.findViewById(R.id.press);
        press.setText(text[0]);
        press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] text = getResources().getStringArray(R.array.joke);
                switch (clicks) {
                    case 1: press.setText(text[1]);
                        clicks++;
                        response.setText(text[2]);
                        break;
                    case 2: String who = enter.getText().toString();
                        String next = who + " " + text[3];
                        response.setText(next);
                        clicks++;
                        break;
                    case 3: response.setText(text[4]);
                            clicks = 1;
                }

            }
        });
        return view;
    }

}