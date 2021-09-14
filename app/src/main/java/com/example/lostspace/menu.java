package com.example.lostspace;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.media.Image;
import android.util.DisplayMetrics;
import android.widget.ImageView;

public class menu {

    private boolean vis = false;
    private Bitmap menuImage;
    private Bitmap[] buttons = new Bitmap[5];
    private Bitmap[] glow = new Bitmap[5];
    private Bitmap play = Bitmap.createBitmap(1,1, Bitmap.Config.ARGB_8888);
    private Bitmap exit = Bitmap.createBitmap(1,1, Bitmap.Config.ARGB_8888);
    private double width;
    private double height;
    private int y1 = 500, y2 = 700;
    private int inY1 = 1300,inY2 = 1500;


    public Bitmap getImage() {
        return menuImage;
    }

    public void getDimensions(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public void setBitmap(Bitmap menuImage, Bitmap[] buttons,Bitmap[] glow) {

        this.menuImage = Bitmap.createScaledBitmap(menuImage, (int) width, (int) height, false);

        System.arraycopy(buttons, 0, this.buttons, 0, buttons.length);
        System.arraycopy(glow,0,this.glow,0,glow.length);

        this.play = this.buttons[0];
        this.exit = this.buttons[1];




    }

    public void draw(Canvas c) {


        c.drawBitmap(menuImage, 0, 0, null);
        c.drawBitmap(play, (int) width / 2 - play.getWidth() / 2, inY1, null);
        c.drawBitmap(exit, (int) width / 2 - exit.getWidth() / 2, inY2, null);




    }

    public void move(boolean move) {
        if (move) {
         if(inY1 > y1)
             inY1-=25;
         else inY1 = y1;

         if(inY2 > y2)
             inY2-=22;
         else inY2 = y2;

        }

    }

    public void initiate(){
        inY1 = 1300;
        inY2 = 1500;
        play = buttons[0];
        exit = buttons[1];
    }


    public void updateImage(int u){

        switch (u){
            case 0 : play = glow[0];
            break;
            case  1: exit = glow[1];
            break;
            default: play = buttons[0];
                     exit = buttons[1];
        }

    }

   public int getWidth(int u){
        if(exit != null)
       switch (u){
           case 0: return exit.getWidth();

           case 1: return  play.getWidth();
       }

        return 0;
   }

   public int getheight(int u){
        if(exit != null)
       switch (u){
           case 0: return exit.getHeight();

           case 1: return  play.getHeight();
       }

        return 0;
   }

   public int getX(int u){
        if(exit != null)
       switch (u){
           case 0: return (int)width/2 - exit.getWidth()/2;

           case 1 : return (int)width/2 - play.getWidth()/2;
       }

        return 0;
   }

   public  int getY(int u){
        if(exit != null)
        switch (u){
            case 0 : return y2;
            case 1: return  y1;
        }
        return 0;
   }

}