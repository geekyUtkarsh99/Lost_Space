package com.example.lostspace;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.Random;

public class enemy implements entity {

    //refer
    private int x,y;
    private boolean vis = true;
    private boolean destroy = false;
    private boolean damage = false;
    private int life  = 12;
    private Bitmap[] frame = new Bitmap[5];
    private Bitmap[] dester = new Bitmap[5];
    private Bitmap damager;
    private int animate = 0;
    private Bitmap refer = Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888);
    private int damagePoints = 0;
    private int i = 1;
    private int width,height;

    //construtor
    public enemy(int x,int y){
       this.x = x;
       this.y = y;

    }

   @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public boolean getVis() {
        return vis;
    }

    @Override
    public void setVis(boolean vis) {
this.vis = vis;
    }

    @Override
    public void move(location loc) {

        if(x > 0 && loc == location.left){
            x-=5;
        }else x = Resources.getSystem().getDisplayMetrics().widthPixels;

    }

    @Override
    public void render() {

        if(!damage){
            setImage(frame[animate]);

        }else if(life > 0){
            setImage(damager);
            life-=damagePoints;

            damage = false;
            damagePoints = 0;

            if(life < 0) {
                life = 0;

            }
        }else if(life == 0){
            life-=1;
            destroy = true;
        }else if(destroy && i <= 5){
            setImage(dester[animate]);
            i++;
        }else vis = false;


        if(animate < 4)
            animate++;
        else animate = 0;

    }

    @Override
    public boolean getNullFactor() {
        return frame[0] == null;
    }


    @Override
    public boolean randomizer() {
        Random r = new Random();
        int a = r.nextInt(5000);
        return a <= 10;

    }

    @Override
    public void draw(Canvas c) {

        if(refer == null)
            refer = Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888);

        c.drawBitmap(refer,x,y,null);



    }

    @Override
    public void getFrames(Bitmap[] frame, Bitmap[] destroy, Bitmap damage) {

        System.arraycopy(frame,0,this.frame,0,frame.length);
        System.arraycopy(destroy,0,this.dester,0,destroy.length);
        this.damager = damage;

        this.width = this.frame[0].getWidth();
        this.height = this.frame[0].getHeight();

    }


    public void setImage(Bitmap frame) {
      this.refer = frame;
    }

    @Override
    public void getDamagePoints(int damagePoints) {
        this.damagePoints = damagePoints;
    }


    @Override
    public Bitmap getImage() {
        return refer;
    }

    @Override
    public Rect getBounds() {
        return new Rect(x  + 10,y + 150,x + 10 + refer.getWidth() - 20,y + 125 + refer.getHeight() - 225);
    }

    @Override
    public void setDamage(boolean damage) {
    this.damage =damage;
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
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}
