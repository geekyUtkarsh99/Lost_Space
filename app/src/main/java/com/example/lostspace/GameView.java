package com.example.lostspace;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


import com.example.lostspace.dataBlock.dataHandler;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

class  GameView extends SurfaceView implements SurfaceHolder.Callback{

    //custom threads
   private threadHandler tHand;

   private Thread animator;

   private Thread heavyProcess;

  private Thread fireFunc;


  private Thread shipFunc;

  private Thread msgRunner ;

  private Thread pinBoss ;


    //numbers
   private int posx = 10,posy = 10;
   private double width;
   private double height;
   private int[] touchX = new  int[3];
   private int[] touchY = new int[3];
   private int enemyCap = 5;
   private int wave = 1;
   private int score = 0;
   private int[] positions = {100,200,300,400,500,600};
   private int speed = 0;



   //strings
    private static String msg;

   //booleans
   private boolean inMenu = true;
   private boolean ingame = false;
   private boolean planet = false;
   private boolean lvlScreen =false;
   private boolean msgVisible = false;
   private boolean movement = false;
   private boolean enemy1 = false;
   private boolean fire = false;
   private boolean pinions = false;
   private boolean nullShips =false;
   private boolean startThread  = false;
   private boolean pinboss = false;
   private boolean healthship = false;
   private boolean animate = false;
   private boolean collider = false;
   private boolean fireThread = false;
   private boolean assemble = false;


    //images data
    private Bitmap[] ship = null;
    private Bitmap[] shipDes = null;
    private Bitmap mainScene;
    private Bitmap[] buttons = null;
    private Bitmap[] glow = null;
    private Bitmap[] planet1 = null;
    private Bitmap[] plan1Buttons =null;
    private  Bitmap[] control = null;
    private Bitmap[] defence1  =null;
    private Bitmap[] destroy = null;
    private Bitmap damageEnemy1 = null;
    private Bitmap[] missile1 = null;
    private Bitmap[] fireButton  = null;
    private Bitmap healthGauge = null;
    private Bitmap[] destM1  = null;
    private Bitmap[] back = null;
    private Bitmap[] spaceback  = null;
    private Bitmap point  = null;
    private Bitmap[] pinion  =null;
    private Bitmap pindam = null;
    private Bitmap[] nullShip =null;
    private Bitmap[] pluto = null;
    private Bitmap nullDam = null;
    private Bitmap[] enemybulls = null;
    private Bitmap[] pinionBoss = null;
    private Bitmap[] pinbossdam = null;
    private Bitmap[] hship = null;
    private Bitmap hpoint = null;
    private Bitmap hpdam = null;
    private Bitmap[] arm = null;
    private Bitmap[] frost;
    private Bitmap[] frostdist;
    private Bitmap frostDust = null;
    private Bitmap assembleback = null;
    private Bitmap[] upgSelection = null;
    private Bitmap[] upgglow = null;
    private Bitmap[] backPanel = null;
    private Bitmap selectionPallet = null;
    private Bitmap sButton = null;
    private Bitmap uButton = null;
    private Bitmap backButton = null;
    private Bitmap next = null;
    private Bitmap selectiontag = null;
    private Bitmap [] popButtons = null;
    private Bitmap[] popframe = null;


    //data object
    private dataHandler dt ;

    //game storage
    private static byte[][] data = null;
    private int[] playerInfo = null;
     private int[] blvl = null;
    private String mselect = "1";
    private String mlvl  = "";

    //constants
    private levels lvl = null;

    //menu reference
    private menu men;

    //assembly room
    private upgradeRoom upg = null;

    //player reference
    private player player = null;
    private location loc = null;

    //collision detector
    private collisionUtil cFunc = new collisionUtil();

    //controller reference
    private controller controlKeys = null;

    //enemy object
    private enemy en;
    private ArrayList<enemy> ene = null;

    //missile 1
    private missile m;
    private ArrayList<missile> mil = null;

    //buttons functional in game
    usableButtons triggerbuttons = null;

    //healthMeter
    private healthMeter hel = null;

    //popups
    private popups pip = null;

    //backgrounds
    private backGround bg = null;

    //pinion enemy
    private pinion p;
    private ArrayList<pinion> pin = null;

    //nullship
    private nullShip pl;
    private ArrayList<nullShip> Null  = null;

    //enemy bullets
    private enemyMissile enbul;
    private ArrayList<enemyMissile> enb = null;

    //pinion boss
    private pinionBoss pb;
    private ArrayList<pinionBoss> pbb  = null;

    //health ship
    private healthShip hps;
    private ArrayList<healthShip> hpss = null;

    //health point behaviors
    private healthPoints hp ;
    private ArrayList<healthPoints> hpp = null ;

    //missile 1 holder
   private arm1 arm1 = null;

   //frost missile
    private frost1 frost1 ;
    private Object String;


    //main constructor
    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        this.setFocusable(true);
        this.setClickable(true);

        this.setOnTouchListener(mainfunc);

