package connectricity;

public class Wire extends Entity{
    
    private int currentLevel = 0;
    private int maxCurrent = 3;

    public Wire (int xIndex, int yIndex, Map map){
		super(xIndex, yIndex, map);
	}

    public int getCurrentLevel() {
        return currentLevel;
    }

    public String getName() {
        return "W";
    }

    public void setCurrentLevel(int level) {
        if (level >= 0 && level < maxCurrent) {
            currentLevel = level;
        }
    }
}
