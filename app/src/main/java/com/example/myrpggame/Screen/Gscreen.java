package com.example.myrpggame.Screen;

import android.graphics.Color;

import com.example.myrpggame.btn.downbtn;
import com.example.myrpggame.btn.leftbtn;
import com.example.myrpggame.btn.rightbtn;
import com.example.myrpggame.btn.upbutton;
import com.example.myrpggame.sprite.ghost;
import com.example.myrpggame.sprite.man;

import loon.LSystem;
import loon.LTexture;
import loon.Screen;
import loon.action.sprite.Animation;
import loon.action.sprite.Sprite;
import loon.action.sprite.Sprites;
import loon.canvas.Image;
import loon.canvas.LColor;
import loon.component.LTextArea;
import loon.event.GameTouch;
import loon.opengl.GLEx;
import loon.utils.timer.LTimer;
import loon.utils.timer.LTimerContext;

public  class Gscreen extends Screen {
    long current,endtime;
    private downbtn downbtn;
    private leftbtn leftbtn;
    private rightbtn rightbtn;
    private upbutton upbtn;
    private Sprite ghost=new ghost(),
            bgd=new Sprite("res\\drawable\\stage.png",50,-380,400,700),
            shocked=new Sprite("res\\drawable\\shocked.png",245,56,35,35),
            jumpface=new Sprite("res\\drawable\\jumpface.png",0,0,500,400);;
    private Image img =null;
    private Sprites sprmgr = new Sprites(this);
    private Sprite gy=new man();
    private  LTextArea area,textArea;
    private boolean flag1,flag2,flag3,flag4;/*分别为上下左右的动画更新判别符*/
    private boolean _left,_right,_up,_down;/*判别符防止原地不动*/
    LTimer timer = new LTimer(LSystem.SECOND);
    private int manX,manY;/*世界坐标*/
    private LTexture texture;
    private int clickcount;
    public Gscreen() {
       // manX=0;
       // manY=0;//将主角出生点作为坐标原点即地图是一个X[-200,200]Y[0,700]的坐标系
        clickcount=0;
        manX=0;
        manY=0;
        flag1=false;
        flag2=false;
        flag3=false;
        flag4=false;
        _left=false;
        _right=false;
        _up=false;
        _down=false;

        texture = getGameWinFrame(478, 100, LColor.black, LColor.blue, false);
        textArea = new LTextArea(0,220,478,100);
        textArea.setBackground(texture);
        textArea .setSlideMessage(true);
        textArea.setShowType(LTextArea.TYPE_DOWN);
        textArea .setLeftOffset(25);
        textArea .setTopOffset(5);
        textArea .put("啊!头好痛,好难受。", LColor.white);
        shocked.setVisible(false);
        jumpface.setVisible(false);
        sprmgr.add(bgd);
        sprmgr.add(ghost);
        sprmgr.add(gy);
        sprmgr.add(shocked);
        sprmgr.add(jumpface);
        this.addScreen(new Fscreen());

        }

    @Override
    public void draw(GLEx glEx) {
        //LButton btn = new LButton("res\\drawable\\upbtn.png","", LColor.blue,38,44,50,180);

        sprmgr.createUI(glEx);
        upbtn = new upbutton("res\\drawable\\upbtn.png","", LColor.blue,38,44,50,180);
        downbtn = new downbtn("res\\drawable\\downbtn.png","", LColor.blue,38,44,49,255);
        leftbtn = new leftbtn("res\\drawable\\leftbtn.png","", LColor.blue,44,38,10,220);
        rightbtn = new rightbtn("res\\drawable\\rightbtn.png","", LColor.blue,44,38,82,220);
        upbtn.createUI(glEx);
        downbtn.createUI(glEx);
        leftbtn.createUI(glEx);
        rightbtn.createUI(glEx);
        area = new LTextArea(400, 238, 50, 40);
        area.setBackground("res\\drawable\\btn.png");
        area.createUI(glEx);
        textArea.createUI(glEx);


    }

    @Override
    public void onLoad() {
        this.addScreen(new Fscreen());
    }

    @Override
    public void alter(LTimerContext lTimerContext) {
        if (this.timer.action(1000)) {  //一秒更新一次精灵状态
            sprmgr.update(150);
            bgd.update(150);


        }

    }

    @Override
    public void resize(int i, int i1) {
        this.setSize(i,i1);
    }

