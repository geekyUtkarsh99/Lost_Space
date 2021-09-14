package com.example.lostspace;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class missile implements entity{

  private int x,y;
  private Bitmap[] frame = new Bitmap[5];
  private int animate = 0;
  private boolean vis = true;
  private Bitmap refer = Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888);
  private int height = Resources.getSystem().getDisplayMetrics().heightPixels,
          width = Resources.getSystem().getDisplayMetrics().widthPixels;
  private int h,w;
 private boolean destroy = false;
  private Bitmap[] dester = new Bitmap[5];
  private int i = 1;
   private boolean doMove = true;

    public missile(int x, int y){
        this.x = x;
        this.y = y;

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public boolean getVis() {
        return vis;
    }

    @Override
    public void setVis(boolean vis) {
        this.vis = vis;
    }

    @Override
    public void move(location loc) {
        if(doMove)
        if(x < width) {
            x += 24;
            } else vis = false;

    }

    @Override
    public void render() {

        if(!destroy){
            setImage(frame[animate]);
        }else if(i <= 5){
            doMove = false;
            setImage(dester[animate]);
            i++;
        }else vis = false;


        if(animate < 4)
            animate++;
        else animate = 0;


    }

    @Override
    public boolean getNullFactor() {
        return frame[0] == null;
    }


    @Override
    public boolean randomizer() {
        return false;
    }

    @Override
    public void draw(Canvas c) {

        if(refer == null)
            refer = Bitmap.createBitmap(1,1, Bitmap.Config.ARGB_8888);

        c.drawBitmap(refer ,x,y,null );

    }

    @Override
    public void getFrames(Bitmap[] frame, Bitmap[] destroy, Bitmap damage) {

        System.arraycopy(frame,0,this.frame,0,frame.length);
        System.arraycopy(destroy,0,dester,0,destroy.length);

        h = this.frame[0].getHeight();
        w = this.frame[0].getWidth();

    }

    @Override
    public void setImage(Bitmap b) {
        this.refer = b;

    }

    @Override
    public void getDamagePoints(int damagePoints) {

    }



    @Override
    public Bitmap getImage() {
        return refer;
    }

    @Override
    public Rect getBounds() {
        return new Rect(x,y,x + refer.getWidth(),y + refer.getHeight());
    }

    @Override
    public void setDamage(boolean damage) {

    }

    @Override
    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    @Override
    public boolean getDestroy() {
        return destroy;
    }

    @Override
    public int getHeight() {
        return h;
    }

    @Override
    public int getWidth() {
        return w;
    }
}
