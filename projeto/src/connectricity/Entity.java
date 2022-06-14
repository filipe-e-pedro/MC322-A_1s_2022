package connectricity;

public abstract class Entity {
	protected int posicao_x, posicao_y;
	protected Map mapa;
	
	public Entity (int posicao_x, int posicao_y, Map mapa){
		this.posicao_x = posicao_x;
		this.posicao_y = posicao_y;
		this.mapa = mapa;
	}
	
	public int[] getPosicao() {
		int posicao[] = new int[2];
		posicao[0] = posicao_x;
		posicao[1] = posicao_y;
		return posicao;
	}

    public void setPosicao(int posicao_x, int posicao_y) {
        this.posicao_x = posicao_x;
        this.posicao_y = posicao_y;
    }

    public abstract String getName();
}
