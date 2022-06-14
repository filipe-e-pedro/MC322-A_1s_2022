package connectricity;

public class Wire extends Entity{
    
    private int current = 0;

    public Wire (int xIndex, int yIndex/*,Map mapa*/){
		super(xIndex, yIndex);
		// this.mapa = mapa;
	}

    public int getCurrent() {
        return current;
    }

    public String getName() {
        return "W";
    }
}
