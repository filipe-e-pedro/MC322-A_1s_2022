package connectricity;

public class Square {
    private Battery battery = null;
    private Exit exit = null;
    private Generator generator = null;
    private Obstacle obstacle = null;
	private Player player = null;
    private Wire wire = null;
	private Resistor resistor = null;
	private int xIndex, yIndex;
	private boolean light = true;
	
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

	public Resistor getResistor() {
		return resistor;
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

	public void setEntity(Player player) {
		this.player = player;
	}

    public void removePlayer() {
        this.player = null;
    }

    public void setEntity(Wire wire) {
        this.wire = wire;
    }

    public Wire removeWire() {
		Wire returnWire = wire;
    	wire = null;
		return returnWire;
    }

	public void setEntity(Resistor resistor) {
        this.resistor = resistor;
    }

	public Resistor removeResistor() {
		Resistor returnResistor = resistor;
    	resistor = null;
		return returnResistor;
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

	public boolean checkResistor(){
		return (resistor!=null) ? true : false;
	}

	public boolean emptySquare() {
		return !(checkBattery() || checkExit() || checkGenerator() || checkObstacle() || checkWire() || checkResistor());
	}

	public String mostRelevantEntity() { 
		// GAMBIARRA, MUDAR DEPOIS
		if (checkObstacle()) {
			return "O";
		}

		if (checkExit()) {
			return "E";
		}

		if (checkPlayer()) {
			return "P";
		}

		if (checkGenerator()) {
			return "G";
		}

		if (checkBattery()) {
			return "B";
		}

		if (checkWire()) {
			return "W";
		}

		if(checkResistor()) {
			return "R";
		}

		return "-";
	}
}
