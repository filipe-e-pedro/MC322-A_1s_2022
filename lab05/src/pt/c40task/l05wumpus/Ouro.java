package pt.c40task.l05wumpus;

public class Ouro extends Componente{
	public Ouro(int posicao_x, int posicao_y, Caverna mapa) {
        super(posicao_x, posicao_y, mapa);
    }
    public String getNome() {
        return "O";
    }
    public int getPrioridade() {
        return 0;
    }
}