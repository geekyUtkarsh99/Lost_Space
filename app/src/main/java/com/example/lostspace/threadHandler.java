package com.example.lostspace;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.SurfaceHolder;

public class threadHandler extends Thread {

    //tag
    private static final String TAG = "threadHandler.java";

    private final SurfaceHolder sf;
    private  GameView g;
    private boolean Run = false;
    private Canvas c;
    private int vertexSpeed = 0;

    public threadHandler(SurfaceHolder sf,GameView g){
       super();

        this.sf = sf;
        this.g = g;

    }

    public void setRunnable(boolean Run){
        this.Run = Run;
    }

    public void run(){
        while(Run){
            long bef,af,wait;
            bef = System.currentTimeMillis();
            try {
                c = sf.lockCanvas();

                synchronized (sf){

                    g.draw(c);
                    g.update();
                    }

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try{
                    sf.unlockCanvasAndPost(c);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            af = System.currentTimeMillis();
            wait = af - bef;
            if(wait > 5)
                wait = 0;

            try {
              //  Log.d(TAG, "run: " + wait);
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

}
