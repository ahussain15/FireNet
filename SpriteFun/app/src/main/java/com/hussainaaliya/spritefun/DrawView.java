package com.hussainaaliya.spritefun;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {

    Bitmap castle = BitmapFactory.decodeResource(getResources(), R.drawable.castle);
    Rect start = new Rect(0, 0, 1600, 2600);
    Paint a = new Paint();
    Bitmap bspr = BitmapFactory.decodeResource(getResources(), R.drawable.bird_sprite);
    Bitmap met = BitmapFactory.decodeResource(getResources(), R.drawable.cloud);
    Bitmap s = BitmapFactory.decodeResource(getResources(), R.drawable.scroll);
    Meteor[] meteor = new Meteor[5];
    Meteor scroll = new Meteor(200, 400, 150, 6, s);
    Bird bird = new Bird(((int)(getWidth()*0.5)), ((int)(getHeight()*0.5)),  bspr);
    int num = 0;
    double i = 0.0;
    double r = 0;
    double b = 0;

    public DrawView(Context context, @Nullable AttributeSet attributeSet){
        super(context, attributeSet);

    }

    protected void onDraw(Canvas canvas){
            if (meteor[0] == null)
                for (int k = 0; k < 5; k++)
                    meteor[k] = new Meteor(((int) (Math.random() * getHeight() * 0.9)), ((int) (Math.random() * getHeight() * 0.8)), 500, 1, met);
            a.setAlpha(200);
            a.setColor(Color.BLACK);
            a.setTextSize(100);
            start.set(0, 0, 1600 - (int) r, 2600 - (int) b);
            canvas.drawBitmap(castle, null, start, a);
            i += 5;
            r = 10 * Math.sin(Math.toRadians(i));
            b = 10 * Math.sin(Math.toRadians(i));
            scroll.draw(canvas);
            scroll.update(canvas);
            bird.draw(canvas);
            bird.update(canvas);
            if(scroll.offscreen(getWidth(), getHeight()))
                num = 0;
            canvas.drawText("Scrolls: "+String.valueOf(num), 400, 100, a);
            for (int k = 0; k < 5; k++) {
                meteor[k].draw(canvas);
                meteor[k].update(canvas);
            }
            invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int t_x = (int) event.getX();
        int t_y = (int) event.getY();
        bird.offsetTo(t_x, t_y);
        if(scroll.within(bird)) {
            int left = (int) (Math.random() * getWidth() * 0.9);
            int top = (int) (Math.random() * getHeight() * 0.9);
            scroll.offsetTo(left, top);
            num++;
        }
        return true;
    }

}
