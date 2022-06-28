package com.connectricity;

import com.badlogic.gdx.graphics.Texture;

public class Obstacle extends Entity{

    public Obstacle (int xIndex, int yIndex, Map map){
        super(xIndex, yIndex, map);
        this.sprite = new Texture("box1.png");
    }

    public String getName() {
        return "W";
    }
}