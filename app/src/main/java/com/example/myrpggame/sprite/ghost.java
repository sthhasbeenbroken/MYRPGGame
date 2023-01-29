package com.example.myrpggame.sprite;

import loon.action.sprite.Animation;
import loon.action.sprite.ISprite;
import loon.action.sprite.Sprite;
import loon.geom.RectBox;

public class ghost  extends Sprite implements ISprite {
    private Animation animation;
    private static String GHOST = "res\\drawable\\deadgoddown.png";
    private static int HEIGHT = 45;
    private static int WIDTH = 38;

    public ghost() {

        new Sprite(GHOST,100,100,WIDTH,HEIGHT);
        this.animation = Animation.getDefaultAnimation("res\\drawable\\deadgoddown.png", 36,45,1000);
        this.setAnimation(animation);
        this.setLocation(234,52);
        this.setVisible(false);
       // this.setAlpha(0.2F);
    }

}
