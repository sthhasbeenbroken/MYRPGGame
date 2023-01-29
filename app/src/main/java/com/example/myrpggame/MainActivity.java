package com.example.myrpggame;

import com.example.myrpggame.Screen.Fscreen;
import com.example.myrpggame.Screen.Gscreen;
import com.example.myrpggame.Screen.secondscreen;

import loon.Screen;
import loon.Stage;
import loon.android.AndroidGame;
import loon.android.Loon;
import loon.canvas.Image;
import loon.canvas.LColor;
import loon.component.LTextArea;
import loon.event.GameTouch;
import loon.event.Touched;

public class MainActivity extends Loon {

    /*STAGE*/
    final public static class gameScreen extends Stage {
        private LTextArea area;
        @Override
        public void create() {
            /*纹理区域*/
             area = new LTextArea(30, 36, 400, 250);
            //area.setDefaultColor(0, 255, 255);
            area.put("START",LColor.white);
            area.setLeftOffset(160);
            area.setTopOffset(180);
            Image img = Image.createImage("res\\drawable\\axl.png");
           area.setBackground(img.texture());

            add(area);
            this.addScreen(new Gscreen());
        }
        @Override
      public void  touchDown(GameTouch var1) {
                this.close();

              runLastScreen();
        }

    }



    @Override
    public void onMain() {
    AndroidGame.AndroidSetting setting = new AndroidGame.AndroidSetting();
        setting.isFPS = true;
        setting.isMemory  = false;
        setting.isLogo = false;
        setting.fullscreen = true;
        setting.width = 480;
        setting.height = 320;
        //不允许配置文件改变屏幕方向
        setting.useOrientation = false;
        //若启动此模式，则画面等比压缩，不会失真
        setting.useRatioScaleFactor = false;
        //强制一个显示大小(在android模式下，不填则默认全屏，此模式可能会造成画面失真)
       // setting.width_zoom = getContainerWidth();
       // setting.height_zoom = getContainerHeight();
       // 屏幕显示模式
       // setting.showMode = AndroidGame.LMode.FitFill;

        setting.fps = 60;
        setting.fontName = "Dialog";

        setting.emulateTouch = false;
        setting.lockBackDestroy = false;

        register(setting, new Data() {

            @Override
            public Screen onScreen() {
              return new gameScreen();
            }
        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}