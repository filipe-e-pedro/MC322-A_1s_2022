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
				novaSala = new Sala(posicao_x, posicao_y, cave[i][2]);
			}
		}
	}
	
	public Caverna geraMapa() {
		return mapa;
	}
}
