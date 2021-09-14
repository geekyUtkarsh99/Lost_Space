package com.example.lostspace;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class healthPoints  {

    //refer
    private int x,y;
    private boolean vis = true;
      private fireRule f = fireRule.u;
    private int inY = 0;
    private Bitmap damager = null;
     private int iny,inx;
    private location loc = null;
    private int width,height;
    private int h = Resources.getSystem().getDisplayMetrics().heightPixels,
            w = Resources.getSystem().getDisplayMetrics().widthPixels;


    public healthPoints(int x,int y,location loc){
        this.x = x;
        this.y =y;
        this.loc = loc;
        if(loc == location.down)
            iny = y + 60;
        else if(loc == location.up)
            iny = y - 60;

    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public boolean getVis() {
        return vis;
    }


    public void setVis(boolean vis) {
this.vis = vis;
    }


    public void move(location loc) {
      if(this.loc == location.up && y > iny){
          y++;
          x-=2;
      }else if(this.loc == location.down && y < inY){
          y--;
          x-=2;
      }else if(x > -10 && y > -250)
       x-=2;
      else vis = false;

    }

    public boolean getNullfactor(){
        return damager == null;
    }

    public void draw(Canvas c) {
c.drawBitmap(damager,x,y,null);
    }

    public void getFrames(Bitmap point){
        this.damager = point;
        width = damager.getWidth();
        height = damager.getHeight();
    }

    public Rect getBounds(){
        return new Rect(x,y,damager.getWidth() + x,damager.getHeight() + y);
    }


    public Bitmap getImage() {
        return damager;
    }


     public int getHeight() {
        return height;
    }


    public int getWidth() {
        return width;
    }
}
