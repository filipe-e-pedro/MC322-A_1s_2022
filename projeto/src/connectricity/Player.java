package connectricity;

public class Player extends Entity{

    public Player(int xIndex, int yIndex) {
        super(xIndex, yIndex);
    }

    public String getName() {
        return "P";
    }
	
	public void setPosition(int xIndex, int yIndex) {
		this.xIndex = xIndex;
		this.yIndex = yIndex;
	}

}