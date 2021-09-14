package com.example.lostspace;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class controller {

    private Bitmap[] buttons = new Bitmap[8];
    private int x = 10;
    private int width = 100;
    private int height = 100;
    private int w = Resources.getSystem().getDisplayMetrics().widthPixels,
            h = Resources.getSystem().getDisplayMetrics().heightPixels;

  public void setFrames(Bitmap[] frames){
     for(int i = 0;i < frames.length;i++) {
         buttons[i] = Bitmap.createScaledBitmap(frames[i],width,height,false);
     }

  }

  public void setSize(int width,int height){
      this.height = height;
      this.width = width;

  }


  public void draw(Canvas c){
       c.drawBitmap(buttons[4],x,h - height*3 - 50,null);
       c.drawBitmap(buttons[0],x + width,h - height*3 - 50,null);
       c.drawBitmap(buttons[5],x + width*2 ,h - height*3 - 50,null);
       c.drawBitmap(buttons[2],x,h - height*2 - 50,null);
       c.drawBitmap(buttons[3],x + width*2 ,h - height*2 - 50,null);
       c.drawBitmap(buttons[6],x,h - height - 50,null);
       c.drawBitmap(buttons[1],x + width,h - height - 50, null);
       c.drawBitmap(buttons[7],x + width*2,h - height - 50,null);

  }

  public int getWidth(){

      return width;
  }

  public int getHeight(){
      return height;
  }

  public int getX(int index){

      switch (index){
          case 0: return x;
          case 1: return x + width;
          case 2 : return x + width*2;

      }

      return 0;
  }

  public int getY(int index){
      switch (index){
          case 0:return h - height*3 - 50;
          case 1: return h - height*2 - 50;
          case 2: return h - height - 50;
      }

      return 0;
  }


}
