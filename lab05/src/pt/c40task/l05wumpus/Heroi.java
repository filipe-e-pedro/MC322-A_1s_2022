package pt.c40task.l05wumpus;

public class Heroi extends Componente{
	public Heroi(int posicao_x, int posicao_y, Caverna mapa) {
        super(posicao_x, posicao_y, mapa);
    }
	
	public void setPosicao(int posicao_x, int posicao_y) {
		this.posicao_x = posicao_x;
		this.posicao_y = posicao_y;
	}
	public String getNome() {
        return "P";
    }
	public int getPrioridade() {
        return 1;
    }
}	