    @Override
    public void touchDown(GameTouch gameTouch) {

        if(clickcount==2){
        textArea.setVisible(false);
        clickcount++;
    }
        if(clickcount==1){
            textArea.put("!???这是哪???", LColor.white);
            clickcount++;
        }
        if(clickcount==0){
            textArea.put("怎么感觉身体怪怪的,好轻。",LColor.white);
            clickcount++;
        }




        if(gameTouch.getX()<=450&&gameTouch.getX()>=400&&gameTouch.getY()>=240&&gameTouch.getY()<=280){
            System.out.println(manY+" "+manX);
                if (manY >= 586 && manY <= 590 && manX <= 2 && manX >= -2) {
                    if(clickcount==3) {
                        jumpface.setVisible(true);
                        ghost.setVisible(true);
                        shocked.setVisible(true);
                        clickcount++;
                    }
                    if(clickcount==5){
                       this.runNextScreen();

                    }
             }
        }

           // System.out.println(gameTouch.get()+" "+manX+" "+manY);

    }

    @Override
    public void touchUp(GameTouch gameTouch) {
        if(gameTouch.getX()<=450&&gameTouch.getX()>=400&&gameTouch.getY()>=240&&gameTouch.getY()<=280) {
            if(clickcount==4) {
                 current = System.currentTimeMillis();
                System.out.println(current + " " + manX + " " + manY);
                 endtime = System.currentTimeMillis();
                boolean flag = false;

                System.out.println(endtime - current);
                while ((endtime - current <= 500)) {
                    System.out.println(endtime - current);
                    flag = true;
                    endtime = System.currentTimeMillis();
                    shocked.setVisible(true);

                }
                if (flag) {
                    // shocked.setVisible(false);
                    jumpface.setVisible(false);
                }
                clickcount++;
            }
        }
    }

