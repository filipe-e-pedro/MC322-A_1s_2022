package connectricity;

public class Generator extends Entity{

    public Generator (int xIndex, int yIndex, Map map){
		super(xIndex, yIndex, map);
	}

    public String getName() {
        return "G";
    }
}