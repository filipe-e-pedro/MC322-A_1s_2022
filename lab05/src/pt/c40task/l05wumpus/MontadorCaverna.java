package pt.c40task.l05wumpus;

public class MontadorCaverna {
	private Caverna mapa = new Caverna();
	private Toolkit tk;
	private Heroi heroi;
	
	public MontadorCaverna(Toolkit tk) {
		this.tk = tk;
	}
	
	public boolean criaSalas() {
		String cave[][] = tk.retrieveCave();
		int posicao_x, posicao_y;
		int contadorWumpus = 0, contadorOuro = 0, contadorBuraco = 0, contadorHeroi = 0;
		Sala novaSala;
		boolean noError = true;
		if(cave.length != 16){
			System.out.println("Arquivo cave.csv com numero errado de salas");
			noError = false;
		}
		else {
			if(!cave[0][2].equalsIgnoreCase("P")){
				System.out.println("Heroi nao esta na primeira sala");
				noError = false;
			}
			else{
				heroi = new Heroi(0, 0, mapa);
				novaSala = new Sala(0, 0, "P", mapa);
				novaSala.revelaSala();
				mapa.setSala(0, 0, novaSala);
				contadorHeroi++;
			}
			for(int i = contadorHeroi; i < 16; i++) {
				posicao_x = i%4;
				posicao_y = i/4;
				novaSala = new Sala(posicao_x, posicao_y, cave[i][2], mapa);
				mapa.setSala(posicao_x, posicao_y, novaSala);
				if(novaSala.checaBuraco())
					contadorBuraco ++;
				if(novaSala.checaOuro())
					contadorOuro ++;
				if(novaSala.checaWumpus())
					contadorWumpus ++;
				if(novaSala.checaHeroi())
					contadorHeroi ++;
			}
		}
		if(contadorBuraco != 2 && contadorBuraco != 3){
			System.out.println("Deve haver no minimo 2 e no maximo 3 buracos");
			noError = false;
		}
		if(contadorOuro != 1){
			System.out.println("Deve haver exclusivamente 1 ouro");
			noError = false;
		}
		if(contadorWumpus != 1){
			System.out.println("Deve haver exclusivamente 1 Wumpus");
			noError = false;
		}
		if(contadorHeroi != 1){
			System.out.println("Deve haver exclusivamente 1 Heroi");
			noError = false;
		}
		return noError;
	}

	public void geraBrisaFedor() {
		Sala s;
		int posicao_x = 0;
		int posicao_y = 0;
		Componente comp;
		for (int i = 0; i < 16; i++) {
			posicao_x = i%4;
			posicao_y = i/4;
			s = mapa.getSala(posicao_x, posicao_y);
			if (s.compMaisImportante() == "B") {
				if (posicao_x < 3) {
					comp = new Brisa(posicao_x + 1, posicao_y, mapa);
					mapa.getSala(posicao_x + 1, posicao_y).setComponente(comp);
				}
				if (posicao_x > 0) {
					comp = new Brisa(posicao_x - 1, posicao_y, mapa);
					mapa.getSala(posicao_x - 1, posicao_y).setComponente(comp);
				}
				if (posicao_y < 3) {
					comp = new Brisa(posicao_x, posicao_y + 1, mapa);
					mapa.getSala(posicao_x, posicao_y + 1).setComponente(comp);
				}
				if (posicao_y > 0) {
					comp = new Brisa(posicao_x, posicao_y - 1, mapa);
					mapa.getSala(posicao_x, posicao_y - 1).setComponente(comp);
				}
			}
			else if (s.compMaisImportante() == "W") {
				if (posicao_x < 3) {
					comp = new Fedor(posicao_x + 1, posicao_y, mapa);
					mapa.getSala(posicao_x + 1, posicao_y).setComponente(comp);
				}
				if (posicao_x > 0) {
					comp = new Fedor(posicao_x - 1, posicao_y, mapa);
					mapa.getSala(posicao_x - 1, posicao_y).setComponente(comp);
				}
				if (posicao_y < 3) {
					comp = new Fedor(posicao_x, posicao_y + 1, mapa);
					mapa.getSala(posicao_x, posicao_y + 1).setComponente(comp);
				}
				if (posicao_y > 0) {
					comp = new Fedor(posicao_x, posicao_y - 1, mapa);
					mapa.getSala(posicao_x, posicao_y - 1).setComponente(comp);
				}
			}
		}
	}
	
	public Caverna getMapa(){
		return mapa;
	}

	public boolean geraMapa() {
		if (criaSalas()){
			geraBrisaFedor();
			return true;
		}
		return false;
	}

	public Heroi getHeroi(){
		return heroi;
	}
}
