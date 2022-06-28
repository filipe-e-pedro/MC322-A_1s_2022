package com.connectricity;

import com.badlogic.gdx.graphics.Texture;

public class Generator extends Conductor{

    public Generator (int xIndex, int yIndex, Map map){
        super(xIndex, yIndex, map);
        this.sprite = new Texture("generator.png");
    }

    public String getName() {
        return "G";
    }
}
