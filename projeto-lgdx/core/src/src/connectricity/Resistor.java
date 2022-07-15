package src.connectricity;

public class Resistor extends Entity{
/**
 * Classe que representa o resistor
 */
    public Resistor (int xIndex, int yIndex, Map map){
		super(xIndex, yIndex, map);
	}

    public String getName() {
        return "R";
    }
}
