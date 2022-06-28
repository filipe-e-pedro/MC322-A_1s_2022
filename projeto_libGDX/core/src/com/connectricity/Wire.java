package com.connectricity;

import com.badlogic.gdx.graphics.Texture;

public class Wire extends Conductor{

    public Wire (int xIndex, int yIndex, Map map){
        super(xIndex, yIndex, map);
        this.sprite = new Texture("wire.png");
    }

    public String getName() {
        return "W";
    }

}