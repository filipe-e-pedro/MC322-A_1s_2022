package connectricity;

public class Square {
    private Battery battery = null;
    private Exit exit = null;
    private Generator generator = null;
    private Obstacle obstacle = null;
	private Player player = null;
    private Wire wire = null;
	private int xIndex, yIndex;
	private boolean light = false;
	
	public Square (int xIndex, int yIndex){
		this.xIndex = xIndex;
		this.yIndex = yIndex;
	}

    public int[] getPosition() {
		int position[] = new int[2];
		position[0] = xIndex;
		position[1] = yIndex;
		return position;
	}

	public Battery getBattery(){
		return battery;
	}

    public Exit getExit(){
        return exit;
    }

    public Generator getGenerator(){
        return generator;
    }

	public Obstacle getObstacle(){
		return obstacle;
	}

    public Player getPlayer() {
        return player;
    }

    public Wire getWire() {
        return wire;
    }

    public boolean getLight() {
        return light;
    }

    public void setEntity(Battery battery) {
		this.battery = battery;
	}

	public void setEntity(Exit exit) {
		this.exit = exit;
	}

	public void setEntity(Generator generator) {
		this.generator = generator;
	}

	public void setEntity(Obstacle obstacle) {
		this.obstacle = obstacle;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

    public void removePlayer() {
        this.player = null;
    }

    public void setWire(Wire wire) {
        this.wire = wire;
    }

    public void removeWire() {
        this.wire = null;
    }

    public void setLight(boolean light) {
		this.light = light;
	}

    public boolean checkBattery(){
		return (battery!=null) ? true : false;
	}

	public boolean checkExit(){
		return (exit!=null) ? true : false;
	}

	public boolean checkGenerator(){
		return (generator!=null) ? true : false;
	}

	public boolean checkObstacle(){
		return (obstacle!=null) ? true : false;
	}

	public boolean checkPlayer(){
		return (player!=null) ? true : false;
	}

    public boolean checkWire(){
		return (wire!=null) ? true : false;
	}
}
