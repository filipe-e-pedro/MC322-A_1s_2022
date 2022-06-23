package connectricity;

public class Player extends Entity{

    public Player(int xIndex, int yIndex, Map map){
		super(xIndex, yIndex, map);
    }

    public String getName() {
        return "P";
    }
	
	public void setPosition(int xIndex, int yIndex) {
		this.xIndex = xIndex;
		this.yIndex = yIndex;
	}

}