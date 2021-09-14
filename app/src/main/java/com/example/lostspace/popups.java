package com.example.lostspace;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class popups  {


    private int x,y;
    private Bitmap[] buttons = null;
    private Bitmap[] frame = null;
    private int show = 0;
    private int h = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int w = Resources.getSystem().getDisplayMetrics().widthPixels;



    public void draw(Canvas c){

      if(show == 1){
          c.drawBitmap(frame[0],w/2 - frame[0].getWidth()/2, h/2 - frame[0].getHeight()/2,null);
          c.drawBitmap(buttons[0],w/2 - frame[0].getWidth()/2 + frame[0].getWidth()/2 - buttons[0].getWidth()/2,
                  h/2 - frame[0].getHeight()/2 + buttons[0].getHeight() + 20 ,null);
          c.drawBitmap(buttons[1],w/2 - frame[0].getWidth()/2 + frame[0].getWidth()/2 - buttons[0].getWidth()/2,
                  h/2 - frame[0].getHeight()/2 + buttons[0].getHeight() * 2 + 20 * 2,null);
          c.drawBitmap(buttons[2],w/2 - frame[0].getWidth()/2 + frame[0].getWidth()/2 - buttons[0].getWidth()/2,
                  h/2 - frame[0].getHeight()/2 + buttons[0].getHeight() * 3 + 20 * 3,null);
          c.drawBitmap(buttons[3],w/2 - frame[0].getWidth()/2 + frame[0].getWidth()/2 - buttons[0].getWidth()/2,
                  h/2 - frame[0].getHeight()/2 + buttons[0].getHeight() * 4 + 20 * 4 ,null);


      }


    }

    public Rect getBounds(int index){
        switch (index){

            case 0 : return new Rect(w/2 - frame[0].getWidth()/2 + frame[0].getWidth()/2 - buttons[0].getWidth()/2,
                    h/2 - frame[0].getHeight()/2 + buttons[0].getHeight() + 20,
                    w/2 - frame[0].getWidth()/2 + frame[0].getWidth()/2 - buttons[0].getWidth()/2 + buttons[0].getWidth(),
                    h/2 - frame[0].getHeight()/2 + buttons[0].getHeight() + 20 + buttons [0].getHeight());

            case 1 : return new Rect(w/2 - frame[0].getWidth()/2 + frame[0].getWidth()/2 - buttons[0].getWidth()/2,
                    h/2 - frame[0].getHeight()/2 + buttons[0].getHeight() * 2 + 20 * 2,
                    w/2 - frame[0].getWidth()/2 + frame[0].getWidth()/2 - buttons[0].getWidth()/2 + buttons[0].getWidth(),
                    h/2 - frame[0].getHeight()/2 + buttons[0].getHeight() * 2 + 20 * 2 + buttons[0].getHeight());

            case 2 : return new Rect(w/2 - frame[0].getWidth()/2 + frame[0].getWidth()/2 - buttons[0].getWidth()/2,
                    h/2 - frame[0].getHeight()/2 + buttons[0].getHeight() * 3 + 20 * 3,
                    w/2 - frame[0].getWidth()/2 + frame[0].getWidth()/2 - buttons[0].getWidth()/2 + buttons[0].getWidth(),
                    h/2 - frame[0].getHeight()/2 + buttons[0].getHeight() * 3 + 20 * 3 + buttons[0].getHeight());

            case 3 : return new Rect(w/2 - frame[0].getWidth()/2 + frame[0].getWidth()/2 - buttons[0].getWidth()/2,
                    h/2 - frame[0].getHeight()/2 + buttons[0].getHeight() * 4 + 20 * 4,
                    w/2 - frame[0].getWidth()/2 + frame[0].getWidth()/2 - buttons[0].getWidth()/2 + buttons[0].getWidth(),
                    h/2 - frame[0].getHeight()/2 + buttons[0].getHeight() * 4 + 20 * 4 + buttons[0].getHeight());



        }

        return null;
    }

    public int getShow(){
        return show;
    }

    public void setShow(int show){
        this.show = show;
    }

    public void getFrames(Bitmap[] buttons, Bitmap [] frame ){

        this.buttons  = new Bitmap[4];
        this.frame = new Bitmap[1];

        for(int i = 0 ; i < buttons.length; i++)
            this.buttons[i] = Bitmap.createScaledBitmap(buttons[i],w * 35/100 * 70/100,h * 70/100 * 15/100,false);



        System.arraycopy(frame,0,this.frame,0,frame.length);

        this.frame[0] = Bitmap.createScaledBitmap(frame[0],w * 35/100,h * 75/100, false);



    }




    public int getWidth(){
        return  w;
    }


}
