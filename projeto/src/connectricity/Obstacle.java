package connectricity;

public class Obstacle extends Entity{

    public Obstacle (int xIndex, int yIndex/*,Map mapa*/){
		super(xIndex, yIndex);
		// this.mapa = mapa;
	}
    
    public String getName() {
        return "W";
    }
}