package com.hussainaaliya.stringmaze;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static android.content.SharedPreferences.Editor;

public class MainActivity extends AppCompatActivity {

    TextView dir;
    TextView maze;
    EditText width;
    EditText height;
    Button gen;
    Button top;
    Button bottom;
    Button right;
    Button left;
    Maze start;
    String start_str;
    Gson gson;
    SharedPreferences sharedPreferences;
    Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dir = findViewById(R.id.directions);
        maze = findViewById(R.id.maze);
        width = findViewById(R.id.width);
        height = findViewById(R.id.height);
        gen = findViewById(R.id.generate);
        top = findViewById(R.id.top);
        bottom = findViewById(R.id.bottom);
        right = findViewById(R.id.right);
        left = findViewById(R.id.left);

        sharedPreferences = getSharedPreferences("Maze", MODE_PRIVATE);
        gson = new GsonBuilder().create();

        start_str = sharedPreferences.getString("maze","");
        if(!start_str.equals("")) {
            start = gson.fromJson(start_str, Maze.class);
            maze.setText(start.getMazeString());
        }

        dir.setText(sharedPreferences.getString("dir", "Please enter dimensions (3 to 20 for width and 3 to 10 for height) for the maze."));

    }

    public void newMaze(View view) {
        try {
            int rows = Integer.parseInt(String.valueOf(height.getText()));
            int cols = Integer.parseInt(String.valueOf(width.getText()));
            if(rows < 3 || rows > 10 || cols < 3 || cols > 20) {
                maze.setText(R.string.invalid);
                dir.setText(R.string.directions);
                editor = sharedPreferences.edit();
                editor.putString("dir", "Please enter dimensions (3 to 20 for width and 3 to 10 for height) for the maze.");
                editor.apply();
            }
            else{
                start = new Maze(rows, cols);
                start.generate();
                start.setMazeMatrix();
                start.startMaze();
                dir.setText(R.string.directions2);
                maze.setText(start.getMazeString());
                editor = sharedPreferences.edit();
                editor.putString("maze", gson.toJson(start));
                editor.putString("dir", "Start at the percent sign. Navigate to the dollar sign.");
            }
            editor.apply();
        }
        catch (NumberFormatException e) {
            maze.setText(R.string.exception);
            dir.setText(R.string.directions);
            editor = sharedPreferences.edit();
            editor.putString("dir", "Please enter dimensions (3 to 20 for width and 3 to 10 for height) for the maze. ");
        }

    }

    public void up(View view) {
        if(!start.isOver()) {
            start.moveUp();
            maze.setText(start.getMazeString());
            editor = sharedPreferences.edit();
            editor.putString("maze", gson.toJson(start));
            editor.apply();
        }
        if(start.isOver()){
            dir.setText(R.string.congrats);
            editor.putString("dir", "Congratulations! You solved the maze! Start a new maze if you would like.");
        }
    }

    public void left(View view) {
        if(!start.isOver()) {
            start.moveLeft();
            maze.setText(start.getMazeString());
            editor = sharedPreferences.edit();
            editor.putString("maze", gson.toJson(start));
            editor.apply();
        }
        if(start.isOver()){
            dir.setText(R.string.congrats);
            editor.putString("dir", "Congratulations! You solved the maze! Start a new maze if you would like.");
        }
    }

    public void right(View view) {
        if(!start.isOver()) {
            start.moveRight();
            maze.setText(start.getMazeString());
            editor = sharedPreferences.edit();
            editor.putString("maze", gson.toJson(start));
            editor.apply();
        }
        if(start.isOver()){
            dir.setText(R.string.congrats);
            editor.putString("dir", "Congratulations! You solved the maze! Start a new maze if you would like.");
        }
    }

    public void down(View view) {
        if(!start.isOver()) {
            start.moveDown();
            maze.setText(start.getMazeString());
            editor = sharedPreferences.edit();
            editor.putString("maze", gson.toJson(start));
            editor.apply();
        }
        if(start.isOver()){
            dir.setText(R.string.congrats);
            editor.putString("dir", "Congratulations! You solved the maze! Start a new maze if you would like.");
        }
    }
}