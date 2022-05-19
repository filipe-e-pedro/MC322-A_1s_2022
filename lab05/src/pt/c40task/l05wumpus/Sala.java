package pt.c40task.l05wumpus;

public class Sala {
	private Componente componentes[] = new Componente[4];
	private String[] componentesNome = new String[5];
	private int posicao_x, posicao_y;
	private boolean revelada = false;
	
	public Sala(int posicao_x, int posicao_y, String componente, Caverna mapa){
		this.posicao_x = posicao_x;
		this.posicao_y = posicao_y;
		for(int i = 0; i < 4; i++) {
			this.componentes[i] = null;
			this.componentesNome[i] = null;
		}
		this.componentesNome[4] = "#";
		if(componente.equalsIgnoreCase("W")){
			this.componentes[0] = new Wumpus(posicao_x, posicao_y, mapa);
			this.componentesNome[0] = "W";
		}
		else if(componente.equalsIgnoreCase("O")){
			this.componentes[0] = new Ouro(posicao_x, posicao_y, mapa);
			this.componentesNome[0] = "O";
		}
		else if(componente.equalsIgnoreCase("B")){
			this.componentes[0] = new Buraco(posicao_x, posicao_y, mapa);
			this.componentesNome[0] = "B";
		}
		else if(componente.equalsIgnoreCase("P"))
			this.componentesNome[1] = "P";
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
		String pos0 = "";
		String pos2 = "";
		String pos3 = "";
		if (componentes[0] != null && componentes[0].getNome() == "W") {
			pos0 = " wumpus";
		}
		if (componentes[0] != null && componentes[0].getNome() == "B") {
			pos0 = " buraco";
		}
		if (componentes[0] != null && componentes[0].getNome() == "O") {
			pos0 = " ouro";
		}
		if (componentes[2] != null) {
			pos2 = " fedor";
		}
		if (componentes[3] != null) {
			pos3 = " brisa";
		}
		return "Esta sala possui:" + pos0 + pos2 + pos3 + ".";
	}
}
