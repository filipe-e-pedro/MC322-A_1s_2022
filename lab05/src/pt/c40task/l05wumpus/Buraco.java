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

    public void geraBrisa(){
        if (posicao_x < 3) {
            Brisa brisa = new Brisa(posicao_x + 1, posicao_y, mapa);
            mapa.getSala(posicao_x + 1, posicao_y).setComponente(brisa);
        }
        if (posicao_x > 0) {
            Brisa brisa = new Brisa(posicao_x - 1, posicao_y, mapa);
            mapa.getSala(posicao_x - 1, posicao_y).setComponente(brisa);
        }
        if (posicao_y < 3) {
            Brisa brisa = new Brisa(posicao_x, posicao_y + 1, mapa);
            mapa.getSala(posicao_x, posicao_y + 1).setComponente(brisa);
        }
        if (posicao_y > 0) {
            Brisa brisa = new Brisa(posicao_x, posicao_y - 1, mapa);
            mapa.getSala(posicao_x, posicao_y - 1).setComponente(brisa);
        }
    }
}

