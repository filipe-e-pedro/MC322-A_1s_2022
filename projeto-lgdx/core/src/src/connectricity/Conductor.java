package src.connectricity;

public abstract class Conductor extends Entity{

    protected int potentialLevel = 0;
    protected int maxPotential = 3;

    public Conductor (int xIndex, int yIndex, Map map){
		super(xIndex, yIndex, map);
	}

    public int getPotentialLevel() {
        return potentialLevel;
    }

    public String getPotential() {
        return Integer.toString(potentialLevel);
    }

    public String getName() {
        return "C";
    }

    public void setPotentialLevel(int level) {
        if (level >= 0 && level <= maxPotential) {
            potentialLevel = level;
        }
    }
}
