package src.connectricity;

 public class Generator extends Conductor{
 /**
  * Classe que representa Generator
  */
    public Generator (int xIndex, int yIndex, Map map){
		super(xIndex, yIndex, map);
	}

    public String getName() {
        return "G";
    }
}