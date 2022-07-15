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

    public void geraFedor(){
        if (posicao_x < 3) {
            Fedor fedor = new Fedor(posicao_x + 1, posicao_y, mapa);
            mapa.getSala(posicao_x + 1, posicao_y).setComponente(fedor);
        }
        if (posicao_x > 0) {
            Fedor fedor = new Fedor(posicao_x - 1, posicao_y, mapa);
            mapa.getSala(posicao_x - 1, posicao_y).setComponente(fedor);
        }
        if (posicao_y < 3) {
            Fedor fedor = new Fedor(posicao_x, posicao_y + 1, mapa);
            mapa.getSala(posicao_x, posicao_y + 1).setComponente(fedor);
        }
        if (posicao_y > 0) {
            Fedor fedor = new Fedor(posicao_x, posicao_y - 1, mapa);
            mapa.getSala(posicao_x, posicao_y - 1).setComponente(fedor);
        }
    }
}
