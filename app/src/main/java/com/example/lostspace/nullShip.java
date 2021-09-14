package com.example.lostspace;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.Random;

public class nullShip implements
        entity {

    //refer
    private int x,y;
    private boolean vis = true;
    private boolean destroy = false;
    private boolean damage = false;
    private int life  = 50;
    private Bitmap[] frame1 = new Bitmap[5];
    private Bitmap[] frame2 = new Bitmap[5];
    private Bitmap[] dester = new Bitmap[5];
    private Bitmap damager;
    private int animate = 0;
    private Bitmap refer = Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888);
    private int damagePoints = 0;
    private int i = 1;
    private int width,height;
    private int max_x,max_y;
    private location loc = null;
    private boolean doMove = true;

    public nullShip(int x,int y){
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

    }


    public void move() {
        if(doMove)
            if(max_x >200 && max_x < 1000 && max_y > 10 && max_y < 600 ){

                if(vis){


                    if(loc == location.up){

                        y-=4;
                        if(y <= max_y){
                            loc = null;
                            doMove =false;

                        }
                    }else if(loc == location.down){
                        y+=4;
                        if(y >= max_y){
                            loc = null;
                            doMove = false;


                        }
                    }else if(loc == location.left){
                        x-=4;
                        if(x <= max_x){
                            loc = null;
                            doMove = false;


                        }

                    }else if(loc == location.downl){
                        x-=4;
                        y-=4;
                        if(x <= max_x){
                            loc = null;
                            doMove = false;


                        }
                    }else if(loc == location.downr){
                        x+=4;
                        y+=4;
                        if(x >= max_x){
                            loc = null;
                            doMove = false;


                        }
                    }else if(loc == location.upl){
                        x-=4;
                        y-=4;
                        if(x<=max_x){
                            loc = null;
                            doMove = false;


                        }
                    }else if(loc == location.upr){
                        x+=4;
                        y-=4;
                        if(x >= max_x){
                            loc = null;
                            doMove = false;


                        }
                    }else if(loc == location.right){
                        x+=4;
                        if(x >= max_x){
                            loc = null;
                            doMove = false;


                        }
                    }else randomizer();
                }
            }else{
                max_x = 300;
                max_y = 300;
            }
    }

    public boolean getMovable(){
        return doMove;
    }

    public void setMovable(boolean doMove){
        this.doMove = doMove;
    }

    @Override
    public void render() {

        if(!damage){
            if(doMove)
                setImage(frame1[animate]);
            else setImage(frame2[animate]);

        }else if( life > 0){
            setImage(damager);
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
        return frame1[0] == null;
    }


    @Override
    public boolean randomizer() {
        Random r = new Random();
        int a = r.nextInt(8);
        switch(a){
            case 1 : loc = location.up;
                max_x = x;
                max_y = y - 150;
                break;

            case 2 : loc = location.down;
                max_x = x;
                max_y = y + 150;
                break;

            case 3 : loc = location.left;
                max_x = x - 150;
                max_y = y;
                break;

            case 4 : loc = location.right;
                max_x = x + 150;
                max_y = y;
                break;

            case 5 : loc = location.downl;
                max_x = x - 150;
                max_y = y + 150;
                break;

            case 6 : loc = location.downr;
                max_x = x + 150;
                max_y = y + 150;
                break;
            case 7 : loc = location.upl;
                max_x = x - 150;
                max_y = y - 150;
                break;

            case 8 : loc = location.upr;
                max_x = x + 150;
                max_y = y - 150;
                break;

        }
        return false;
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


    public void getFrames(Bitmap[] frame, Bitmap[] frame2,Bitmap[] destroy, Bitmap damage) {
        System.arraycopy(frame,0,this.frame1,0,frame.length);
        System.arraycopy(destroy,0,this.dester,0,destroy.length);
        System.arraycopy(frame2,0,this.frame2,0,frame2.length);
        this.damager = damage;

        this.width = this.frame1[0].getWidth();
        this.height = this.frame1[0].getHeight();

    }

    @Override
    public void setImage(Bitmap b) {
      this.refer = b;
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
        return new Rect(x,y,x + refer.getWidth(),y + refer.getHeight());

    }

    @Override
    public void setDamage(boolean damage) {
        this.damage = damage;
    }

    @Override
    public void setDestroy(boolean destroy) {

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
