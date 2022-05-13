package pt.c40task.l05wumpus;

public class Wumpus extends Componente{
	public Wumpus(int posicao_x, int posicao_y, Caverna mapa) {
        super(posicao_x, posicao_y, mapa);
    }
    public String getNome() {
        return "W";
    }
    public int getPrioridade() {
        return 0;
    }
}
