package src.connectricity;

public class Wire extends Conductor{

    public Wire (int xIndex, int yIndex, Map map){
		super(xIndex, yIndex, map);
	}

    public String getName() {
        return "W";
    }

}
