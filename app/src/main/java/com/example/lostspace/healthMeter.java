package com.example.lostspace;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.ArrayList;

public class healthMeter {

    private Bitmap healthGauge,point;
    private int h = Resources.getSystem().getDisplayMetrics().heightPixels,
            w = Resources.getSystem().getDisplayMetrics().widthPixels;

    private int x = 0,y = 0;
    private int c = Color.TRANSPARENT;
     private healthPoints hel;
    private ArrayList<healthPoints> he = new ArrayList<>();


    public void draw(Canvas c){
        healthAdder();
        loadLife(c);
        c.drawBitmap(healthGauge,x,y,null);


    }

    public void getFrames(Bitmap frame,Bitmap point){
        this.healthGauge = frame;
  this.point = point;
    }

    public void loadLife(Canvas c){

        int x = 20;

        for(int i = 0; i < he.size();i++){
            hel = he.get(i);
            hel.getFrames(point);
            hel.draw(c);
            hel.setX(x);
            x++;
    }

        //update bar
        if(he.size() <= 90 && he.size() >= 45)
            this.c = Color.YELLOW;
        else if(he.size() <= 45)
            this.c = Color.RED;



    }

    public void healthAdder(int increaseFactor){
        int i = 0;
        while(i <= increaseFactor){
            he.add(new healthPoints());
            i++;
        }
    }

    public void healthAdder(){

        if(he.size() == 0) {
            int i = 0;
            while(i <= 345) {
                he.add(new healthPoints());
            i++;

            }

        }

    }

    public void decrease(int decreaseFactor){
        int i = 0;
        while(i <= decreaseFactor){
            int lastindex = he.size() - 1;
            he.remove(lastindex);
            i++;

        }

    }


    private class healthPoints{

        private int x = 20,y = 63;
        private Bitmap frame;
        private int c = Color.TRANSPARENT;

        public void draw(Canvas c){

          c.drawBitmap(frame,x,y,null);

        }

        public void getFrames(Bitmap frame){
            this.frame = frame;
        }

        public void setColor(int c){
            this.c = c;
        }

        public void setX(int x){
            this.x = x;
        }

    }
}
