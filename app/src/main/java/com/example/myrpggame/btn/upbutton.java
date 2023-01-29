package com.example.myrpggame.btn;

import loon.LSystem;
import loon.LTexture;
import loon.action.sprite.Sprite;
import loon.canvas.LColor;
import loon.component.LButton;
import loon.event.ActionKey;
import loon.event.CallFunction;
import loon.font.IFont;

public class upbutton extends LButton {
    private Sprite sprite;
    public upbutton(String s, String s1, LColor lColor, int i, int i1,int i2,int i3) {
        super(s, s1, lColor, i, i1,i2,i3);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }


}

