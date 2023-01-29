package com.example.myrpggame.Screen;

import android.media.MediaPlayer;

import loon.Assets;
import loon.LSystem;
import loon.LTexture;
import loon.Screen;
import loon.action.sprite.Sprite;
import loon.action.sprite.Sprites;
import loon.canvas.LColor;
import loon.component.LMenu;
import loon.component.LMenuSelect;
import loon.component.LTextArea;
import loon.event.GameTouch;
import loon.event.Touched;
import loon.opengl.GLEx;
import loon.utils.timer.LTimer;
import loon.utils.timer.LTimerContext;

public class Fscreen extends Screen {
    private Sprite GHOST=new Sprite("res\\drawable\\deathgod1.png",290,10,120,200),
            GHOST2=new Sprite("res\\drawable\\deadgod2.png",220,20,320,275),
            fightgy = new Sprite("res\\drawable\\mefight.png",80,120,80,80),
            shocked=new Sprite("res\\drawable\\shocked.png",120,120,35,35),
            blood = new Sprite("res\\drawable\\blood.png",0,-50,400,300);
    private LTimer timer = new LTimer(LSystem.SECOND);
    private LTextArea area= new LTextArea(4,0,200,478,102),
            textArea=new  LTextArea(4,0,200,400,102),
            slectArea1=new LTextArea(1,400,200,80,34),
            slectArea2=new LTextArea(1,400,234,80,34),
            slectArea3=new LTextArea(1,400,268,80,34),
            dialog = new LTextArea(1,150,70,200,200),
            dialog2 = new LTextArea(1,160,180,80,50),
            dialog3 = new LTextArea(1,260,180,80,50);

    private LTexture texture= getGameWinFrame(300, 100, LColor.black, LColor.blue, false),
            texture2= getGameWinFrame(300, 100, LColor.black, LColor.blue, false);
    private Sprites sprites = new Sprites(this);
    private  int count=0;
    private boolean runaway=false,flag =false;
    public Fscreen(){
        fightgy.setAlpha(0.7F);
        GHOST2.setVisible(false);
        textArea.setBackground(texture);
        textArea.setSlideMessage(true);
        textArea.setLeftOffset(25);
        textArea.setTopOffset(5);
        slectArea1.setBackground(texture);
        slectArea1.setSlideMessage(true);
        slectArea1.setLeftOffset(5);
        slectArea1.setTopOffset(1);
        slectArea2.setBackground(texture);
        slectArea2.setSlideMessage(true);
        slectArea2.setLeftOffset(5);
        slectArea2.setTopOffset(1);
        slectArea3.setBackground(texture);
        slectArea3.setSlideMessage(true);
        slectArea3.setLeftOffset(5);
        slectArea3.setTopOffset(1);

        dialog.setBackground(texture);
        dialog.setSlideMessage(true);
        dialog.setLeftOffset(25);
        dialog.setTopOffset(50);

        dialog2.setBackground(texture);
        dialog2.setSlideMessage(true);
        dialog2.setLeftOffset(5);
        dialog2.setTopOffset(5);

        dialog3.setBackground(texture);
        dialog3.setSlideMessage(true);
        dialog3.setLeftOffset(5);
        dialog3.setTopOffset(5);
        dialog.put("确定要逃跑吗？");
        dialog2.addString("确定");
        dialog3.addString("取消");

        dialog.setVisible(false);
        dialog2.setVisible(false);
        dialog3.setVisible(false);

        textArea.put("请做出你的选择");
        slectArea1.addString("攻击");
        slectArea2.addString("道具");
        slectArea3.addString("逃跑");
        textArea.setVisible(false);
        slectArea1.setVisible(false);
        slectArea2.setVisible(false);
        slectArea3.setVisible(false);
        area.setBackground(texture);
        //area.setDefaultColor(255, 255, 255);
       area.setShowType(LTextArea.TYPE_DOWN);
       area.setSlideMessage(true);
       area.setLeftOffset(25);
       area.setTopOffset(5);
       shocked.setVisible(false);
       area.put("???:"+"  ", LColor.red);
       blood.setVisible(false);
         sprites.add(GHOST);
         sprites.add(GHOST2);
         sprites.add(fightgy);
         sprites.add(shocked);
         sprites.add(blood);
       // playSound("res\\assets\\anger.mp3",true); failed to start music unknown mistake
        this.add(new secondscreen());





    }


