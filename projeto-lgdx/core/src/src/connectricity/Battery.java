package src.connectricity;

public class Battery extends Conductor{

    private int targetPotential;
    private int ID;

    public Battery (int xIndex, int yIndex, Map map, int targetPotential){
		super(xIndex, yIndex, map);
        this.targetPotential = targetPotential;
	}

    public String getName() {
        return "B";
    }

    public void setID(int ID){ this.ID = ID; }

    public int getID(){ return ID; }

    public boolean rightPotential() {
        return targetPotential == potentialLevel;
    }
}