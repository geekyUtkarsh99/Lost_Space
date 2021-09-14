package com.example.lostspace;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class speedRust implements  entity {

    //refer
    private int x,y;
    private boolean vis = true;
    private boolean destroy = false;
    private boolean doMove = false;
    private boolean damage = false;
    private fireRule f = fireRule.u;
    private int inY = 0;
    private int life  = 35;
    private Bitmap[] frame = new Bitmap[5];
    private Bitmap[] dester = new Bitmap[5];
    private Bitmap damager;
    private int animate = 0;
    private Bitmap refer = Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888);
    private int damagePoints = 0;
    private int i = 1;
    private int width,height;
    private int rotatingConst = 0;
    private int h = Resources.getSystem().getDisplayMetrics().heightPixels,
            w = Resources.getSystem().getDisplayMetrics().widthPixels;


    public speedRust(int x,int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public boolean getVis() {
        return false;
    }

    @Override
    public void setVis(boolean vis) {

    }

    @Override
    public void move(location loc) {

    }

    @Override
    public void render() {

    }

    @Override
    public boolean getNullFactor() {
        return false;
    }

    @Override
    public boolean randomizer() {
        return false;
    }

    @Override
    public void draw(Canvas c) {

    }

    @Override
    public void getFrames(Bitmap[] frame, Bitmap[] destroy, Bitmap damage) {

    }

    @Override
    public void setImage(Bitmap b) {

    }

    @Override
    public void getDamagePoints(int damagePoints) {

    }

    @Override
    public Bitmap getImage() {
        return null;
    }

    @Override
    public Rect getBounds() {
        return null;
    }

    @Override
    public void setDamage(boolean damage) {

    }

    @Override
    public void setDestroy(boolean destroy) {

    }

    @Override
    public boolean getDestroy() {
        return false;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }
}
