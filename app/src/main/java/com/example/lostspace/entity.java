package com.example.lostspace;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public interface entity {

    public int getX();
    public int getY();
    public boolean getVis();
    public void setVis(boolean vis);
    public void move(location loc);
    public void render();
    public boolean getNullFactor();
    public boolean randomizer();
    public  void draw(Canvas c);
    public void getFrames(Bitmap[] frame,Bitmap[] destroy,Bitmap damage);
    public void setImage(Bitmap b);
    public void getDamagePoints(int damagePoints);
    public Bitmap getImage();
    public Rect getBounds();
    public void setDamage(boolean damage);
    public void setDestroy(boolean destroy);
    public boolean getDestroy();
    public int getHeight();
    public int getWidth();






}
