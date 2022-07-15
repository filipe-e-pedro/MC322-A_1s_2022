package src.connectricity;

public abstract class Entity {
/**
 * Classe abstrata que engloba das entidades do jogo
 */
	protected int xIndex, yIndex;
	protected Map map;

	public Entity (int xIndex, int yIndex, Map map){
	/**
	 * Construtor que que coloca os valores da posicao e do mapa
	 * em que a entidade esta contida nos seus respectivos atributos
	 */
		this.xIndex = xIndex;
		this.yIndex = yIndex;
		this.map = map;
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
