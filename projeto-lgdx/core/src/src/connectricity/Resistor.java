package src.connectricity;

import com.badlogic.gdx.graphics.Texture;

public class Resistor extends Entity{

    public Resistor (int xIndex, int yIndex, Map map){
        super(xIndex, yIndex, map);
    }

    public String getName() {
        return "R";
    }
}
