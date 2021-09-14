package com.example.lostspace;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.awt.font.TextAttribute;

public class upgradeRoom {

    private Bitmap back = null;
    private Bitmap[] buttons = null;
    private Bitmap[] selected = null;
    private Bitmap[] backPanel = null;
    private Bitmap selectionPallet = null;
    private Bitmap sButton = null;
    private Bitmap uButton = null;
    private Bitmap backButton = null;
    private Bitmap selectiontag = null;
    private Bitmap b1,b2;
    private Bitmap next = null;
    private int selectedMissile = 0;
    private int floor = -1,board;
    private int x = 0,y = 0;
    private int tier = -1;
    private int h = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int w = Resources.getSystem().getDisplayMetrics().widthPixels;

    public upgradeRoom(int selectedMissile){
        this.selectedMissile = selectedMissile;
    }

    public void draw(Canvas c){

        if(back != null) {
            c.drawBitmap(back, x, y, null);
            c.drawBitmap(backButton,x + 10,y+ 10,null);
            c.drawBitmap(next,w - next.getWidth() - 20,h - next.getHeight()- 20,null);

            if(buttons != null) {
                c.drawBitmap(b1, 10, back.getHeight() - buttons[0].getHeight() - 10, null);
                c.drawBitmap(b2, buttons[0].getWidth() + 10, back.getHeight() - buttons[1].getHeight() - 10, null);
            }
           if(floor == 0){
               c.drawBitmap(backPanel[0],w/2 - backPanel[0].getWidth()/2,h/2 - backPanel[0].getHeight()/2, null);

               c.drawBitmap(backButton,
                       w/2 - backPanel[0].getWidth()/2 + 200,h/2 - backPanel[0].getHeight()/2 + 150,null);
               if(tier == 0){
                   //1st tile
                   c.drawBitmap(selectionPallet,w/2 - backPanel[0].getWidth()/2 + 300
                           ,h/2 - backPanel[0].getHeight()/2 + 300,null);
                   //selection for 1st object
                   if(selectedMissile != 0)
                   c.drawBitmap(sButton,w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300 + 50,null);
                   else c.drawBitmap(selectiontag,w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300 + 50,null);


                   c.drawBitmap(uButton,w/2 - backPanel[0].getWidth()/2 + 300+ selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300 + sButton.getHeight() + 60,null);

                   // 2nd tile
                   c.drawBitmap(selectionPallet,w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() + 10,
                           h/2 - backPanel[0].getHeight()/2 + 300,null);
                   //selection for 1st object
                   if(selectedMissile != 1)
                   c.drawBitmap(sButton,w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() + 10
                                   + selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300 + 50,null);
                   else c.drawBitmap(selectiontag,w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() + 10
                                   + selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300 + 50,null);

                   c.drawBitmap(uButton,w/2 - backPanel[0].getWidth()/2 + 300  + selectionPallet.getWidth() + 10
                                   + selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300 + sButton.getHeight() + 60,null);


                   //3rd tile
                   c.drawBitmap(selectionPallet,w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() * 2 + 10,
                           h/2 - backPanel[0].getHeight()/2 + 300,null);
                   //selection for 1st object
                   if(selectedMissile != 2)
                   c.drawBitmap(sButton,w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() * 2 + 10 + selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300 + 50,null);
                   else c.drawBitmap(selectiontag,w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() * 2 + 10 + selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300 + 50,null);


                   c.drawBitmap(uButton,w/2 - backPanel[0].getWidth()/2 + 300+ selectionPallet.getWidth() * 2 + 10+ selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300 + sButton.getHeight() + 60,null);

                   //4th tile
                   c.drawBitmap(selectionPallet,w/2 - backPanel[0].getWidth()/2 + 300 ,
                           h/2 - backPanel[0].getHeight()/2 + 300 + selectionPallet.getHeight() + 50,null);
                   //selection for 1st object
                   if(selectedMissile != 3)
                   c.drawBitmap(sButton,w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300 +  selectionPallet.getHeight() + 50 + 50,null);
                   else c.drawBitmap(selectiontag,w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300 +  selectionPallet.getHeight() + 50 + 50,null);


                   c.drawBitmap(uButton,w/2 - backPanel[0].getWidth()/2 + 300+ selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300  + selectionPallet.getHeight() + 50 + sButton.getHeight() + 60,null);


                   //5th tile
                   c.drawBitmap(selectionPallet,w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() + 10,
                           h/2 - backPanel[0].getHeight()/2 + 300 + selectionPallet.getHeight() + 50,null);
                   //selection for 1st object
                   if(selectedMissile != 4)
                   c.drawBitmap(sButton,w/2 - backPanel[0].getWidth()/2 + 300+ selectionPallet.getWidth() + 10 + selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300 + selectionPallet.getHeight() + 50+ 50,null);
                   else c.drawBitmap(selectiontag,w/2 - backPanel[0].getWidth()/2 + 300+ selectionPallet.getWidth() + 10 + selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300 + selectionPallet.getHeight() + 50+ 50,null);


                   c.drawBitmap(uButton,w/2 - backPanel[0].getWidth()/2 + 300+ selectionPallet.getWidth() + 10+ selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300 + selectionPallet.getHeight() + 50+ sButton.getHeight() + 60,null);


                   //6th tile
                   c.drawBitmap(selectionPallet,w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() * 2 + 10,
                           h/2 - backPanel[0].getHeight()/2 + 300 + selectionPallet.getHeight() + 50,null);
                   //selection for 1st object
                   if(selectedMissile != 5)
                   c.drawBitmap(sButton,w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() * 2 + 10 + selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300 + selectionPallet.getHeight() + 50+ 50,null);
                   else c.drawBitmap(selectiontag,w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() * 2 + 10 + selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300 + selectionPallet.getHeight() + 50+ 50,null);


                   c.drawBitmap(uButton,w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() * 2 + 10+ selectionPallet.getWidth() - sButton.getWidth() - 50,
                           h/2 - backPanel[0].getHeight()/2 + 300+ selectionPallet.getHeight() + 50 + sButton.getHeight() + 60,null);

               }

           }

        }

    }

