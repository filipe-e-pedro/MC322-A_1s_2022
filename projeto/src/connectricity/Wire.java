package connectricity;

public class Wire extends Entity{
    
    private int potentialLevel = 0;
    private int maxPotential = 3;

    public Wire (int xIndex, int yIndex, Map map){
		super(xIndex, yIndex, map);
	}

    public int getPotentialLevel() {
        return potentialLevel;
    }

    public String getName() {
        return "W";
    }

    public void setPotentialLevel(int level) {
        if (level >= 0 && level < maxPotential) {
            potentialLevel = level;
        }
    }
}
