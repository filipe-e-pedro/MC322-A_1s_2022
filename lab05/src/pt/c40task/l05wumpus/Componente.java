package pt.c40task.l05wumpus;

public abstract class Componente {
	protected int posicao_x, posicao_y;
	protected Caverna mapa;
	
	public Componente(int posicao_x, int posicao_y, Caverna mapa){
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

	public abstract int getPrioridade();

	public abstract String getNome();
}
