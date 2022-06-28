package com.connectricity;

import com.badlogic.gdx.graphics.Texture;

public class Resistor extends Entity{

    public Resistor (int xIndex, int yIndex, Map map){
        super(xIndex, yIndex, map);
        this.sprite = new Texture("resistor.png");
    }

    public String getName() {
        return "R";
    }
}
