package src.connectricity;

public class Exit extends Entity{
    
    private boolean open = false;

    public Exit (int xIndex, int yIndex, Map map){
		super(xIndex, yIndex, map);
	}

    public String getName() {
        return "E";
    }

    public boolean getOpen() {
        return open;
    }

    public void setOpen(boolean state) {
        open = state;
    }

}
