package connectricity;

public class Battery extends Entity{

    private int chargeLevel = 0;

    public Battery (int xIndex, int yIndex/*,Map mapa*/){
		super(xIndex, yIndex);
		// this.mapa = mapa;
	}

    public String getName() {
        return "B";
    }

    public int getChargeLevel() {
        return chargeLevel;
    }

    public void setChargeLevel(int level) {
        if (level >= 0 && level < 3) {
            chargeLevel = level;
        }
    }
}