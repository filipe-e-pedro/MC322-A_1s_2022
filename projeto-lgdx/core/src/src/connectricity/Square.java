package src.connectricity;

public class Square {
/**
 * Classe que representa cada celula do espaco celular do jogo
 */
    private Battery battery = null;
    private Exit exit = null;
    private Generator generator = null;
    private Obstacle obstacle = null;
	private Player player = null;
    private Wire wire = null;
	private Resistor resistor = null;
	private int xIndex, yIndex;
	
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

	public Conductor getConductor() {
		if (checkGenerator()) {
			return generator;
		}
		if (checkBattery()) {
			return battery;
		}
		if (checkWire()) {
			return wire;
		}
		return null;
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
		if (checkObstacle()) {
			return obstacle.getName();
		}

		if (checkExit()) {
			return exit.getName();
		}

		if (checkPlayer()) {
			return player.getName();
		}

		if (checkGenerator()) {
			return generator.getName();
		}

		if (checkBattery()) {
			return battery.getName();
		}

		if (checkWire()) {
			return wire.getName()+wire.getPotentialLevel();
		}

		if(checkResistor()) {
			return resistor.getName();
		}

		return "-";
	}

	public String circuitPart() {

		if (checkGenerator()) {
			return generator.getName();
		}

		if (checkBattery()) {
			return battery.getName();
		}

		if (checkWire()) {
			return wire.getName();
		}

		if(checkResistor()) {
			return resistor.getName();
		}
		return "-";
	}
}
