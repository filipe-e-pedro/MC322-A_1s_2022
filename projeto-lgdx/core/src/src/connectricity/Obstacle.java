package src.connectricity;

public class Obstacle extends Entity{
/**
 * Classe que representa o obstaculo
 */
    public Obstacle (int xIndex, int yIndex, Map map){
		super(xIndex, yIndex, map);
	}
    
    public String getName() {
        return "O";
    }
}