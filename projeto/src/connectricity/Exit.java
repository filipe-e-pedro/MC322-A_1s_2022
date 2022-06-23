package connectricity;

public class Exit extends Entity{
    
    private boolean open = false;

    public Exit (int xIndex, int yIndex/*,Map mapa*/){
		super(xIndex, yIndex);
		// this.mapa = mapa;
	}

    public String getName() {
        return "E";
    }

    public boolean getOpen() {
        return open;
    }

    public void open() {
        open = true;
    }

    public void close() {
        open = false;
    }

}
