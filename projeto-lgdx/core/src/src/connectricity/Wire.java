package src.connectricity;

public class Wire extends Conductor{
/**
 * Classe que representa o fio
 */
    public Wire (int xIndex, int yIndex, Map map){
		super(xIndex, yIndex, map);
	}

    public String getName() {
        return "W";
    }

}
