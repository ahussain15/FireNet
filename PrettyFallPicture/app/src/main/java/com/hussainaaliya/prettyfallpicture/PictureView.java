package com.hussainaaliya.prettyfallpicture;

import android.app.Notification;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PictureView extends View {

    Paint p = new Paint();

    Rect sky = new Rect(0, 0, 2000, 4000);
    Rect trunk = new Rect(0, 250, 250, 4000);
    Rect wall = new Rect(0, 1500, 2000, 4000);

    Rect brick1 = new Rect(0, 1500, 300, 1650);
    Rect brick2 = new Rect(325, 1500, 625, 1650);
    Rect brick3 = new Rect(650, 1500, 950, 1650);
    Rect brick4 = new Rect(975, 1500, 1275, 1650);
    Rect brick5 = new Rect(1300, 1500, 1600, 1650);

    Rect brick6 = new Rect(0, 1675, 150, 1825);
    Rect brick7 = new Rect(175, 1675, 475, 1825);
    Rect brick8 = new Rect(500, 1675, 800, 1825);
    Rect brick9 = new Rect(825, 1675, 1125, 1825);
    Rect brick10 = new Rect(1150, 1675, 1450, 1825);

    Rect brick11 = new Rect(0, 1850, 300, 2000);
    Rect brick12 = new Rect(325, 1850, 625, 2000);
    Rect brick13 = new Rect(650, 1850, 950, 2000);
    Rect brick14 = new Rect(975, 1850, 1275, 2000);
    Rect brick15 = new Rect(1300, 1850, 1600, 2000);

    Rect brick16 = new Rect(0, 2025, 150, 2175);
    Rect brick17 = new Rect(175, 2025, 475, 2175);
    Rect brick18 = new Rect(500, 2025, 800, 2175);
    Rect brick19 = new Rect(825, 2025, 1125, 2175);
    Rect brick20 = new Rect(1150, 2025, 1450, 2175);

    Rect stem = new Rect(975, 1150, 1025, 1202);

    public PictureView(Context context, @Nullable AttributeSet attributeSet){
        super(context, attributeSet);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        // Sky
        p.setColor(getResources().getColor(R.color.sky));
        p.setStyle(Paint.Style.FILL);
        canvas.drawRect(sky, p);
        //Tree
        p.setColor(getResources().getColor(R.color.bark));
        canvas.drawRect(trunk, p);
        // Wall
        p.setColor(Color.WHITE);
        canvas.drawRect(wall, p);
        // Brick Row 1
        p.setColor(getResources().getColor(R.color.brick));
        canvas.drawRect(brick1, p);
        canvas.drawRect(brick2, p);
        canvas.drawRect(brick3, p);
        canvas.drawRect(brick4, p);
        canvas.drawRect(brick5, p);
        // Brick Row 2
        canvas.drawRect(brick6, p);
        canvas.drawRect(brick7, p);
        canvas.drawRect(brick8, p);
        canvas.drawRect(brick9, p);
        canvas.drawRect(brick10, p);
        // Brick Row 3
        canvas.drawRect(brick11, p);
        canvas.drawRect(brick12, p);
        canvas.drawRect(brick13, p);
        canvas.drawRect(brick14, p);
        canvas.drawRect(brick15, p);
        // Brick Row 4
        canvas.drawRect(brick16, p);
        canvas.drawRect(brick17, p);
        canvas.drawRect(brick18, p);
        canvas.drawRect(brick19, p);
        canvas.drawRect(brick20, p);
        // Leaves
        for(int i = 0; i < 50; i++)
            drawLeaves(canvas, p);
        // Pumpkin
        p.setColor(getResources().getColor(R.color.orange));
        canvas.drawOval(800, 1200, 1200, 1500, p);
        p.setColor(getResources().getColor(R.color.stem));
        canvas.drawRect(stem, p);
        p.setColor(Color.BLACK);
        canvas.drawCircle(925, 1300, 20, p);
        canvas.drawCircle(1075, 1300, 20, p);
        canvas.drawOval(950, 1400, 1050, 1450, p);
    }

    private void drawLeaves(Canvas canvas, Paint p){
        double color = Math.random();
        if(color < (1/3.0))
            p.setColor(getResources().getColor(R.color.red));
        if(color >= (1/3.0) && color < (2.0/3))
            p.setColor(getResources().getColor(R.color.orange));
        if(color >= (2.0/3))
            p.setColor(getResources().getColor(R.color.yellow));
        canvas.drawCircle((int)(Math.random()*1000), (int)(Math.random()*700), 75, p);
        canvas.drawCircle((int)(Math.random()*900), (int)(Math.random()*700), 75, p);
        canvas.drawCircle((int)(Math.random()*1000), (int)(Math.random()*900), 75, p);
        canvas.drawCircle((int)(Math.random()*900), (int)(Math.random()*700), 75, p);
        canvas.drawCircle((int)(Math.random()*1000), (int)(Math.random()*900), 75, p);
    }
}
