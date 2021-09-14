package com.example.lostspace;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class levelScreen {

    private int l1X = 10,l1Y = 20;
    private Bitmap[] lvls = new Bitmap[30];
    private int height = 400,width = 400;

    public void getFrames(Bitmap[] frame){

       System.arraycopy(frame,0,lvls,0,frame.length);
       height = lvls[0].getHeight();
       width = lvls[0].getWidth();

    }

    public void draw(Canvas c , planets planets){
         if(planets == com.example.lostspace.planets.planet1) {
             c.drawBitmap(lvls[0], l1X, l1Y, null);
             c.drawBitmap(lvls[1], l1X + width, l1Y, null);
             c.drawBitmap(lvls[2], l1X + width * 2, l1Y, null);
             c.drawBitmap(lvls[3], l1X + width * 3, l1Y, null);
             c.drawBitmap(lvls[4], l1X + width * 4, l1Y, null);

         }
    }

    public int getX(int index,planets planets){
       if(planets == com.example.lostspace.planets.planet1) {
           switch (index) {
               case 1:
                   return l1X;

               case 2:
                   return l1X + width;

               case 3:
                   return l1X + width * 2;

               case 4:
                   return l1X + width * 3;

               case 5:
                   return l1X + width * 4;


           }
       }

        return 0;
    }

    public int getY(int index,planets planets){

        return l1Y;
    }

    public int getWidth(int index,planets planets){
        return width;
    }

    public int getHeight(int index,planets planets){
        return height;
    }
}
