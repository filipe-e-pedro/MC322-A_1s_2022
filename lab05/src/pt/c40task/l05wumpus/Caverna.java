package pt.c40task.l05wumpus;

public class Caverna {
	private Sala salas[][] = new Sala[4][4];
	
	public void setSala(int posicao_x, int posicao_y, Sala novaSala) {
		this.salas[posicao_x][posicao_y] = novaSala;
	}

	public Sala getSala(int posicao_x, int posicao_y) {
		return salas[posicao_x][posicao_y];
	}
}
