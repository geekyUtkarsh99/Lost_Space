package com.example.lostspace;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

import javax.xml.transform.Templates;

public class player {

    private int x = 10,y = 0;
    private int animate = 0;
    private boolean vis = true;
    private boolean damage = false;
    private int width,height;
    private Bitmap[] player = new Bitmap[5];
    private int h = Resources.getSystem().getDisplayMetrics().heightPixels,
    w = Resources.getSystem().getDisplayMetrics().widthPixels;
    private Bitmap refer = Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888);

    public player(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public  void draw(Canvas c){
        if(refer == null)
            refer = Bitmap.createBitmap(1,1, Bitmap.Config.ARGB_8888);

        if(player[0] == null)
            System.out.println("null pointed");

        c.drawBitmap(refer,x,y,null);
    }

    public void setImage(Bitmap refer){
        this.refer = refer;
    }

    public  Bitmap getImage(){
        return refer;
    }

    public void getFrames(Bitmap[] frame){

       System.arraycopy(frame,0,this.player,0,frame.length);
        width = player[0].getWidth();
       height = player[0].getHeight();
    }

    public void setVis(boolean vis){
        this.vis = vis;
    }

    public boolean getVis(){
       return vis;
    }

    public void move(location loc){

            if (loc == location.up && y > 0) {
                y -= 5;
            } else if (loc == location.down && y < h)
                y += 5;
            else if (loc == location.left && x > 0)
                x -= 5;
            else if (loc == location.right && x < w)
                x += 5;
            else if (loc == location.downl) {
                x -= 5;
                y += 5;
            } else if (loc == location.downr) {
                x += 5;
                y += 5;
            } else if (loc == location.upl) {
                y -= 5;
                x -= 5;
            } else if (loc == location.upr) {
                x += 5;
                y -= 5;
            }


    }

    public void render(){

        if(!damage){
            setImage(player[animate]);
        }

        if(animate < 4){
            animate++;
        }else animate = 0;

    }

    public Bitmap rotate(Bitmap b){
        Matrix m = new Matrix();
        m.postRotate(10);
        return Bitmap.createBitmap(b,0,0,b.getWidth(),b.getHeight(),m,false);
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Rect getBounds(){
        return new Rect(x,y,x + refer.getWidth(),y + refer.getHeight());
    }

}

