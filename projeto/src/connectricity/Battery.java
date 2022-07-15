package connectricity;

public class Battery extends Conductor{

    private int targetPotential;

    public Battery (int xIndex, int yIndex, Map map, int targetPotential){
		super(xIndex, yIndex, map);
        this.targetPotential = targetPotential;
	}

    public String getName() {
        return "B";
    }

    public boolean rightPotential() {
        return targetPotential == potentialLevel;
    }
}