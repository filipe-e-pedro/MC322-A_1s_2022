package src.connectricity;

import com.badlogic.gdx.graphics.Texture;

public class Generator extends Conductor{

    public Generator (int xIndex, int yIndex, Map map){
        super(xIndex, yIndex, map);
    }

    public String getName() {
        return "G";
    }
}
