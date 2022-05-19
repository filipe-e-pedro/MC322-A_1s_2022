package pt.c40task.l05wumpus;

public class Sala {
	private Componente componentes[] = new Componente[4];
	private String[] componentesNome = new String[5];
	private int posicao_x, posicao_y;
	private boolean revelada = false;
	
	public Sala(int posicao_x, int posicao_y, Componente[] componentes, String[] componentesNome){
		this.posicao_x = posicao_x;
		this.posicao_y = posicao_y;
		this.componentes = componentes;
		this.componentesNome = componentesNome;
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
	
	public void setComponente(Componente comp) {
		componentes[comp.getPrioridade()] = comp;
		componentesNome[comp.getPrioridade()] = comp.getNome();
	}

	public boolean checaBuraco(){
		if(compMaisImportante() == "B"){
			return true;
		}
		return false;
	}

	public boolean checaWumpus(){
		if(compMaisImportante() == "W"){
			return true;
		}
		return false;
	}

	public boolean checaHeroi(){
		if(componentesNome[1] == "P")
			return true;
		return false;
	}

	public boolean checaOuro(){
		if(compMaisImportante() == "O"){
			return true;
		}
		return false;
	}

	public void removeOuro(){
		componentes[0] = null;
		componentesNome[0] = null;
	}

	public void removeHeroi() {
		componentes[1] = null;
		componentesNome[1] = null;
	}

	public void adicionaHeroi(Heroi heroi) {
		componentes[1] = heroi;
		componentesNome[1] = "P";
	}
	
	public int[] getPosicao() {
		int posicao[] = new int[2];
		posicao[0] = posicao_x;
		posicao[1] = posicao_y;
		return posicao;
	}

	public void removeWumpus() {
		componentes[0] = null;
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


