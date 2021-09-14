package com.example.lostspace;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class usableButtons {

    private Bitmap[] buttons = new Bitmap[4] ;
    private int x,y;
    private int w = 250,h = 250;
    private int height = Resources.getSystem().getDisplayMetrics().heightPixels,
            width = Resources.getSystem().getDisplayMetrics().widthPixels;


    public void draw(Canvas c){

      c.drawBitmap(buttons[0],width - w - 10,height - h - 10,null);
      c.drawBitmap(buttons[3],width - buttons[3].getWidth() - 10,10,null);

  }

  public void getButtons(Bitmap[] buttons){
      System.arraycopy(buttons,0,this.buttons,0,buttons.length);
      this.buttons[0] = Bitmap.createScaledBitmap(buttons[0], w,h,false);
      this.buttons[3] = Bitmap.createScaledBitmap(buttons[3],75,75,false);
  }

  public Rect getBounds(int index){
        switch (index){
            case 0 : return new Rect(width - buttons[3].getWidth() - 10,10,
                    width - buttons[3].getWidth() - 10 + buttons[3].getWidth(),10 + buttons[3].getHeight());
        }


        return null;
  }

  public int getHeight(){
        return h;
  }

  public int getWidth(){
        return w;
  }

  public int getX(){
        return width - w - 10;
  }

  public int getY(){
        return height - h - 10;
  }

}
