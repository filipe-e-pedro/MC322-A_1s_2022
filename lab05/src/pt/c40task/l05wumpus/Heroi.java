package pt.c40task.l05wumpus;

public class Heroi extends Componente{
	public Heroi(int posicao_x, int posicao_y) {
		super(posicao_x, posicao_y);
	}
	
	public void setPosicao(int posicao_x, int posicao_y) {
		this.posicao_x = posicao_x;
		this.posicao_y = posicao_y;
	}
}	
