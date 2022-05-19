package pt.c40task.l05wumpus;

public class Sala {
	private Wumpus wumpus = null;
	private Ouro ouro = null;
	private Buraco buraco = null;
	private Heroi heroi = null;
	private Brisa brisa = null;
	private Fedor fedor = null;
	private String[] componentesNome = new String[5];
	private int posicao_x, posicao_y;
	private boolean revelada = false;
	
	public Sala(int posicao_x, int posicao_y){
		this.posicao_x = posicao_x;
		this.posicao_y = posicao_y;
		for(int i = 0; i < 4; i++)
			componentesNome[i]=null;
		componentesNome[4] = "#";
	}

	public String compMaisImportante() {
		for (int i = 0; i < 4; i++) {
			if (componentesNome[i] != null) {
				return componentesNome[i];
			}
		}
		return componentesNome[4];
	}

	public void revelaSala() {
		revelada = true;
	}

	public boolean foiVisitada() {
		return revelada;
	}
	
	public void setComponente(Wumpus wumpus) {
		this.wumpus = wumpus;
		componentesNome[0] = "W";
	}

	public void setComponente(Ouro ouro) {
		this.ouro = ouro;
		componentesNome[0] = "O";
	}

	public void setComponente(Buraco buraco) {
		this.buraco = buraco;
		componentesNome[0] = "B";
	}

	public void setComponente(Heroi heroi) {
		this.heroi = heroi;
		componentesNome[1] = "P";
	}

	public void setComponente(Fedor fedor) {
		this.fedor = fedor;
		componentesNome[2] = "f";
	}

	public void setComponente(Brisa brisa) {
		this.brisa = brisa;;
		componentesNome[3] = "b";
	}


	public boolean checaBuraco(){
		return (buraco!=null) ? true : false;
	}

	public boolean checaWumpus(){
		return (wumpus!=null) ? true : false;
	}

	public boolean checaHeroi(){
		return (heroi!=null) ? true : false;
	}

	public boolean checaOuro(){
		return (ouro!=null) ? true : false;
	}

	public boolean checaBrisa(){
		return (brisa!=null) ? true : false;
	}

	public boolean checaFedor(){
		return (fedor!=null) ? true : false;
	}

	public void removeOuro(){
		ouro = null;
		componentesNome[0] = null;
	}

	public void removeHeroi() {
		heroi = null;
		componentesNome[1] = null;
	}

	public void adicionaHeroi(Heroi heroi) {
		this.heroi = heroi;
		componentesNome[1] = "P";
	}
	
	public int[] getPosicao() {
		int posicao[] = new int[2];
		posicao[0] = posicao_x;
		posicao[1] = posicao_y;
		return posicao;
	}

	public Wumpus getWumpus(){
		return wumpus;
	}

	public Buraco getBuraco(){
		return buraco;
	}

	public void removeWumpus() {
		wumpus = null;
		componentesNome[0] = null;
	}

	public String mensagemAuxilio() {
		String[] elementos = new String[4];
		String m;
		int contador = 0;
		for (int i = 0; i < 4; i++) {
			if (componentesNome[i] == "W") {
				elementos[contador] = "wumpus";
				contador++;
			}
			else if (componentesNome[i] == "O") {
				elementos[contador] = "ouro";
				contador++;
			}
			else if (componentesNome[i] == "B") {
				elementos[contador] = "buraco";
				contador++;
			}
			else if (componentesNome[i] == "f") {
				elementos[contador] = "fedor";
				contador++;
			}
			else if (componentesNome[i] == "b") {
				elementos[contador] = "brisa";
				contador++;
			}
		}
		if (contador == 0) {
			m = "Esta sala está vazia.";
		}
		else {
			m = "Esta sala possui:";
			for (int i = 0; i < contador - 1; i++) {
				m += " " + elementos[i] + ",";
			}
			m += " " + elementos[contador - 1];
		}
		return m;
	}
}


