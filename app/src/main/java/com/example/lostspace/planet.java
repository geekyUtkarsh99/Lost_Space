package com.example.lostspace;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class planet {

    private Bitmap[] planet1 = new Bitmap[7];
    private int planY = 100;
    private int animate = 0;
    private int width = Resources.getSystem().getDisplayMetrics().widthPixels,
            height = Resources.getSystem().getDisplayMetrics().heightPixels;



    public void move(){

    }

    public void setBitmap(Bitmap[] planet1){

        System.arraycopy(planet1, 0, this.planet1, 0, planet1.length);

    }

   public void draw(Canvas c){

        c.drawBitmap(planet1[animate],width/2 - planet1[0].getWidth()/2,planY,null);


   }

   public void render(){

   }

   public int getX(int index){
        switch (index){
            case 0: return width/2-planet1[0].getWidth()/2;

        }

        return 0;
   }

   public int getY(int index){
        switch (index){
            case 0: return planY;
        }

        return 0;
   }

   public int getWidth(int index){
        switch (index){
            case 0: return  width;
        }
        return 0;
   }

   public int getHeight(int index){
        switch (index){
            case 0: return height;
        }

        return 0;
   }

}
