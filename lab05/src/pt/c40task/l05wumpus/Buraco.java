package pt.c40task.l05wumpus;

public class Buraco extends Componente{
	public Buraco(int posicao_x, int posicao_y, Caverna mapa) {
        super(posicao_x, posicao_y, mapa);
    }
    public String getNome() {
        return "B";
    }
    public int getPrioridade() {
        return 0;
    }
}

