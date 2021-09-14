package com.example.lostspace;

import android.app.admin.SystemUpdatePolicy;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

public class frost1  {

    private int x,y,bullX,bullY;
    private Bitmap[] frame,bullet,bulletdam;
    private Bitmap refer,freeze;
    private int animate = 0;
    private int index = 1;
    private frost f;
    private ArrayList<frost> fr;


    public frost1(int index){
this.index = index;
    }

    public void setPositions(int x,int y){
        this.x = x;
        this.y =y;
        this.bullX = x;
        this.bullY = y;

    }

    public void draw(Canvas c){
         if(refer != null)
        c.drawBitmap(refer,x,y,null);
        renderMissile(c);

    }

    public void getFrames(Bitmap[] frame,Bitmap[] bulls,Bitmap[] damage,Bitmap freeze){
        this.frame = new Bitmap[5];
        this.bullet = new Bitmap[5];
        this.bulletdam = new Bitmap[5];
        System.arraycopy(frame,0,this.frame,0,frame.length);
        System.arraycopy(bulls,0,this.bullet,0,bulls.length);
        System.arraycopy(damage,0,this.bulletdam,0,damage.length);
        this.freeze = freeze;

    }

    private void renderMissile(Canvas c){
        if(fr != null){
            for(int i = 0; i < fr.size(); i++){
                f = fr.get(i);
                if(f.getNullFactor())
                    f.getFrames(bullet,bulletdam,freeze);
                f.draw(c);
                f.move(location.up);

                if(!f.getVis())
                    fr.remove(f);

            }
        }
    }

    public Bitmap getImage(int index){
        if(fr != null){
            if(index < fr.size()){
                f = fr.get(index);
                return f.getImage();
            }
        }
        return null;
    }

    public int getHeight(int index){
        if(fr != null){
            if(index < fr.size()){
                f = fr.get(index);
                return f.getHeight();
            }
        }
        return 0;
    }

    public int getWidth(int index){
        if(fr != null){
            if(index < fr.size()){
                f = fr.get(index);
                return f.getWidth();
            }
        }
        return 0;
    }

    public int getSize(){
        return fr != null ? fr.size() : 0;
    }

  public Rect getBounds(int id,int index){

       return ( fr != null ? fr.get(index).getBounds(id) : null);
  }


    public void fire(boolean fire){
        if(fire){
            if(fr == null){
                fr = new ArrayList<>();
            }
            if(index == 1){
            if(fr.size() <= 55){
                fr.add(new frost(bullX,bullY,fireRule.waveu));

            }
            }

        }
    }
    public int getDamagePoint(){
      return 3;
    }

    public void setDestroy(boolean destroy,int index){
        if(fr != null){
            if(index < fr.size()){
                f = fr.get(index);
                f.setDestroy(destroy);
            }
        }
    }

    private void render(){
        if(fr!= null){
            for(int i = 0; i < fr.size(); i++){
                f = fr.get(i);
                f.render();
            }
        }
    }

    public void render(boolean fire){
        render();
        if(frame[0] != null) {
            if (fire) {
                setImage(frame[animate]);
            } else setImage(frame[0]);
        }

        if(animate < 4){
            animate++;
        }else animate = 0;


    }

    public void setImage(Bitmap frame){
        this.refer = frame;
    }

private class frost implements entity{

        private int x,y,x1,y1;
    private Bitmap[] frame = new Bitmap[5];
    private int animate = 0;
    private boolean vis = true;
    private Bitmap refer = Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888);
    private Bitmap dust = null;
    private int height = Resources.getSystem().getDisplayMetrics().heightPixels,
            width = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int damagePoints = 3;
    private int h,w;
    private boolean destroy = false;
    private Bitmap[] dester = new Bitmap[5];
    private int i = 1;
    private boolean doMove = true;
    private fireRule f;
    private int a = 30,l = 15,c = 1;
    private dust d;
    private ArrayList<dust> du = null;


    private frost(int x,int y,fireRule f){
            this.x = x;
            this.y = y;
            this.x1 = x;
            this.y1 = y;
            this.f = f;
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
        if(doMove){
            if(x <= width){
     if(f == fireRule.waveu){
    y -= (int)Math.round(a * Math.sin(((2 * Math.PI)/l)*c ));
    x+=12;


         y1 += (int)Math.round(a * Math.sin(((2 * Math.PI)/l)*c ));
         x1+=12;
         c++;


            }
        }else vis =false;
        }
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
        if(refer != null){
            connect();
            drawFrame(c);

            c.drawBitmap(refer,x,y,null);
            c.drawBitmap(refer,x1,y1,null);
        }
    }

    private void drawFrame(Canvas c){
        if(du != null){
            for(int i = 0; i < du.size(); i++){
                d = du.get(i);
                d.draw(c,dust);
                d.move();
                if(!d.getvis())
                    du.remove(d);

            }
        }
    }

    private void connect(){
        if(du == null){
            du = new ArrayList<>();
        int i = 1;
            while(i <= 10){
                du.add(new dust(x - i,y,c - i,f));
                i++;
            }
        }

    }

    @Override
    public void getFrames(Bitmap[] frame, Bitmap[] destroy, Bitmap dust) {

        System.arraycopy(frame,0,this.frame,0,frame.length);
        System.arraycopy(destroy,0,dester,0,destroy.length);
        this.dust = dust;
        h = this.frame[0].getHeight();
        w = this.frame[0].getWidth();

    }


    @Override
    public void setImage(Bitmap b) {
        this.refer = b;

    }

    @Override
    public void getDamagePoints(int damagePoints) {

   this.damagePoints = damagePoints;
    }

    @Override
    public Bitmap getImage() {
        return refer;
    }

    @Override
    public Rect getBounds() {
        return null;
    }


    public Rect getBounds(int index) {
        switch (index){
            case 0 : return new Rect(x,y,x + refer.getWidth(),y + refer.getHeight());

            case 1 : return new Rect(x1,y1,x1 + refer.getWidth(), y1 + getHeight());
        }

    return null;

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

private class dust{
private int x,y;
private boolean vis= true;
private int c;
private fireRule f = null;
private int width = Resources.getSystem().getDisplayMetrics().widthPixels;

private dust(int x,int y,int  c,fireRule f){
    this.x = x;
    this.y = y;
            this.c = c;
            this.f = f;

}

        public void draw(Canvas c , Bitmap frame){
            c.drawBitmap(frame,x,y,null);
        }

        public void move(){
            if(x <= width ){
                if(f == fireRule.waveu){
                    y -= (int)Math.round(30 * Math.sin(((2 * Math.PI)/15)*c ));
                    x+=12;
                    c++;
                }else if(f == fireRule.waveD){
                    y += (int)Math.round(30 * Math.sin(((2 * Math.PI)/15)*c ));
                    x+=12;
                    c++;

                }
            }else vis = false;
        }

        public boolean getvis(){
    return vis;
        }


}


}

