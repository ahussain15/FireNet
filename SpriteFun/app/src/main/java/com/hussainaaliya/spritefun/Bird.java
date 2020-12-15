package com.hussainaaliya.spritefun;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.content.Context;

public class Bird extends RectF {
    private final int sm_cols = 3;
    private final int sm_rows = 3;
    private int icon_w;
    private int icon_h;
    private Bitmap bitmap;
    private int cur_frame = 0;
    private int sx, sy;
    private int delay = 20;

    public Bird(int left, int top, Bitmap bm){
        super(left, top, left+400, top+400);
        bitmap = bm;
        icon_w = bitmap.getWidth() / sm_cols;
        icon_h = bitmap.getHeight() / sm_rows;
    }

    public void update(Canvas canvas){
        if(right<0||left>canvas.getWidth())
            offsetTo(0,(int)(Math.random()*500));
        if(top>canvas.getHeight())
            offsetTo(left,-height());
        if(bottom<0)
            offsetTo(left,canvas.getHeight());
        if(delay--<0) {
            cur_frame = ++cur_frame % sm_cols;
            delay=20;
        }
    }

    public void draw(Canvas canvas){
        sx = cur_frame * icon_w;
        sy = getAnimationRow() * icon_h;
        Rect src = new Rect(sx, sy, icon_w+sx, icon_h+sy);
        canvas.drawBitmap(bitmap, src, this, null);
    }

    private int getAnimationRow() {
        if(cur_frame < 3)
            return 0;
        if(cur_frame < 6)
            return 1;
        return 2;
    }

    public int getT(){
        return (int)top;
    }

    public int getB(){
        return  (int)bottom;
    }

    public int getL(){
        return (int)left;
    }

    public int getR(){
        return (int)right;
    }

    public void setBitmap(Bitmap bm){
        bitmap = bm;
    }

}
