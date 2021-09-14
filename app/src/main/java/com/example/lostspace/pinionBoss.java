package com.example.lostspace;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

public class pinionBoss implements entity {

    //refer
    private int x,y;
    private boolean vis = true;
    private boolean destroy = false;
    private boolean doMove = false;
    private boolean damage = false;
    private fireRule f = fireRule.u;
    private int inY = 0;
    private int life  = 150;
    private Bitmap[] frame = new Bitmap[5];
    private Bitmap[] dester = new Bitmap[5];
    private Bitmap damager;
    private int animate = 0;
    private Bitmap refer = Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888);
    private int damagePoints = 0;
    private int i = 1;
    private int width,height;
    private int h = Resources.getSystem().getDisplayMetrics().heightPixels,
            w = Resources.getSystem().getDisplayMetrics().widthPixels;




    public pinionBoss(int x,int y){
        this.x = x;
        this.y =y;
        this.inY = y;
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

        if(x > w/2 - 300){
            x-=2;
        }else {
            if(doMove){
               switch(f){
                   case u : y-=3;
                   if(y < 10)
                       f = fireRule.d;
                   break;
                   case d : y+=3;
                   if(y > h - 100)
                       f = fireRule.n;
                   break;
                   case n: y-=3;
                   if(y <= inY){
                       doMove =false;
                       f = fireRule.u;
                   }
                   break;

               }
            }
        }

    }

    @Override
    public void render() {
        if(!damage){
            setImage(frame[animate]);

        }else if(life > 0){
             setImage(frame[animate]);
            life-=damagePoints;

            damage = false;
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

    }

    public void getFrames(Bitmap[] frame, Bitmap[] destroy) {
        System.arraycopy(frame,0,this.frame,0,frame.length);
        System.arraycopy(destroy,0,this.dester,0,destroy.length);

        this.width = this.frame[0].getWidth();
        this.height = this.frame[0].getHeight();

    }

    @Override
    public void setImage(Bitmap b) {
this.refer = b;
    }

    public boolean getMove(){
        return doMove;
    }

    public void setMove(boolean doMove){
        this.doMove = doMove;
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
        return new Rect(x, y, x + refer.getWidth(), y + refer.getHeight());
    }

        @Override
    public void setDamage(boolean damage) {
this.damage = damage;
    }

    @Override
    public void setDestroy(boolean destroy) {
this.destroy = destroy;
    }

    @Override
    public boolean getDestroy() {
        return destroy;
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
