package connectricity;

import connectricity.Entity;

public class Generator extends Entity{

    public Generator (int xIndex, int yIndex/*,Map mapa*/){
		super(xIndex, yIndex);
		// this.mapa = mapa;
	}

    public String getName() {
        return "G";
    }
}