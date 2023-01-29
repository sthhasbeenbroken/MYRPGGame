package com.example.myrpggame.sprite;

import loon.LObject;
import loon.LTexture;
import loon.Screen;
import loon.action.ActionTween;
import loon.action.map.Field2D;
import loon.action.sprite.Animation;
import loon.action.sprite.ISprite;
import loon.action.sprite.Sprite;
import loon.action.sprite.Sprites;
import loon.canvas.LColor;
import loon.geom.RectBox;
import loon.opengl.GLEx;

public class man extends Sprite implements ISprite{
    private Animation animation;
    private static String ME = "res\\drawable\\me.png";
    private static int HEIGHT = 44;
    private static int WIDTH = 30;
    public man(){
        new Sprite(ME,WIDTH,HEIGHT);
       this.animation = Animation.getDefaultAnimation("res\\drawable\\me.png", 32,48,1300);
        this.setAnimation(animation);
        this.setLocation(238,270);
    }



}
