package connectricity;

public class Battery extends Conductor{

    public Battery (int xIndex, int yIndex, Map map){
		super(xIndex, yIndex, map);
	}

    public String getName() {
        return "B";
    }
}