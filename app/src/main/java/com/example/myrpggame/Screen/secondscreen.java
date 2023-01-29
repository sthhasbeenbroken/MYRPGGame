package com.example.myrpggame.Screen;

import com.example.myrpggame.btn.downbtn;
import com.example.myrpggame.btn.leftbtn;
import com.example.myrpggame.btn.rightbtn;
import com.example.myrpggame.btn.upbutton;
import com.example.myrpggame.sprite.ghost;
import com.example.myrpggame.sprite.man;

import loon.LSystem;
import loon.LTexture;
import loon.Screen;
import loon.action.sprite.Sprite;
import loon.action.sprite.Sprites;
import loon.canvas.LColor;
import loon.component.LTextArea;
import loon.event.GameTouch;
import loon.opengl.GLEx;
import loon.utils.timer.LTimer;
import loon.utils.timer.LTimerContext;

public class secondscreen extends Screen {
    private Sprite ghost=new ghost(), gy=new man(),
            blood = new Sprite("res\\drawable\\blood.png",0,-50,400,300),
            shocked=new Sprite("res\\drawable\\shocked.png",230,140,35,35);
    private com.example.myrpggame.btn.downbtn downbtn;
    private com.example.myrpggame.btn.leftbtn leftbtn;
    private com.example.myrpggame.btn.rightbtn rightbtn;
    private upbutton upbtn;
    private Sprites sprmgr = new Sprites(this);
    private LTextArea area,textArea,
    backpack = new LTextArea(10,5,5,60,30);
    private LTexture texture;
    private int clickcount;
    LTimer timer = new LTimer(LSystem.SECOND);
    public secondscreen(){

        shocked.setVisible(false);
        clickcount=0;
        texture = getGameWinFrame(478, 100, LColor.black, LColor.blue, false);
        textArea = new LTextArea(4,0,220,478,100);
        textArea.setBackground(texture);
        textArea .setSlideMessage(true);
        textArea.setShowType(LTextArea.TYPE_DOWN);
        textArea .setLeftOffset(25);
        textArea .setTopOffset(5);
        textArea .put("啊??我不是死了吗?", LColor.white);
        gy.setLocation(220,170);
        blood.setVisible(true);
        sprmgr.add(ghost);
        sprmgr.add(gy);
        sprmgr.add(blood);
        sprmgr.add(shocked);
        backpack.setBackground(texture);
        backpack.setSlideMessage(true);
       // backpack.setLeftOffset(5);
        //backpack.setTopOffset(5);
        backpack.put("背包");
        backpack.setVisible(false);
    }

    @Override
    public void draw(GLEx glEx) {
        upbtn = new upbutton("res\\drawable\\upbtn.png","", LColor.blue,38,44,50,180);
        downbtn = new downbtn("res\\drawable\\downbtn.png","", LColor.blue,38,44,49,255);
        leftbtn = new leftbtn("res\\drawable\\leftbtn.png","", LColor.blue,44,38,10,220);
        rightbtn = new rightbtn("res\\drawable\\rightbtn.png","", LColor.blue,44,38,82,220);
        upbtn.createUI(glEx);
        downbtn.createUI(glEx);
        leftbtn.createUI(glEx);
        rightbtn.createUI(glEx);
        upbtn.setVisible(false);
        downbtn.setVisible(false);
        leftbtn.setVisible(false);
        rightbtn.setVisible(false);
        sprmgr.createUI(glEx);
        textArea.createUI(glEx);
        backpack.createUI(glEx);

    }

    @Override
    public void onLoad() {

    }

    @Override
    public void alter(LTimerContext lTimerContext) {
        timer.action(1000);
        sprmgr.update(150);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void touchDown(GameTouch gameTouch) {
        if ((clickcount==13)){
            textArea.setVisible(false);
            upbtn.setVisible(true);
            downbtn.setVisible(true);
            leftbtn.setVisible(true);
            rightbtn.setVisible(true);
            clickcount++;
        }
        if ((clickcount==12)){
            textArea.put("啊？喂喂喂？人呢？",LColor.white);
            clickcount++;
        }
        if(clickcount==11){
            ghost.setVisible(false);
            clickcount++;
        }
        if(clickcount==10){
            textArea.put("去找寻你的死因再回来吧!bye~",LColor.red);
            clickcount++;
        }
        if(clickcount==9){
            textArea.put("你就可以进行攻击",LColor.red);
            textArea.put("在你获得武器后",LColor.red);
            clickcount++;
        }
        if (clickcount==8){
            textArea.put("这样你就可以查看你拥有的物品",LColor.red);
            textArea.put("我先为你添加了背包",LColor.red);
            backpack.setVisible(true);
            clickcount++;
        }

        if(clickcount==7){
            textArea.put("这就有点麻烦了",LColor.red);
            textArea.put("是的~看样子你好像不明白自己的死亡原因。",LColor.red);

            clickcount++;
        }
        if(clickcount==6){
            shocked.setVisible(false);
            textArea.put("啊？什么意思？我原本就死了吗？",LColor.white);
            clickcount++;
        }
        if(clickcount==5){
            blood.setVisible(false);
            clickcount++;
        }
        if(clickcount==4){
            blood.setAlpha(0.5F);
            clickcount++;
        }
        if(clickcount==3){
            textArea.put("刚刚和你开了一个玩笑，屏幕有点脏我先帮你擦擦。",LColor.red);
            clickcount++;
        }
        if(clickcount==2){
            textArea.put("这里就是地狱~明白了吗？我是死神~",LColor.red);
            clickcount++;
        }
        if(clickcount==1){
            shocked.setVisible(true);
            textArea.put("诶？什么意思,你又是谁！",LColor.white);
            clickcount++;
        }
        if(clickcount==0){
            ghost.setVisible(true);
            textArea.put("没错~但你本来就死了。",LColor.red);
            clickcount++;
        }
    }

    @Override
    public void touchUp(GameTouch gameTouch) {

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

    }
}
