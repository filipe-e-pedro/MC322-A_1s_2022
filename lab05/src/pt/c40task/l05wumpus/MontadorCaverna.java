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
			for(int i = 0; i < 16; i++) {
				for (int j = 0; j < 4; j++){
				}
				posicao_x = i%4;
				posicao_y = i/4;
				novaSala = new Sala(posicao_x, posicao_y);
				if(cave[i][2].equalsIgnoreCase("W")){
					Wumpus wumpus = new Wumpus(posicao_x, posicao_y, mapa);
					novaSala.setComponente(wumpus);
					contadorWumpus ++;
				}
				else if(cave[i][2].equalsIgnoreCase("O")){
					Ouro ouro = new Ouro(posicao_x, posicao_y, mapa);
					novaSala.setComponente(ouro);
					contadorOuro ++;
				}
				else if(cave[i][2].equalsIgnoreCase("B")){
					Buraco buraco = new Buraco(posicao_x, posicao_y, mapa);
					novaSala.setComponente(buraco);
					contadorBuraco ++;
				}
				else if(cave[i][2].equalsIgnoreCase("P")){
					heroi = new Heroi(posicao_x, posicao_y, mapa);
					novaSala.setComponente(heroi);
					novaSala.revelaSala();
					contadorHeroi++;
				}
				mapa.setSala(posicao_x, posicao_y, novaSala);
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
		for (int i = 0; i < 16; i++) {
			posicao_x = i%4;
			posicao_y = i/4;
			s = mapa.getSala(posicao_x, posicao_y);
			if(s.checaWumpus())
				s.getWumpus().geraFedor();
			if(s.checaBuraco())
				s.getBuraco().geraBrisa();
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