        dt = new dataHandler(context);
        if(dt != null){
            try {
                loadProgress();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //load dimensions
        getDimensions();
        speed = 125;
        System.out.println("run");


        //thread handling
        tHand = new threadHandler(getHolder(),this);


    }

    public boolean performClick(){
        super.performClick();
        return true;
    }

     OnTouchListener mainfunc = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent e) {
            performClick();

            int action = e.getActionMasked();

            touchX[0] = (int) e.getX(0);
            touchY[0] = (int) e.getY(0);
            if(e.getPointerCount() >1) {
                touchX[1] = (int) e.getX(1);
                touchY[1] = (int) e.getY(1);
            }

            if(e.getPointerCount() >2){
            touchX[2] = (int) e.getX(2);
            touchY[2] = (int) e.getY(2);
            }

            //handling null pointer touch exception
            if(touchY == null){
                touchX = new int[3];
                touchY = new int[3];
            }
            int i = 0;

                 //for menu inputs
                 if (inMenu && men != null) {
                     if (action == MotionEvent.ACTION_DOWN) {

                         if (cFunc.checkBoxCollision(touchX[i], touchY[i], men.getX(0), men.getY(0), men.getWidth(0), men.getheight(0))) {

                             System.out.println("collide");
                             men.updateImage(1);

                         } else if (cFunc.checkBoxCollision(touchX[i], touchY[i], men.getX(1), men.getY(1), men.getWidth(1), men.getheight(1))) {
                             men.updateImage(0);
                         }


                     } else men.updateImage(-1);

                     if (action == MotionEvent.ACTION_UP) {
                         //exit button:
                         if (cFunc.checkBoxCollision(touchX[i], touchY[i], men.getX(0), men.getY(0), men.getWidth(0), men.getheight(0))) {

                             System.out.println("collide");
                             men.updateImage(1);
                             System.exit(1);
                         //play button:
                         } else if (cFunc.checkBoxCollision(touchX[i] ,touchY[i], men.getX(1), men.getY(1), men.getWidth(1), men.getheight(1))) {
                             men.updateImage(0);
                             inMenu = false;
                          assemble = true;
                          //reset image cycling
                          men.initiate();

                         }
                     }


                 }

                 if(assemble && upg != null ) {


                     if (upg.getFloor() < 0) {

                         if (cFunc.checkBoxCollision(touchX[i], touchY[i], upg.selectionKeysX(0), upg.selectionKeysY(0), upg.getWidth(0),
                                 upg.getHeight(0))) {
                             if (action == MotionEvent.ACTION_UP) {
                                 upg.setSelectedFloor(0);
                                 upg.setTier(0);
                                 upg.setStateOfButton(-1);
                             } else {
                                 upg.setStateOfButton(0);
                             }


                         } else upg.setStateOfButton(-1);

                         if (cFunc.checkBoxCollision(touchX[i], touchY[i], upg.getX(1),
                                 upg.getY(1), upg.getButtonWidth(1), upg.getButtonheight(1)) && action == MotionEvent.ACTION_UP) {


                             assemble = false;
                             ingame = true;
                             fireThread =true;
                             animate =true;
                             collider = true;
                             lvl = levels.l4;
                             createThread();

                             System.out.println(" response ");
                         }

                         if (cFunc.checkBoxCollision(touchX[i], touchY[i], upg.getX(2), upg.getY(2), upg.getButtonWidth(0)
                                 , upg.getButtonheight(0)) && action == MotionEvent.ACTION_UP) {
                             throwTouches();

                             assemble = false;
                             upg = null;
                             inMenu = true;

                         }


                     } else if (upg.getFloor() == 0) {

                         if (cFunc.checkBoxCollision(touchX[i], touchY[i], upg.getX(0),
                                 upg.getY(0), upg.getButtonWidth(0), upg.getButtonheight(0)) && action == MotionEvent.ACTION_UP) {

                             upg.setSelectedFloor(-1);

                         }

                         if (upg.getTier() == 0) {

                             if (upg.selectionButtonsBounds(0).contains(touchX[0], touchY[0]) && action == MotionEvent.ACTION_UP) {
                                 upg.setSelectedMissile(0);
                                 mselect = "0";
                                 speed = 125;
                             } else if (upg.selectionButtonsBounds(1).contains(touchX[0], touchY[0]) && action == MotionEvent.ACTION_UP) {
                                 upg.setSelectedMissile(1);
                                 mselect = "1";
                                 speed = 500;
                             } else if (upg.selectionButtonsBounds(2).contains(touchX[0], touchY[0]) && action == MotionEvent.ACTION_UP) {
                                 upg.setSelectedMissile(2);
                             } else if (upg.selectionButtonsBounds(3).contains(touchX[0], touchY[0]) && action == MotionEvent.ACTION_UP) {
                                 upg.setSelectedMissile(3);
                             } else if (upg.selectionButtonsBounds(4).contains(touchX[0], touchY[0]) && action == MotionEvent.ACTION_UP) {
                                 upg.setSelectedMissile(4);
                             } else if (upg.selectionButtonsBounds(5).contains(touchX[0], touchY[0]) && action == MotionEvent.ACTION_UP) {
                                 upg.setSelectedMissile(5);
                             }


                         }

                     }
                 }

                 if (ingame  && pip != null) {

                     //pause menu :
                     if(pip.getShow() == 1) {

                         if(pip.getBounds(3).contains(touchX[0],touchY[0])){
                             ingame = false;
                             inMenu = true;


                         }else if (pip.getBounds(0).contains(touchX[0],touchY[0]))
                             pip.setShow(0);
                          else if (pip.getBounds(1).contains(touchX[0],touchY[0])){
                              pip.setShow(0);
                              wave = 1;


                         }



                     }else {


                         if (triggerbuttons.getBounds(0).contains(touchX[0], touchY[0]) && action == MotionEvent.ACTION_UP) {

                             pip.setShow(1);

                         }
                         movement = true;
                         if (controlKeys != null && action == MotionEvent.ACTION_DOWN)
                             setMovement(movement);

                         triggerFire();
                         if (action == MotionEvent.ACTION_UP) {

                             for (int l = 0; l < touchX.length; l++) {
                                 touchX[l] = 0;
                                 touchY[l] = 0;
                             }
                            setMovement(false);
                             fire = false;
                         }

                     }

                 }

            return false;
        }
    };

    private void throwTouches(){

        for(int i = 0 ; i < touchX.length; i++){
            touchX[i] = 0;
            touchY[i] = 0;
        }


    }

    private void createThread(){
        if(animate){
            animator = new Thread(){
                public void run(){
                    while (animate){
                        if(player != null)
                        player.render();
                        if(arm1 != null)
                        arm1.render();
                        renderEnemy();
                        animateMissile();

                        try {
                            Thread.sleep(45);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            }
            };

            animator.start();
        }

        if(collider){
            heavyProcess = new Thread(){
                //complicated stuff
                public  void run(){
                    while(collider){
                 collisionDetector();

                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            };
            heavyProcess.start();


        }

        if(fireThread){
            fireFunc = new Thread(){
              public void run(){

                  while(fireThread){
                      fireFast();

                      MediaPlayer mFire;
                      if(mselect == "0"){
                          spawnMissile();
                          mFire = MediaPlayer.create(getContext(),R.raw.m1fire);

                          if(fire){
                              mFire.start();
                          }else mFire.stop();
                      }

                      if(mselect == "1") {
                          if (frost1 != null)
                              frost1.fire(fire);
                      }

                      try {
                          Thread.sleep(speed);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }

              }
            };
            fireFunc.start();
              shipFunc = new Thread(){
                public void run(){
                    while (true){
                        if(Null != null)
                            for(int i = 0; i < Null.size(); i++){
                                pl = Null.get(i);

                                if(!pl.getMovable()){


                                    //add missile


                                }

                                pl.setMovable(true);
                            }

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            };
            shipFunc.start();

            pinBoss = new Thread(){
                public  void run(){
                    while(startThread){
                        try {
                            Thread.sleep(20000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if(pbb != null)
                            for(int i = 0; i < pbb.size(); i++){
                                pb = pbb.get(i);
                                pb.setMove(true);
                            }

                    }
                }
            };
            pinBoss.start();

        }

    }



    public void draw(Canvas c){
        super.draw(c);

        spawn(c);
    }

    private void loadGraphics(int index){
        switch (index){
            case 0 :   //menu data
                buttons = new Bitmap[5];
                glow = new Bitmap[5];
                mainScene = BitmapFactory.decodeResource(getResources(),R.drawable.menu0);
                buttons[0] = BitmapFactory.decodeResource(getResources(),R.drawable.play);
                buttons[1] = BitmapFactory.decodeResource(getResources(),R.drawable.exit);
                glow[0] = BitmapFactory.decodeResource(getResources(),R.drawable.playglow);
                glow[1] = BitmapFactory.decodeResource(getResources(),R.drawable.exitglow);
           break;

            case 1:   //spacacraft

                ship  = new Bitmap[5];
                ship[0] = BitmapFactory.decodeResource(getResources(),R.drawable.ship1);
                ship[1] = BitmapFactory.decodeResource(getResources(),R.drawable.ship2);
                ship[2] = BitmapFactory.decodeResource(getResources(),R.drawable.ship3);
                ship[3] = BitmapFactory.decodeResource(getResources(),R.drawable.ship4);
                ship[4] = BitmapFactory.decodeResource(getResources(),R.drawable.ship5);
                break;

            case 2:   //planets data
                planet1 = new Bitmap[30];
                planet1[0] = BitmapFactory.decodeResource(getResources(),R.drawable.plan1);
                planet1[1] = BitmapFactory.decodeResource(getResources(),R.drawable.plan2);

                break;
            case 3 :    //levelscreen buttons
                plan1Buttons = new Bitmap[5];
                plan1Buttons[0] = BitmapFactory.decodeResource(getResources(),R.drawable.p1b1);
                plan1Buttons[1] = BitmapFactory.decodeResource(getResources(),R.drawable.p1b2);
                plan1Buttons[2] = BitmapFactory.decodeResource(getResources(),R.drawable.p1b3);
                plan1Buttons[3] = BitmapFactory.decodeResource(getResources(),R.drawable.p1b4);
                plan1Buttons[4] = BitmapFactory.decodeResource(getResources(),R.drawable.p1b5);
                break;
            case 4 :   //controller
                control = new Bitmap[8];
                control[0] = BitmapFactory.decodeResource(getResources(),R.drawable.up);
                control[1] = BitmapFactory.decodeResource(getResources(),R.drawable.down);
                control[2] = BitmapFactory.decodeResource(getResources(),R.drawable.left);
                control[3] = BitmapFactory.decodeResource(getResources(),R.drawable.right);
                control[4] = BitmapFactory.decodeResource(getResources(),R.drawable.leftup);
                control[5] = BitmapFactory.decodeResource(getResources(),R.drawable.rightup);
                control[6] = BitmapFactory.decodeResource(getResources(),R.drawable.leftdown);
                control[7] = BitmapFactory.decodeResource(getResources(),R.drawable.rightdown);
                break;
            case 5 :   //defence
                defence1 = new Bitmap[5];
                defence1[0] = BitmapFactory.decodeResource(getResources(),R.drawable.def1);
                defence1[1] = BitmapFactory.decodeResource(getResources(),R.drawable.def2);
                defence1[2] = BitmapFactory.decodeResource(getResources(),R.drawable.def3);
                defence1[3] = BitmapFactory.decodeResource(getResources(),R.drawable.def4);
                defence1[4] = BitmapFactory.decodeResource(getResources(),R.drawable.def5);
                break;
            case 6 :   //destroy
                destroy = new Bitmap[5];
                destroy[0] = BitmapFactory.decodeResource(getResources(),R.drawable.damage1);
                destroy[1] = BitmapFactory.decodeResource(getResources(),R.drawable.damage2);
                destroy[2] = BitmapFactory.decodeResource(getResources(),R.drawable.damage3);
                destroy[3] = BitmapFactory.decodeResource(getResources(),R.drawable.damage4);
                destroy[4] = BitmapFactory.decodeResource(getResources(),R.drawable.damage5);
break;
            case 7 :
                //damage
                damageEnemy1 = BitmapFactory.decodeResource(getResources(),R.drawable.damdef);

                break;
            case 8 :
                //fire button
                fireButton = new Bitmap[4];
                fireButton[0] = BitmapFactory.decodeResource(getResources(),R.drawable.firetab);
                fireButton[3] = BitmapFactory.decodeResource(getResources(),R.drawable.pausemenubutton);
break;
            case 9 :    //missile
                missile1 = new Bitmap[5];
                missile1[0] = BitmapFactory.decodeResource(getResources(),R.drawable.bullet1);
                missile1[1] = BitmapFactory.decodeResource(getResources(),R.drawable.bullet2);
                missile1[2] = BitmapFactory.decodeResource(getResources(),R.drawable.bullet3);
                missile1[3] = BitmapFactory.decodeResource(getResources(),R.drawable.bullet4);
                missile1[4] = BitmapFactory.decodeResource(getResources(),R.drawable.bullet5);
break;
            case 10 :   //health
                healthGauge = BitmapFactory.decodeResource(getResources(),R.drawable.hmeter);
                point = BitmapFactory.decodeResource(getResources(),R.drawable.hp2);
break;
            case 11 :    //destroy missile 1 (fire bullet)
                destM1 = new Bitmap[5];
                destM1[0] = BitmapFactory.decodeResource(getResources(),R.drawable.bdestroy1);
                destM1[1] = BitmapFactory.decodeResource(getResources(),R.drawable.bdestroy2);
                destM1[2] = BitmapFactory.decodeResource(getResources(),R.drawable.bdestroy3);
                destM1[3] = BitmapFactory.decodeResource(getResources(),R.drawable.bdestroy4);
                destM1[4] = BitmapFactory.decodeResource(getResources(),R.drawable.bdestroy5);
break;
            case 12 :  //background
                back = new Bitmap[4];
                spaceback = new Bitmap[5];
                back[0] = BitmapFactory.decodeResource(getResources(),R.drawable.back1);
                back[1] = BitmapFactory.decodeResource(getResources(),R.drawable.back2);
                back[2] = BitmapFactory.decodeResource(getResources(),R.drawable.back3);
                back[3] = BitmapFactory.decodeResource(getResources(),R.drawable.back4);
                spaceback[0] = BitmapFactory.decodeResource(getResources(),R.drawable.space1);
                spaceback[1] = BitmapFactory.decodeResource(getResources(),R.drawable.space2);
                spaceback[2] = BitmapFactory.decodeResource(getResources(),R.drawable.space3);
                spaceback[3] = BitmapFactory.decodeResource(getResources(),R.drawable.space4);
                spaceback[4] = BitmapFactory.decodeResource(getResources(),R.drawable.space5);
      break;
            case 13 :
                //pinion damage
                pindam = BitmapFactory.decodeResource(getResources(),R.drawable.piniondamage);
break;
            case 14 :
                // pinion
                pinion = new Bitmap[5];
                pinion[0] = BitmapFactory.decodeResource(getResources(),R.drawable.pinonp1);
                pinion[1] = BitmapFactory.decodeResource(getResources(),R.drawable.pinonp2);
                pinion[2] = BitmapFactory.decodeResource(getResources(),R.drawable.pinonp3);
                pinion[3] = BitmapFactory.decodeResource(getResources(),R.drawable.pinonp4);
                pinion[4] = BitmapFactory.decodeResource(getResources(),R.drawable.pinonp5);
break;
            case 15 :   //nullship
                nullShip = new Bitmap[5];
                pluto = new Bitmap[5];
                nullShip[0] = BitmapFactory.decodeResource(getResources(),R.drawable.pluto1);
                nullShip[1] = BitmapFactory.decodeResource(getResources(),R.drawable.pluto2);
                nullShip[2] = BitmapFactory.decodeResource(getResources(),R.drawable.pluto3);
                nullShip[3] = BitmapFactory.decodeResource(getResources(),R.drawable.pluto4);
                nullShip[4] = BitmapFactory.decodeResource(getResources(),R.drawable.pluto5);
                nullDam =BitmapFactory.decodeResource(getResources(),R.drawable.plutodamage);
                pluto[0] = BitmapFactory.decodeResource(getResources(),R.drawable.nullship1);
                pluto[1] = BitmapFactory.decodeResource(getResources(),R.drawable.nullship2);
                pluto[2] = BitmapFactory.decodeResource(getResources(),R.drawable.nullship1);
                pluto[3] = BitmapFactory.decodeResource(getResources(),R.drawable.nullship2);
                pluto[4] = BitmapFactory.decodeResource(getResources(),R.drawable.nullship1);
break;
            case 16 :  //bullets for enemy
                enemybulls = new Bitmap[5];
                enemybulls[0] = BitmapFactory.decodeResource(getResources(),R.drawable.enbul1);
                enemybulls[1] = BitmapFactory.decodeResource(getResources(),R.drawable.enbul2);
                enemybulls[2] = BitmapFactory.decodeResource(getResources(),R.drawable.enbul3);
                enemybulls[3] = BitmapFactory.decodeResource(getResources(),R.drawable.enbul4);
                enemybulls[4] = BitmapFactory.decodeResource(getResources(),R.drawable.enbul5);
break;
            case 17 :  //pinion boss
                pinionBoss =new Bitmap[5];
                pinionBoss[0] = BitmapFactory.decodeResource(getResources(),R.drawable.pinboss);
                pinionBoss[1] = BitmapFactory.decodeResource(getResources(),R.drawable.pinboss1);
                pinionBoss[2] = BitmapFactory.decodeResource(getResources(),R.drawable.pinboss2);
                pinionBoss[3] = BitmapFactory.decodeResource(getResources(),R.drawable.pinboss3);
                pinionBoss[4] = BitmapFactory.decodeResource(getResources(),R.drawable.pinboss4);
break;
            case 18 :
                //health ship
                hship = new Bitmap[5];
                hship[0] = BitmapFactory.decodeResource(getResources(),R.drawable.hship1);
                hship[1] = BitmapFactory.decodeResource(getResources(),R.drawable.hship2);
                hship[2] = BitmapFactory.decodeResource(getResources(),R.drawable.hship3);
                hship[3] = BitmapFactory.decodeResource(getResources(),R.drawable.hship4);
                hship[4] = BitmapFactory.decodeResource(getResources(),R.drawable.hship5);

                hpoint = BitmapFactory.decodeResource(getResources(),R.drawable.zbullet);

                hpdam = BitmapFactory.decodeResource(getResources(),R.drawable.hshipdam);
break;
            case 19 :   //arm for missile1
                arm = new Bitmap[5];
                arm[0] = BitmapFactory.decodeResource(getResources(),R.drawable.m1arm1);
                arm[1] = BitmapFactory.decodeResource(getResources(),R.drawable.m1arm2);
                arm[2] = BitmapFactory.decodeResource(getResources(),R.drawable.m1arm1);
                arm[3] = BitmapFactory.decodeResource(getResources(),R.drawable.m1arm2);
                arm[4] = BitmapFactory.decodeResource(getResources(),R.drawable.m1arm1);
break;
            case 20 : //for frost bullets lvl 1
                frost = new Bitmap[5];
                frost[0] = BitmapFactory.decodeResource(getResources(),R.drawable.frost1);
                frost[1] = BitmapFactory.decodeResource(getResources(),R.drawable.frost2);
                frost[2] = BitmapFactory.decodeResource(getResources(),R.drawable.frost3);
                frost[3] = BitmapFactory.decodeResource(getResources(),R.drawable.frost4);
                frost[4] = BitmapFactory.decodeResource(getResources(),R.drawable.frost5);
                frostdist =new Bitmap[5];
                frostdist[0] = BitmapFactory.decodeResource(getResources(),R.drawable.frostdist1);
                frostdist[1] = BitmapFactory.decodeResource(getResources(),R.drawable.frostdist2);
                frostdist[2] = BitmapFactory.decodeResource(getResources(),R.drawable.frostdist3);
                frostdist[3] = BitmapFactory.decodeResource(getResources(),R.drawable.frostdist4);
                frostdist[4] = BitmapFactory.decodeResource(getResources(),R.drawable.frostdist5);
                frostDust = BitmapFactory.decodeResource(getResources(),R.drawable.frostdust);
break;
            case 21 : //upgrade room
                assembleback = BitmapFactory.decodeResource(getResources(),R.drawable.assembleback);
                upgSelection = new Bitmap[2];
                upgglow = new Bitmap[2];
                upgSelection[0] = BitmapFactory.decodeResource(getResources(),R.drawable.bselect0);
                upgSelection[1] = BitmapFactory.decodeResource(getResources(),R.drawable.sselect0);
                upgglow[0] = BitmapFactory.decodeResource(getResources(),R.drawable.bselect1);
                upgglow[1] = BitmapFactory.decodeResource(getResources(),R.drawable.sselect1);
                backPanel = new Bitmap[1];
                backPanel[0] = BitmapFactory.decodeResource(getResources(),R.drawable.backlog);
                selectionPallet = BitmapFactory.decodeResource(getResources(),R.drawable.boardset);
                uButton = BitmapFactory.decodeResource(getResources(),R.drawable.upgbutton);
                sButton = BitmapFactory.decodeResource(getResources(),R.drawable.select);
                backButton = BitmapFactory.decodeResource(getResources(),R.drawable.backbutton);
                next = BitmapFactory.decodeResource(getResources(),R.drawable.next);
                selectiontag = BitmapFactory.decodeResource(getResources(),R.drawable.selectiontag);

                break;

            case 22 : //for pop up menu
                popButtons = new Bitmap [4];
                popframe = new Bitmap [1];
                popButtons[0] = BitmapFactory.decodeResource(getResources(),R.drawable.resume);
                popButtons[1] = BitmapFactory.decodeResource(getResources(),R.drawable.restart);
                popButtons[2] = BitmapFactory.decodeResource(getResources(),R.drawable.settings);
                popButtons[3] = BitmapFactory.decodeResource(getResources(),R.drawable.quit);
                popframe[0] = BitmapFactory.decodeResource(getResources(),R.drawable.resumeback);
break;


        }
    }

    private void createMsgThread(){
        if(msgVisible){
            msgRunner = new Thread(){
              public void run(){
                  while (msgVisible){
                      try {
                          Thread.sleep(3500);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                      msgVisible = false;
                      msg = null;
                  }

                }
            };
            msgRunner.start();
        }
    }

    private void loadNull(int index){
        switch (index){
            case 0 :   //menu data
                buttons = null;
                glow = null;

                break;

            case 1:   //spacacraft

                ship  = null;
                 break;

            case 2:   //planets data
                planet1 = null;

                break;
            case 3 :    //levelscreen buttons
                plan1Buttons = null;
                  break;
            case 4 :   //controller
                control = null;
                 break;
            case 5 :   //defence
                defence1 = null;
                  break;
            case 6 :   //destroy
                destroy = null;
                  break;
            case 7 ://damage
                damageEnemy1 = null;
                break;
            case 8 :
                //fire button
                fireButton = null;
                 break;
            case 9 :    //missile
                missile1 = null;
                break;
            case 10 :   //health
                healthGauge = null;
                point =null;
                break;
            case 11 :    //destroy missile 1 (fire bullet)
                destM1 = null;
                 break;
            case 12 :  //background
                back = null;
                spaceback = null;
                   break;
            case 13 :
                //pinion damage
                pindam = null;
                break;
            case 14 :
                // pinion
                pinion = null;
                 break;
            case 15 :   //nullship
                nullShip = null;
                pluto = null;
                   break;
            case 16 :  //bullets for enemy
                enemybulls = null;
                break;
            case 17 :  //pinion boss
                pinionBoss =null;
                  break;
            case 18 :
                //health ship
                hship = null;

                hpoint = null;

                hpdam = null;
                break;
            case 19 :   //arm for missile1
                arm = null;
                break;
            case 20 : //for frost bullets lvl 1
                frost = null;
                frostdist =null;
                 frostDust = null;
                break;
            case 21 : //upgrade room
                assembleback = null;
break;


        }
    }


    public void getDimensions(){
       width = Resources.getSystem().getDisplayMetrics().widthPixels;
       height = Resources.getSystem().getDisplayMetrics().heightPixels;

       System.out.println(width);
       System.out.println(height);
    }

    public void spawn(Canvas c){


        if(ingame) {
            //spawn entities
            addSpawn();

            //load player
            if(player == null){
                player = new player(posx,posy);
                loadGraphics(1);
                player.getFrames(ship);
            }

            //load Background
            if(bg == null){
                bg = new backGround();
                loadGraphics(12);
                bg.getFrames(back,spaceback);

            }

            if(mselect == "1"){
            if(frost1 == null){
                frost1 = new frost1(1);
                loadGraphics(19);
                loadGraphics(20);
                frost1.getFrames(arm,frost,frostdist,frostDust);
                System.out.println("concurrency check");
            }
            }

            //first thing drawn on the screen
            bg.draw(c);
            bg.moveFunc();

            //players configuration
            player.draw(c);

            //missile arm load
            if(arm1 == null){
                arm1 = new arm1();
                loadGraphics(19);
                arm1.getFrames(arm);

            }
            arm1.getFire(fire);
            arm1.setpositions(player.getX() + player.getWidth() - 110,player.getY() + 170);
            arm1.draw(c);
            player.move(loc);

            //draw health
            if(hel == null) {
                hel = new healthMeter();
                loadGraphics(10);
                hel.getFrames(healthGauge,point);

                }

            hel.draw(c);

            renderMissiles(c);
            spawnEnemy(c);

            //controllers configuration
            if(controlKeys == null){
                controlKeys = new controller();
                loadGraphics(4);
                controlKeys.setFrames(control);

            }
            controlKeys.draw(c);


            //fire buttons
           if(triggerbuttons == null){
               triggerbuttons = new usableButtons();
               loadGraphics(8);
               triggerbuttons.getButtons(fireButton);
           }

            triggerbuttons.draw(c);

              levelRunner();

              if(pip == null){
                  pip = new popups();
                  loadGraphics(22);
                  pip.getFrames(popButtons,popframe);

              }
              pip.draw(c);
              displayWave(msgVisible,wave,msg,c);
        }else {
            //space management
            pip = null;
            player = null;
            bg = null;
            triggerbuttons = null;
            controlKeys = null;
            hel = null;
            arm1 = null;
            pbb =null;
            ene = null;
            enb = null;
            pin = null;
            Null =  null;
            hpss = null;
            hpp = null;
            frost1 = null;
            animator = null;
            fireFunc = null;
            shipFunc = null;
            heavyProcess = null;
            pinBoss = null;
            animate =false;
            fireThread = false;
            collider = false;


            loadNull(1);
            loadNull(12);
            loadNull(19);
            loadNull(4);
            loadNull(8);
            loadNull(10);

        }

        if(inMenu){

            if(men == null){
                men = new menu();
                loadGraphics(0);
                men.getDimensions(width,height);
                men.setBitmap(mainScene,buttons,glow);

  }


            men.draw(c);
            men.move(true);

        }else {
            men = null;
            loadNull(0);
        }

        if(assemble){
            if(upg == null){
                upg = new upgradeRoom(0);

                loadGraphics(21);
                upg.getFrame(assembleback,upgSelection,upgglow,backPanel,
                        uButton,sButton,selectionPallet,
                        next,backButton,selectiontag);

               switch (mselect){
                   case "0": upg.setSelectedMissile(0);
                   break;
                   case "1" : upg.setSelectedMissile(1);
                   break;
               }
            }

            upg.draw(c);
        }else {
            upg = null;
            loadNull(21);
        }

          invalidate();
    }


    private void loadProgress() throws IOException {

        //data initialized
        data = new byte[2][];
        byte[] ms = mselect.getBytes();
        byte[] m1lvl = mlvl.getBytes();
        data[0] = ms;
        data[1] = m1lvl;
dt.delete("player.svg");
         if(dt.getPath("player.svg") != null) {

            data = dt.read("player.svg");

            mselect = new String(data[0]);
            mlvl = new String(data[1]);
System.out.println(mlvl);


        }else dt.writeFile(data,"player.svg");

         //speed of Thread
        switch (mselect){
            case "0" : speed = 125;
            break;
            case "1" : speed = 500;
                break;

        }

    }

    private void spawnEnemy(Canvas c) {

        if(enemy1){
            if(ene != null)
            for(int i = 0; i < ene.size(); i++){
                en = ene.get(i);
                if(en.getNullFactor())
                en.getFrames(defence1,destroy,damageEnemy1);
                en.draw(c);
                en.move(location.left);

                if(en.randomizer() ){
                    if(enb == null){
                        enb = new ArrayList<>();
                        loadGraphics(16);
                    }
                    enb.add(new enemyMissile(en.getX(),en.getY() + en.getHeight()/2,fireRule.n));

                }else en.randomizer();


                if(!en.getVis()){
                    ene.remove(en);
                score++;
                }
            }
        }

        if(pinions){
            if(pin != null)
            for(int i = 0; i < pin.size(); i++){
                p = pin.get(i);
                if(p.getNullFactor())
                p.getFrames(pinion,destroy,pindam);
                p.draw(c);
                p.move(location.left);

                if(p.randomizer() ){
                    if(enb == null){
                        enb = new ArrayList<>();
                        loadGraphics(16);
                    }

                    enb.add(new enemyMissile(p.getX(),p.getY(),fireRule.d));
                    enb.add(new enemyMissile(p.getX(),p.getY(),fireRule.u));
                }

                if(!p.getVis()) {
                    pin.remove(p);
                    score++;
                }
            }
        }

        if(nullShips){
            if(Null != null)
            for(int i = 0; i < Null.size(); i++){
                pl = Null.get(i);
               if( pl.getNullFactor())
                pl.getFrames(nullShip,pluto,destroy,nullDam);
                pl.draw(c);
                pl.move();
                if(!pl.getVis()){
                    Null.remove(pl);
                    score++;
                }
            }
        }

        if(pinboss){
            if(pbb != null)
            for(int i = 0; i < pbb.size(); i++){
                pb = pbb.get(i);
                if(pb.getNullFactor())
                pb.getFrames(pinionBoss,destroy);
                pb.draw(c);
                pb.move(location.left);



                if(!pb.getVis()){
                    pbb.remove(pb);
                    score++;
                }
            }
        }

             if(hpss != null)
            for(int i = 0;i < hpss.size();i++){
                hps = hpss.get(i);
                if(hps.getNullFactor())
                hps.getFrames(hship,destroy,hpdam);
                hps.draw(c);
                hps.move(location.up);

                if(!hps.getVis()){
                    //add health

                    if(hpp == null){
                        hpp = new ArrayList<>();

                    }
                    hpp.add(new healthPoints(hps.getWidth()/2 + hps.getX(),hps.getHeight()/2 + hps.getY(),location.up));
                    hpp.add(new healthPoints(hps.getWidth()/2 + hps.getX(),hps.getHeight()/2 + hps.getY(),location.down));

                    hpss.remove(hps);
               }

        }

    }

    private void displayWave(boolean msgVisible,int wave,String msg,Canvas c){
        if(msgVisible){

            Paint p = new Paint();
            p.setTextSize(24);
            p.setColor(Color.CYAN);

            c.drawText("WAVE : " + wave,(int )width/2,(int )height/3,p);
            if(msg != null)
            c.drawText(msg,(int )width/2,(int)height/2 + 50,p);
        }

    }

    private void addSpawn(){
        if(enemy1){
            if(ene == null){
            ene = new ArrayList<>();

            loadGraphics(5);
            loadGraphics(6);
            loadGraphics(7);

            }

        if (ene.size() <= enemyCap){
          int y = new Random().nextInt((int)height - 300);
            ene.add(new enemy((int)width,y));

        }
        }else {
            ene = null;
            loadNull(5);
            loadNull(7);
        }

        if( pinions){
          if(pin == null){
            pin = new ArrayList<>();
          loadGraphics(6);
          loadGraphics(13);
          loadGraphics(14);
          }
            if (pin.size() <= enemyCap){
            int y = new Random().nextInt((int)height - 300);
             pin.add(new pinion((int)width,y));

            }
        }else {
            pin = null;
            loadNull(13);
            loadNull(14);
        }

        if(nullShips){
          if(Null == null) {
              Null = new ArrayList<>();
          loadGraphics(6);
          loadGraphics(15);

          }
          if (Null.size() <= enemyCap) {
                int y = new Random().nextInt((int) height - 300);
                int x = new Random().nextInt((int) width - 300);

                Null.add(new nullShip(x, y));

           }
        }else {
            Null = null;
            loadNull(15);
        }

        if(pinboss) {
            if(pbb == null){
                pbb = new ArrayList<>();
                loadGraphics(17);

            }
            if (pbb.size() <= enemyCap) {
                int y = new Random().nextInt((int) height / 2);
                pbb.add(new pinionBoss((int) width, y));
            }
        }else {
            pbb = null;
            loadNull(17);
        }

        if(healthship){
            if(hpss == null){
                hpss = new ArrayList<>();
                loadGraphics(18);
            }
        if(hpss.size() <= enemyCap ){
             int x = new Random().nextInt((int)width - 300);
            hpss.add(new healthShip(x,(int )height));

        }
        }else {
            hpss = null;
            loadNull(18);
        }
    }

    public void renderEnemy(){
        if(enemy1)
            if(ene != null)
        for(int i= 0;i < ene.size();i++){

            en = ene.get(i);
            en.render();
        }

        if(nullShips)
            if(Null != null)
            for(int i = 0;i< Null.size();i++){
                pl = Null.get(i);
                pl.render();
            }


        if(pinions)
            if(pin != null)
            for(int i = 0; i < pin.size();i++){
                p = pin.get(i);
                p.render();
            }

        if(pinboss){
            if(pbb != null)
            for(int i = 0; i < pbb.size(); i++){
                pb = pbb.get(i);
                pb.render();


            }
        }


        if(hpss != null)
            for(int i = 0; i < hpss.size();i++){
                hps = hpss.get(i);
                hps.render();
            }



    }

    private void fireFast(){
        if(pinboss){
            for(int i = 0; i < pbb.size(); i++){
                pb = pbb.get(i);

                if(pb.getMove()){

                    if(enb == null){
                        enb = new ArrayList<>();
                        loadGraphics(16);
                    }
                    enb.add(new enemyMissile(pb.getX() + 200, pb.getY() + 400, fireRule.u));
                    enb.add(new enemyMissile(pb.getX() + 200, pb.getY() + 400, fireRule.d));
                    enb.add(new enemyMissile(pb.getX() + 200, pb.getY() + 400, fireRule.n));

                }
            }
        }
    }


    private  void renderMissiles(Canvas c){
        if(mil != null)
        for(int i = 0; i < mil.size(); i++){
            m = mil.get(i);
            if(m.getNullFactor())
            m.getFrames(missile1,destM1,null);
            m.draw(c);
            m.move(location.right);

            if(!m.getVis())
            mil.remove(m);
        }

        if(frost1 != null){
            frost1.setPositions(player.getX() + player.getWidth() - 110,player.getY() + 170);
            frost1.draw(c);
        }

        if(enb != null)
        for(int i = 0;i < enb.size(); i++){
            enbul = enb.get(i);
            if(enbul.getNullFactor())
            enbul.getFrames(enemybulls);
            enbul.draw(c);
            enbul.move(location.left);

            if(!enbul.getVis())
                enb.remove(enbul);

        }

        if(hpp != null)
        for(int i =0 ;i < hpp.size(); i++){
            hp = hpp.get(i);
            if(hp.getNullfactor())
            hp.getFrames(hpoint);
            hp.draw(c);
            hp.move(location.left);

            if(!hp.getVis())
                hpp.remove(hp);

        }


    }

    private void spawnMissile(){
           if(fire) {
              if(mil == null){
               mil = new ArrayList<>();
              loadGraphics(9);
              loadGraphics(11);
              }
               if (mil.size() <= 55){
                   mil.add(new missile(player.getX() + player.getWidth() / 2 + 195, player.getY() + player.getHeight() / 2));

           }}

    }

    private void animateMissile(){
        if (mil != null)
            for(int i = 0; i < mil.size();i++){

                m = mil.get(i);
                m.render();
            }

        if(enb != null)
        for(int i = 0; i < enb.size(); i++){
            enbul = enb.get(i);
            enbul.render();
        }

        if(frost1 != null && frost1.getSize() != 0)
            frost1.render(fire);



    }

    //collision detection-not perfect yet--
    private void collisionDetector(){
        try {
            if (ingame) {
                if (enemy1 && ene != null)
                    for (int i = 0; i < ene.size(); i++) {
                        en = ene.get(i);

                       if(player.getBounds().intersect(en.getBounds())){
                            if (cFunc.checkPixelCollision(player.getImage(), en.getImage(), player.getX(), en.getX(), player.getY(), en.getY())) {
                                hel.decrease(1);

                            }

                        }
                    }

                //collision detection for frost bullets
                if(frost1 != null){

                    if(frost1.getSize() > 0) {
                        int i = 0;

                        while (i < 55) {

                            if(frost1.getImage(i) != null) {


                                if (ene != null)
                                    for (int l = 0; l < ene.size(); l++) {
                                        en = ene.get(l);

                                      if(frost1.getBounds(0,i).intersect(en.getBounds())){
                                                en.getDamagePoints(frost1.getDamagePoint());

                                                en.setDamage(true);
                                                frost1.setDestroy(true,i);


                                        }
                                        if(frost1.getBounds(1,i).intersect(en.getBounds())){
                                            en.getDamagePoints(frost1.getDamagePoint());

                                            en.setDamage(true);
                                            frost1.setDestroy(true,i);


                                        }


                                    }

                                if(pin != null)
                                    for (int l = 0; l < pin.size(); l++) {

                                        p = pin.get(l);
                                        if(frost1.getBounds(0,i).intersect(p.getBounds())){
                                                p.getDamagePoints(frost1.getDamagePoint());
                                                p.setDamage(true);
                                                frost1.setDestroy(true,i);


                                        } if(frost1.getBounds(1,i).intersect(p.getBounds())){
                                            p.getDamagePoints(frost1.getDamagePoint());
                                            p.setDamage(true);
                                            frost1.setDestroy(true,i);


                                        }

                                    }

                                if(Null != null)
                                    for (int j = 0; j < Null.size(); j++) {
                                        pl = Null.get(j);
                                        if(frost1.getBounds(0,i).intersect(pl.getBounds())){

                                            pl.getDamagePoints(frost1.getDamagePoint());
                                                pl.setDamage(true);
                                                frost1.setDestroy(true,i);

                                        } if(frost1.getBounds(1,i).intersect(pl.getBounds())){

                                            pl.getDamagePoints(frost1.getDamagePoint());
                                            pl.setDamage(true);
                                            frost1.setDestroy(true,i);

                                        }
                                    }

                                if(pbb != null)
                                    for (int k = 0; k < pbb.size(); k++) {
                                        pb = pbb.get(k);
                                        if(frost1.getBounds(0,i).intersect(pb.getBounds())){
                                            pb.getDamagePoints(frost1.getDamagePoint());
                                                pb.setDamage(true);
                                                frost1.setDestroy(true,i);

                                        }if(frost1.getBounds(1,i).intersect(pb.getBounds())){
                                            pb.getDamagePoints(frost1.getDamagePoint());
                                            pb.setDamage(true);
                                            frost1.setDestroy(true,i);

                                        }
                                    }


                                if(hpss != null)
                                    for(int g = 0;g <  hpss.size();g++){
                                        hps = hpss.get(g);
                                        if(frost1.getBounds(0,i).intersect(hps.getBounds())){
                                            hps.getDamagePoints(frost1.getDamagePoint());
                                                hps.setDamage(true);
                                                frost1.setDestroy(true,i);

                                        }if(frost1.getBounds(1,i).intersect(hps.getBounds())){
                                            hps.getDamagePoints(frost1.getDamagePoint());
                                            hps.setDamage(true);
                                            frost1.setDestroy(true,i);

                                        }
                                    }




                            }else break;

                            i++;
                        }
                    }
                }

                //for health increaser
                if(hpp != null)
                for(int i = 0; i < hpp.size();i++){
                    hp = hpp.get(i);
                   if(player.getBounds().intersect(hp.getBounds())){
                        if (cFunc.checkPixelCollision(player.getImage(), hp.getImage(), player.getX(), hp.getX(), player.getY(), hp.getY())) {
                           hel.healthAdder(30);
                           hpp.remove(hp);
                        }

                    }
                }

                if (pinions && pin != null) {
                    for (int i = 0; i < pin.size(); i++) {
                        p = pin.get(i);
                       if(player.getBounds().intersect(p.getBounds())) {
                            if (cFunc.checkPixelCollision(player.getImage(), p.getImage(), player.getX(), p.getX(), player.getY(), p.getY())) {
                                hel.decrease(1);

                            }
                        }
                    }
                }

                if (nullShips && Null != null) {
                    for (int i = 0; i < Null.size(); i++) {
                        pl = Null.get(i);
                       if(pl.getBounds().intersect(player.getBounds())) {
                            if (cFunc.checkPixelCollision(player.getImage(), pl.getImage(), player.getX(), pl.getX(), player.getY(), pl.getY())) {
                                hel.decrease(1);

                            }
                        }
                    }
                }


                //for enemy missile;
                if(enb != null)
                for (int i = 0; i < enb.size(); i++) {
                    enbul = enb.get(i);
                    if (!(enbul.getX() == 0))
                        if(enbul.getBounds().intersect(player.getBounds())){
                            if (cFunc.checkPixelCollision(player.getImage(), enbul.getImage(), player.getX(), enbul.getX(), player.getY(), enbul.getY())) {
                                hel.decrease(1);
                                enb.remove(enbul);

                            }

                        }

                }

                  if(mil != null)
                for (int i = 0; i < mil.size(); i++) {
                    m = mil.get(i);
                    if(ene != null)
                    for (int l = 0; l < ene.size(); l++) {
                        en = ene.get(l);

                       if(m.getBounds().intersect(en.getBounds())) {
                            if (cFunc.checkPixelCollision(m.getImage(), en.getImage(), m.getX(), en.getX(), m.getY(), en.getY())) {

                                en.getDamagePoints(1);

                                en.setDamage(true);
                                m.setDestroy(true);

                            }
                        }


                    }
                    if(pin != null)
                    for (int l = 0; l < pin.size(); l++) {

                        p = pin.get(l);
                       if(m.getBounds().intersect(p.getBounds())){
                            if (cFunc.checkPixelCollision(m.getImage(), p.getImage(), m.getX(), p.getX(), m.getY(), p.getY())) {
                                p.getDamagePoints(1);
                                p.setDamage(true);
                                m.setDestroy(true);
                            }

                        }

                    }

                    if(Null != null)
                    for (int j = 0; j < Null.size(); j++) {
                        pl = Null.get(j);
                       if(m.getBounds().intersect(pl.getBounds())) {
                            if (cFunc.checkPixelCollision(m.getImage(), pl.getImage(), m.getX(), pl.getX(), m.getY(), pl.getY())) {
                                pl.getDamagePoints(1);
                                pl.setDamage(true);
                                m.setDestroy(true);
                            }
                        }
                    }

                    if(pbb != null)
                    for (int k = 0; k < pbb.size(); k++) {
                        pb = pbb.get(k);
                       if(m.getBounds().intersect(pb.getBounds())) {
                            if (cFunc.checkPixelCollision(m.getImage(), pb.getImage(), m.getX(), pb.getX(), m.getY(), pb.getY())) {
                                pb.getDamagePoints(1);
                                pb.setDamage(true);
                                m.setDestroy(true);
                            }
                        }
                    }


                       if(hpss != null)
                        for(int g = 0;g <  hpss.size();g++){
                            hps = hpss.get(g);
                           if(m.getBounds().intersect(hps.getBounds())){
                                if (cFunc.checkPixelCollision(m.getImage(), hps.getImage(), m.getX(), hps.getX(), m.getY(), hps.getY())) {
                                    hps.getDamagePoints(1);
                                    hps.setDamage(true);
                                    m.setDestroy(true);
                                }
                            }
                        }


                }
            }
        }catch (Exception e){

            collisionDetector();
        }
        }


        private void jsonHolder(){

            JSONObject jsonObject = new JSONObject();

        }


    //player movement algorithm
    private  void setMovement(boolean movement){
        for(int i = 0; i < touchX.length; i++){
        if(movement){
            System.out.println("access movement");
            if(cFunc.checkBoxCollision(touchX[i],touchY[i],controlKeys.getX(0),controlKeys.getY(0),controlKeys.getWidth(),controlKeys.getHeight())) {
                loc = location.upl;
                 break;
            }else if(cFunc.checkBoxCollision(touchX[i],touchY[i],controlKeys.getX(1),controlKeys.getY(0),controlKeys.getWidth(),controlKeys.getHeight())){
                loc = location.up;
            break;}
            else if(cFunc.checkBoxCollision(touchX[i],touchY[i],controlKeys.getX(2),controlKeys.getY(0),controlKeys.getWidth(),controlKeys.getHeight())){
                loc = location.upr;
            break;}
            else if(cFunc.checkBoxCollision(touchX[i],touchY[i],controlKeys.getX(0),controlKeys.getY(1),controlKeys.getWidth(),controlKeys.getHeight())){
                loc = location.left;
            break;}
            else if(cFunc.checkBoxCollision(touchX[i],touchY[i],controlKeys.getX(2),controlKeys.getY(1),controlKeys.getWidth(),controlKeys.getHeight())){
                loc = location.right;
            break;}
            else if(cFunc.checkBoxCollision(touchX[i],touchY[i],controlKeys.getX(0),controlKeys.getY(2),controlKeys.getWidth(),controlKeys.getHeight())){
                loc = location.downl;
            break;}
            else if(cFunc.checkBoxCollision(touchX[i],touchY[i],controlKeys.getX(1),controlKeys.getY(2),controlKeys.getWidth(),controlKeys.getHeight())){
                loc = location.down;
            break;}
            else if(cFunc.checkBoxCollision(touchX[i],touchY[i],controlKeys.getX(2),controlKeys.getY(2),controlKeys.getWidth(),controlKeys.getHeight())){
                loc = location.downr;
            break;}
            else loc = null;
        }else loc = null;
        }
        
     }



     private void triggerFire(){

        for(int i =0 ; i < touchX.length ; i++) {
            if(triggerbuttons != null)
            if (cFunc.checkBoxCollision(touchX[i], touchY[i], triggerbuttons.getX(), triggerbuttons.getY(), triggerbuttons.getWidth(), triggerbuttons.getHeight())) {
                this.fire = true;

            }
        }



     }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        tHand.setRunnable(true);
        tHand.start();



        startThread = true;


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try {
                tHand.join();
                animator.join();
                fireFunc.join();
                shipFunc.join();
                pinBoss.join();
                heavyProcess.join();
                msgRunner.join();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                retry = false;
            }
        }

    }

    //main process runner
    public void update(){

    }

    public void dispose(){

        enemy1 = false;
        msgVisible =false;
        nullShips = false;
        pinboss = false;
        pinions = false;
        healthship = false;
        fire = false;
        msg = null;
        lvl = null;
        ingame =false;
        inMenu = true;
        score = 0;
        enemyCap = -1;
        wave = 1;

    }

    public void levelRunner(){
        if(bg != null){
            if(lvl == levels.l1){
                bg.setIndex(1);
                if(wave <=3){
                    if(wave == 1){
                        enemyCap = 5;

                        enemy1 =true;


                        if(score >= 15) {
                            wave++;
                            msgVisible = true;
                            msg = "more on the way";
                            ene.clear();
                            createMsgThread();
                        }

                    }else if(wave == 2){
                        enemy1 = true;
                        if(score >= 25)
                            wave++;
                    }else if(wave == 3){
                        enemy1 = true;
                        if(score >= 35){

                            lvl = levels.l2;
                            wave = 1;
                            //msgVisible = true;
                             //dispose();
                        }
                    }

                }
            }else if(lvl == levels.l2){
                if(wave <= 4){
                    bg.setIndex(1);
                    if(wave == 1){
                        enemyCap = 5;
                        pinions = true;
                        if(score >= 10){
                            wave++;
                            msgVisible = true;
                            msg = "another";
                            createMsgThread();
                            pin.clear();
                        }
                    }else if(wave == 2){
                        enemy1 = true;
                        if(score >= 20){
                            wave++;
                            msgVisible = true; createMsgThread();
                            ene.clear();
                        }
                    }else if(wave == 3){
                        pinions = true;
                        enemy1 = true;
                        enemyCap = 2;
                        if(score >= 30){
                            wave++;
                            msgVisible = true; createMsgThread();

                            pin.clear();
                            ene.clear();
                        }
                    }else if(wave == 4){
                        enemy1 = true;
                        pin.clear();
                        if(score >= 45){
                            //dispose();
                            //msgVisible = true;
                            lvl = levels.l3;
                            wave = 1;
                        }
                    }
                }
            }else if(lvl == levels.l3){
                if(wave <= 3){
                    bg.setIndex(1);
                    enemyCap = 4;
                    if(wave == 1){
                        pinions = true;
                        if(score >= 20){
                            pin.clear();
                            wave++;
                            msgVisible = true; createMsgThread();

                        }
                    }else if(wave == 2){
                        pinions = true;
                        if(score >= 30){
                            pin.clear();
                            wave++;
                            msgVisible = true; createMsgThread();

                        }
                    }else if(wave == 3){
                        pinions = true;
                        if(score >= 40){
                            //dispose();
                            //msgVisible = true;
                            lvl = levels.l4;
                            wave = 1;
                        }
                    }
                }

            }else if(lvl == levels.l4){
                if(wave <= 3){
                    bg.setIndex(1);
                    if(wave == 1){
                        enemyCap = 5;
                        pinions = true;
                        if(score >= 1){
                            pin.clear();
                            wave++;
                            msgVisible = true; createMsgThread();

                        }

                    }else if(wave == 2){
                        enemy1 = true;
                        pinions = true;
                        if(score >= 40){
                            pin.clear();
                            ene.clear();
                            wave++;
                            msgVisible = true; createMsgThread();

                        }
                    }else if(wave == 3){
                        pin.clear();
                        ene.clear();
                        enemyCap = 2;
                        nullShips = true;
                        if(score >= 45){
                            //dispose();
                            //msgVisible = true;
                            lvl = levels.l5;
                            wave = 1;
                        }
                    }
                }
            }else if(lvl == levels.l5){
                if(wave <= 3){
                    bg.setIndex(1);
                    if(wave == 1){
                        enemyCap = 4;
                        pinions = true;
                        if(score >= 20){
                            wave++;
                            pinions = false;
                            msgVisible = true; createMsgThread();

                        }

                    }else if(wave == 2){
                        enemyCap = 9;
                        pinions = true;
                        if(score >= 40){
                            wave++;
                            pinions =false;
                            msgVisible = true; createMsgThread();

                        }
                    }else if(wave == 3){
                        enemyCap = 0;

                        pinboss = true;

                       healthship = true;

                        if(score >= 41){
                            pinboss = false;
                            pinions = false;
                            healthship = false;
                            dispose();

                        }
                    }
                }
            }else if(lvl == levels.l6){

            }
        }



    }





}