    @Override
    public void touchMove(GameTouch gameTouch) {
        /***
         * GOUP*/
        if (gameTouch.getX() >= 55 && gameTouch.getX() <= 80 && gameTouch.getY() <= 217 && gameTouch.getY() >= 185) {
            if (!flag1) {
                flag1 = true;
                flag2 = false;
                flag3 = false;
                flag4 = false;
                gy.setAnimation(Animation.getDefaultAnimation("res\\drawable\\meup.png", 32, 48, 1300));

            }
            if ((((gy.getCenterX() - ghost.getCenterX()) * (gy.getCenterX() - ghost.getCenterX())) +
                    ((gy.getCenterY() - ghost.getCenterY()) * (gy.getCenterY() - ghost.getCenterY())) <= 600) && _up == false) {
                _up = false;
                _left = true;
                _right = true;
                _down = true;
                gy.moveToX(gy.getX(), gy.getY());
            } else {

                if (iscenter(gy) && manY <= 530) {
                    bgd.move_down(2);
                    manY = manY + 2;
                }
                if (iscenter(gy) && manY > 530) {
                    gy.move_up(2);
                    manY = manY + 2;
                }
                if (!iscenter(gy) && (manY <= 590) && (manX >= -24 || manX <= 24)) {
                    gy.move_up(2);
                    manY = manY + 2;
                }
                if (manY >= 590 && manY <= 640 && (manX < -24 || manX > 24)) {
                    gy.move_up(2);
                    manY = manY + 2;
                }

                _left = false;
                _right = false;
                _up = false;
                _down = false;
            }
            //System.out.println(manY + " " + manX);

        }
        /**
         * GODOWN*/
        if (gameTouch.getX() >= 55 && gameTouch.getX() <= 80 && gameTouch.getY() <= 299 && gameTouch.getY() >= 255) {
            if (!flag2) {
                flag1 = false;
                flag2 = true;
                flag3 = false;
                flag4 = false;
                gy.setAnimation(Animation.getDefaultAnimation("res\\drawable\\me.png", 32, 48, 1300));
            }
            if ((((gy.getCenterX() - ghost.getCenterX()) * (gy.getCenterX() - ghost.getCenterX())) +
                    ((gy.getCenterY() - ghost.getCenterY()) * (gy.getCenterY() - ghost.getCenterY())) <= 600) && _down == false) {
                _up = true;
                _left = true;
                _right = true;
                _down = false;
                gy.moveToX(gy.getX(), gy.getY());

            } else {
                // bgd.move_up(2);
                if (iscenter(gy) && manY <= 140) {
                    gy.move_down(2);
                    manY = manY - 2;
                }
                if (iscenter(gy) && manY > 140) {
                    bgd.move_up(2);
                    manY = manY - 2;
                }
                if (!iscenter(gy) && manY > 0) {
                    gy.move_down(2);
                    manY = manY - 2;
                }


                _left = false;
                _right = false;
                _up = false;
                _down = false;

            }

            //System.out.println(((gy.getCenterX()-ghost.getCenterX())*(gy.getCenterX()-ghost.getCenterX()))+
            //        ( (gy.getCenterY()-ghost.getCenterY())*(gy.getCenterY()-ghost.getCenterY())));


        }

        /***
         * GOLEFT*/
        if (gameTouch.getX() >= 10 && gameTouch.getX() <= 55 && gameTouch.getY() <= 255 && gameTouch.getY() >= 220) {
            if (!flag3) {
                flag1 = false;
                flag2 = false;
                flag3 = true;
                flag4 = false;
                gy.setAnimation(Animation.getDefaultAnimation("res\\drawable\\meleft.png", 32, 48, 1300));
            }
            if ((((gy.getCenterX() - ghost.getCenterX()) * (gy.getCenterX() - ghost.getCenterX())) +
                    ((gy.getCenterY() - ghost.getCenterY()) * (gy.getCenterY() - ghost.getCenterY())) <= 600) && _left == false) {
                _up = true;
                _left = false;
                _right = true;
                _down = true;
                gy.moveToX(gy.getX(), gy.getY());

            } else {

                if (manX >= -104 && manY <= 530 && manY > 80) {
                    gy.move_left(2);
                    manX -= 2;

                }

                if ((manX >= -32) && (manY <= 80)) {
                    gy.move_left(2);
                    ;
                    manX -= 2;
                }


                if (((manX <= -104) && (manY <= 530 && manY > 80))) {
                    gy.moveToX(gy.getX(), gy.getY());

                }
                if (manY > 530 && manY <= 640 && manX <= -70) {
                    gy.moveToX(gy.getX(), gy.getY());
                }
                if (manY > 530 && manX > -70 && manX <= 0) {
                    gy.move_left(2);
                    manX -= 2;
                }
                if (manY >= 600 && manX > 0 && manX <= 26) {
                    gy.moveToX(gy.getX(), gy.getY());
                }
                if (manY > 530 && manX > 0 && (manX <= 70) && !(manY >= 600 && manX > 0 && manX <= 26)) {
                    gy.move_left(2);
                    manX -= 2;
                }
                _left = false;
                _right = false;
                _up = false;
                _down = false;
            }
           // System.out.println(manX);
        }
        /**
         * GORIGHT*/
        if (gameTouch.getX() >= 84 && gameTouch.getX() <= 121 && gameTouch.getY() <= 255 && gameTouch.getY() >= 220) {
            System.out.println(manX);
            if (!flag4) {
                flag1 = false;
                flag2 = false;
                flag3 = false;
                flag4 = true;
                gy.setAnimation(Animation.getDefaultAnimation("res\\drawable\\meright.png", 32, 48, 1300));
            }

            if ((((gy.getCenterX() - ghost.getCenterX()) * (gy.getCenterX() - ghost.getCenterX())) +
                    ((gy.getCenterY() - ghost.getCenterY()) * (gy.getCenterY() - ghost.getCenterY())) <= 600) && _right == false) {
                _up = true;
                _left = true;
                _right = false;
                _down = true;
                gy.moveToX(gy.getX(), gy.getY());

            } else {

                if (manX <= 104 && manY <= 530 && manY > 80) {
                    gy.move_right(2);
                    manX += 2;

                }

                if ((manX <= 32) && (manY <= 80)) {
                    gy.move_right(2);
                    ;
                    manX += 2;
                }


                if (((manX >= 104) && (manY < 530 && manY > 80))) {
                    gy.moveToX(gy.getX(), gy.getY());

                }
                if (manY >= 530 && manY <= 640 && manX >= 70) {
                    gy.moveToX(gy.getX(), gy.getY());
                }
                if (manY > 530 && manX < 70 && manX >= 0) {
                    gy.move_right(2);
                    manX += 2;
                }
                if (manY >= 600 && manX < 0 && manX >= -26) {
                    gy.moveToX(gy.getX(), gy.getY());
                }
                if (manY > 530 && manX < 0 && (manX >= -70) && !(manY >= 600 && manX < 0 && manX >= -26)) {
                    gy.move_right(2);
                    manX += 2;
                }
                _left = false;
                _right = false;
                _up = false;
                _down = false;
            }
           // System.out.println("X: " + manX + "Y: " + manY);

        }

    }



    @Override
    public void touchDrag(GameTouch gameTouch) {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void close() {

        this.setClose(true);

    }

    public boolean iscenter(Sprite sprite){
        if(sprite.getY()==130){
            return true;
        }
        else
            return false;
    }

}