    public  void getFrame(Bitmap back,Bitmap[] selectionButtons , Bitmap[] glow,Bitmap[] backPanel,Bitmap uButton,Bitmap sButton,Bitmap selectionPallet,
                          Bitmap next,Bitmap previous,Bitmap selectiontag){
        this.back = Bitmap.createScaledBitmap(back,w,h,false);
        buttons = new Bitmap[selectionButtons.length];
        selected = new Bitmap[glow.length];
        this.backPanel = new Bitmap[backPanel.length];
        for(int i = 0; i < backPanel.length; i++)
        this.backPanel[i] = Bitmap.createScaledBitmap(backPanel[i],w * 105/100,h * 115/100,false);


        this.next = Bitmap.createScaledBitmap(next,230,100,false);
        this.backButton = previous;
        this.sButton = Bitmap.createScaledBitmap(sButton,w * 6/100,w * 6/100,false);
        this.selectionPallet = Bitmap.createScaledBitmap(selectionPallet,w * 25/100,h * 35/100,false);
        this.uButton = Bitmap.createScaledBitmap(uButton,w * 6/100,w * 6/100,false);
        this.selectiontag = Bitmap.createScaledBitmap(selectiontag,w * 6/100,w * 6/100,false);

    System.arraycopy(selectionButtons,0,this.buttons,0,selectionButtons.length);
        System.arraycopy(glow,0,this.selected,0,glow.length);

         b1 = buttons[0];
         b2 = buttons[1];


    }

    public void setSelectedMissile(int selectedMissile){
        this.selectedMissile = selectedMissile;
    }

