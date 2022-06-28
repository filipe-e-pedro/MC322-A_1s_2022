package com.connectricity;

import com.badlogic.gdx.graphics.Texture;

public abstract class Entity {
    protected int xIndex, yIndex;
    protected Map map;
    protected Texture sprite;

    public Entity (int xIndex, int yIndex, Map map){
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.map = map;
    }

    public int[] getPosition() {
        int position[] = new int[2];
        position[0] = xIndex;
        position[1] = yIndex;
        return position;
    }

    public abstract String getName();

    public void setPosicao(int xIndex, int yIndex) {
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }
}