package connectricity;

public abstract class Entity {
	protected int xIndex, yIndex;
	/*protected Map mapa; */
	
	public Entity (int xIndex, int yIndex /*,Map mapa*/){
		this.xIndex = xIndex;
		this.yIndex = yIndex;
		// this.mapa = mapa;
	}
	
	public int[] getPosicao() {
		int posicao[] = new int[2];
		posicao[0] = xIndex;
		posicao[1] = yIndex;
		return posicao;
	}

    public void setPosicao(int xIndex, int yIndex) {
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }

    public abstract String getName();
}