    public Rect selectionButtonsBounds(int index){
        switch (index) {

            case 0 : return new Rect(w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() - sButton.getWidth() - 50,
                    h/2 - backPanel[0].getHeight()/2 + 300 + 50,w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() - sButton.getWidth() - 50 + sButton.getWidth(),
                    h/2 - backPanel[0].getHeight()/2 + 300 + 50 + sButton.getHeight());
            case 1 : return new Rect(w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() + 10
                    + selectionPallet.getWidth() - sButton.getWidth() - 50,
                    h/2 - backPanel[0].getHeight()/2 + 300 + 50,w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() + 10
                    + selectionPallet.getWidth() - sButton.getWidth() - 50 + sButton.getWidth(),
                    h/2 - backPanel[0].getHeight()/2 + 300 + 50 + sButton.getHeight());

            case 2 : return new Rect(w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() * 2 + 10 + selectionPallet.getWidth() - sButton.getWidth() - 50,
                    h/2 - backPanel[0].getHeight()/2 + 300 + 50,
                    w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() * 2 + 10 + selectionPallet.getWidth() - sButton.getWidth() - 50 + sButton.getWidth(),
                    h/2 - backPanel[0].getHeight()/2 + 300 + 50 + sButton.getHeight());

            case 3 : return new Rect(w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() - sButton.getWidth() - 50,
                    h/2 - backPanel[0].getHeight()/2 + 300 +  selectionPallet.getHeight() + 50 + 50,
                    w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() - sButton.getWidth() - 50 + sButton.getWidth(),
                    h/2 - backPanel[0].getHeight()/2 + 300 +  selectionPallet.getHeight() + 50 + 50 + sButton.getHeight());

            case 4 : return new Rect(w/2 - backPanel[0].getWidth()/2 + 300+ selectionPallet.getWidth() + 10 + selectionPallet.getWidth() - sButton.getWidth() - 50,
                    h/2 - backPanel[0].getHeight()/2 + 300 + selectionPallet.getHeight() + 50+ 50,
                    w/2 - backPanel[0].getWidth()/2 + 300+ selectionPallet.getWidth() + 10 + selectionPallet.getWidth() - sButton.getWidth() - 50 + sButton.getWidth(),
                    h/2 - backPanel[0].getHeight()/2 + 300 + selectionPallet.getHeight() + 50+ 50 + sButton.getHeight());

            case 5 : return new Rect(w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() * 2 + 10 + selectionPallet.getWidth() - sButton.getWidth() - 50,
                    h/2 - backPanel[0].getHeight()/2 + 300 + selectionPallet.getHeight() + 50+ 50,
                    w/2 - backPanel[0].getWidth()/2 + 300 + selectionPallet.getWidth() * 2 + 10 + selectionPallet.getWidth() - sButton.getWidth() - 50 + sButton.getWidth(),
                    h/2 - backPanel[0].getHeight()/2 + 300 + selectionPallet.getHeight() + 50+ 50 + sButton.getHeight());

        }
        return null;
    }


    public void glow(int index){

    }

    public int selectionKeysX(int index ){

       switch (index){
           case 0 : return 10;

       }
       return 0;
    }

    public  int selectionKeysY(int index ){

        switch (index){
            case 0 : return back.getHeight() - buttons[0].getHeight() - 10;


        }
        return 0;
    }

    public void  setSelectedFrames(int floor,int board){



    }

    public void setStateOfButton(int index){
        switch (index){
            case 0 :
                b1 = selected[0];
                b2 = buttons[1];
                break;
            case 1 :
                b1 = buttons[0];
                b2 = selected[1];
                break;
            case -1 :
                b1 = buttons[0];
                b2 = buttons[1];
                break;
        }

    }

    public int getFloor(){
        return floor;
    }

    public void setTier(int tier){
        this.tier = tier;
    }

    public int getTier(){
        return tier;
    }

    public void setSelectedFloor(int floor){
        this.floor = floor;
    }

    public  int getWidth(int index){
        switch (index){
            case 0 : return buttons[0].getWidth();
        }

     return 0;
    }

    public int  getHeight(int index){
        switch (index){
        case 0: return buttons[0].getHeight();
    }
        return 0;
    }

    public int getX(int index){
        switch(index){
            case 0 : return  w/2 - backPanel[0].getWidth()/2 + 200;

            case 1 : return w - next.getWidth() - 20;

            case 2 : return x + 10;

        }

        return 0;
    }
    public int getY(int index){
        switch (index){
            case 0 : return h/2 - backPanel[0].getHeight()/2 + 150;

            case 1 : return h - next.getHeight()- 20;

            case 2 :return y + 10;

        }
        return 0;
    }
    public int getButtonWidth(int index){
        switch (index) {

            case 0: return backButton.getWidth();

            case 1 : return next.getWidth();


        }
        return 0;
    }

    public int getButtonheight(int index){
        switch (index){
            case 0 : return backButton.getHeight();

            case 1 :  return next.getHeight();

        }

        return 0;
    }


}
