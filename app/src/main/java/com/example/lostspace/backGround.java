package com.example.lostspace;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.print.PrintAttributes;

public class backGround {

    private Bitmap[] frame1 = new Bitmap[5];
    private Bitmap[] frame2 = new Bitmap[5];
    private int index  = -1;
    private int x = 0,y;
    private int inx,iny;
    private int width = Resources.getSystem().getDisplayMetrics().widthPixels,
            height = Resources.getSystem().getDisplayMetrics().heightPixels;

    public void draw(Canvas c){
            if(index == 0) {
                c.drawBitmap(frame1[0], x, 0, null);
                c.drawBitmap(frame1[1], frame1[0].getWidth() + x, 0, null);
                c.drawBitmap(frame1[2], frame1[1].getWidth() + x, 0, null);
                c.drawBitmap(frame1[3], frame1[2].getWidth() + x, 0, null);
            }else if(index == 1){
                c.drawBitmap(frame2[0],x,0,null);
                c.drawBitmap(frame2[1],x + frame2[0].getWidth(),0,null);
                c.drawBitmap(frame2[2],x + frame2[1].getWidth(),0,null);
                c.drawBitmap(frame2[3],x + frame2[2].getWidth(),0,null);
                c.drawBitmap(frame2[4],x + frame2[3].getWidth(),0,null);

            }
    }

    public void moveFunc(){
        if(index == 0){
       if(x + frame1[2].getWidth() > 50)
        x--;
       else x = 0;
        }else if(index == 1){
            if(x + frame2[3].getWidth() >0)
                x--;
            else
                x = 0;
        }
    }

    public void getFrames(Bitmap[] frame1,Bitmap[] frame2){
        for(int i = 0; i < frame1.length; i++)
            this.frame1[i] = Bitmap.createScaledBitmap(frame1[i],width,height,false);

        for(int i = 0; i < frame2.length; i++)
            this.frame2[i] = Bitmap.createScaledBitmap(frame2[i],width,height,false);


    }

    public void setIndex(int index){
        this.index = index;
    }

}
