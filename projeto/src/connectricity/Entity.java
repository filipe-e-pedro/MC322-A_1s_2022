package connectricity;

public abstract class Entity {
	protected int xIndex, yIndex;
	/*protected Map mapa; */
	
	public Entity (int xIndex, int yIndex /*,Map mapa*/){
		this.xIndex = xIndex;
		this.yIndex = yIndex;
		// this.mapa = mapa;
	}
	
	public int[] getPosition() {
		int position[] = new int[2];
		position[0] = xIndex;
		position[1] = yIndex;
		return position;
	}

	public abstract String getName();

    public void setPosicao(int xIndex, int yIndex) {
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }
}
