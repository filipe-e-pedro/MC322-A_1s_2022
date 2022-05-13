package pt.c40task.l05wumpus;

public class Brisa extends Componente{
	public Brisa(int posicao_x, int posicao_y, Caverna mapa) {
        super(posicao_x, posicao_y, mapa);
    }

    public String getNome() {
        return "b";
    }

    public int getPrioridade() {
        return 3;
    }
}