    @Override
    public void draw(GLEx glEx) {
    sprites.createUI(glEx);
    area.createUI(glEx);
    textArea.createUI(glEx);
    slectArea1.createUI(glEx);
    slectArea2.createUI(glEx);
    slectArea3.createUI(glEx);
    dialog.createUI(glEx);
    dialog2.createUI(glEx);
    dialog3.createUI(glEx);

    }

    @Override
    public void onLoad() {
        this.addScreen(new secondscreen());
    }

    @Override
    public void alter(LTimerContext lTimerContext) {
            timer.action(1000);
            GHOST.update(150);

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void touchDown(GameTouch gameTouch) {
    System.out.println(count);
        if(count==7){
         this.runLastScreen();

        }
        if(count==6){
            shocked.setVisible(false);
            fightgy.setVisible(false);
            textArea.setVisible(false);
            slectArea1.setVisible(false);
            slectArea2.setVisible(false);
            slectArea3.setVisible(false);
            blood.setVisible(true);
            count++;
        }
        if(count==5){
            if(gameTouch.getX()>=400&&gameTouch.getX()<=480&&gameTouch.getY()>=200&&gameTouch.getY()<234){
                textArea.put("Oh(⊙o⊙)？你好像没有任何战斗能力");

            }
            if(gameTouch.getX()>=400&&gameTouch.getX()<=480&&gameTouch.getY()>=234&&gameTouch.getY()<268){
                textArea.put("Oh(⊙o⊙)？你没有任何道具");

            }
            if(gameTouch.getX()>=400&&gameTouch.getX()<=480&&gameTouch.getY()>=268&&gameTouch.getY()<302){
                dialog.setVisible(true);
                dialog2.setVisible(true);
                dialog3.setVisible(true);
                flag=true;
            }
            if(gameTouch.getX()>=160&&gameTouch.getX()<=240&&gameTouch.getY()>=180&&gameTouch.getY()<230&&flag){
                runaway=true;
                dialog.setVisible(false);
                dialog2.setVisible(false);
                dialog3.setVisible(false);
                flag=false;
            }
            if(gameTouch.getX()>=260&&gameTouch.getX()<=340&&gameTouch.getY()>=180&&gameTouch.getY()<230&&flag){
                runaway=false;
                dialog.setVisible(false);
                dialog2.setVisible(false);
                dialog3.setVisible(false);
                flag=false;
            }
        }
        if(runaway==true){
            textArea.put("\n");
            textArea.put("???:”懦弱之举“。",LColor.red);
            runaway=false;
            count++;
        }
        if(count==4){
            //area.clear();
            area.setVisible(false);
            textArea.setVisible(true);
            slectArea1.setVisible(true);
            slectArea2.setVisible(true);
            slectArea3.setVisible(true);
            count++;
        }
        if(count==3){
            area.put("”啊啊啊啊干嘛!!“",LColor.white);
            count++;
        }
        if(count==2){
            shocked.setVisible(true);
            area.put("”啊？你说什么???“",LColor.white);
            count++;
        }
        if(count==1){

            area.put("???:”EIDEEEEEEEEEEEEEEE!!!“",LColor.red);
            count++;
        }

        System.out.println(area.getTouchX());
        if(count==0) {

            area.addString("”UoyeRaohw!!!?“", LColor.red);
            count++;

        }

    }

    @Override
    public void touchUp(GameTouch gameTouch) {
        if(count==2) {

            GHOST.setVisible(false);
            GHOST2.setVisible(true);

        }
    }

    @Override
    public void touchMove(GameTouch gameTouch) {

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
}
