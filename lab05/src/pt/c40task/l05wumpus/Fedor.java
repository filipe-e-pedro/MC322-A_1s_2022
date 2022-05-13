package pt.c40task.l05wumpus;

public class Fedor extends Componente{
    public Fedor(int posicao_x, int posicao_y, Caverna mapa) {
        super(posicao_x, posicao_y, mapa);
    }

    public String getNome() {
        return "f";
    }

    public int getPrioridade() {
        return 2;
    }
}
