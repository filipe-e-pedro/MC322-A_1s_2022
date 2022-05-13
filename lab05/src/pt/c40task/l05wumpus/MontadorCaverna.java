package pt.c40task.l05wumpus;

public class MontadorCaverna {
	private Caverna mapa = new Caverna();
	private Toolkit tk;
	
	public MontadorCaverna(Toolkit tk) {
		this.tk = tk;
	}
	
	public void criaSalas() {
		String cave[][] = tk.retrieveCave();
		int posicao_x, posicao_y;
		Sala novaSala;
		if(cave.length != 16)
			System.out.println("Arquivo cave.csv com numero errado de salas");
		else {
			for(int i = 0; i < 16; i++) {
				posicao_x = i/4;
				posicao_y = i%4;
				novaSala = new Sala(posicao_x, posicao_y, cave[i][2], mapa);
				mapa.setSala(posicao_x, posicao_y, novaSala);
			}
		}
	}

	public void geraBrisaFedor() {
		Sala s;
		int posicao_x = 0;
		int posicao_y = 0;
		Componente comp;
		for (int i = 0; i < 16; i++) {
			posicao_x = i/4;
			posicao_y = i%4;
			s = mapa.getSala(i/4, i%4);
			if (s.compMaisImportante().getNome() == "B") {
				if (posicao_x < 3) {
					comp = new Brisa(posicao_x + 1, posicao_y, mapa);
					mapa.getSala(posicao_x + 1, posicao_y).setComponente(comp);
				}
				if (posicao_x > 0) {
					comp = new Brisa(posicao_x - 1, posicao_y, mapa);
					mapa.getSala(posicao_x - 1, posicao_y).setComponente(comp);
				}
				if (posicao_y < 3) {
					comp = new Brisa(posicao_y + 1, posicao_y, mapa);
					mapa.getSala(posicao_y + 1, posicao_y).setComponente(comp);
				}
				if (posicao_y > 0) {
					comp = new Brisa(posicao_y - 1, posicao_y, mapa);
					mapa.getSala(posicao_y - 1, posicao_y).setComponente(comp);
				}
			}
			if (s.compMaisImportante().getNome() == "W") {
				if (posicao_x < 3) {
					comp = new Fedor(posicao_x + 1, posicao_y, mapa);
					mapa.getSala(posicao_x + 1, posicao_y).setComponente(comp);
				}
				if (posicao_x > 0) {
					comp = new Fedor(posicao_x - 1, posicao_y, mapa);
					mapa.getSala(posicao_x - 1, posicao_y).setComponente(comp);
				}
				if (posicao_y < 3) {
					comp = new Fedor(posicao_y + 1, posicao_y, mapa);
					mapa.getSala(posicao_y + 1, posicao_y).setComponente(comp);
				}
				if (posicao_y > 0) {
					comp = new Fedor(posicao_y - 1, posicao_y, mapa);
					mapa.getSala(posicao_y - 1, posicao_y).setComponente(comp);
				}
			}
		}
	}
	
	public Caverna geraMapa() {
		criaSalas();
		geraBrisaFedor();
		return mapa;
	}
}
