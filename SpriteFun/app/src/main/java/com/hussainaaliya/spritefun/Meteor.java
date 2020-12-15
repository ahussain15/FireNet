package com.hussainaaliya.spritefun;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class Meteor extends RectF {
    private Bitmap bitmap;
    private int dx, dy;

    public Meteor(int left, int top, int size, int speed, Bitmap bm){
        super(left, top, left+size, top+size);
        bitmap = bm;
        dx = speed;
        dy = 2*speed;
    }

    public void update(Canvas canvas){
        if(right<0||left>canvas.getWidth())
            offsetTo(0,(int)(Math.random()*500));
        if(top+dy>canvas.getHeight())
            offsetTo(left,-height());
        if(bottom+dy<0)
            offsetTo(left,canvas.getHeight());
        offset(dx,dy);
    }

    public void draw(Canvas canvas){
        Rect scr = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, scr, this, null);
    }

    public String getCoors(){
        return (new String(String.valueOf(left)) + " " + new String(String.valueOf(top)));
    }


    public boolean within(Bird b){
        if(right < b.getL() || b.getR() < left)
            return false;
        if(bottom < b.getT() || b.getB() < left)
            return false;
        return true;
    }

    public boolean offscreen(int w, int h){
        return (right > w || bottom > h);
    }
}
