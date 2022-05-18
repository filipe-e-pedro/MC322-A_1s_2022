package pt.c40task.l05wumpus;

public class Sala {
	private Componente componentes[] = new Componente[4];
	private int posicao_x, posicao_y;
	private boolean revelada = false;
	
	public Sala(int posicao_x, int posicao_y, String componente, Caverna mapa){
		this.posicao_x = posicao_x;
		this.posicao_y = posicao_y;
		for(int i = 0; i < 4; i++) {
			this.componentes[i] = null;
		}
		if(componente.equalsIgnoreCase("W")) 
			this.componentes[0] = new Wumpus(posicao_x, posicao_y, mapa);
		else if(componente.equalsIgnoreCase("O")) 
			this.componentes[0] = new Ouro(posicao_x, posicao_y, mapa);
		else if(componente.equalsIgnoreCase("B")) 
			this.componentes[0] = new Buraco(posicao_x, posicao_y, mapa);
		else if(componente.equalsIgnoreCase("P")) 
			this.componentes[1] = new Heroi(posicao_x, posicao_y, mapa);
	}

	public Componente compMaisImportante() {
		for (int i = 0; i < 4; i++) {
			if (componentes[i] != null) {
				return componentes[i];
			}
		}
		return null;
	}
	
	public void revelaSala() {
		revelada = true;
	}

	public boolean foiVisitada() {
		return revelada;
	}
	
	public void setComponente(Componente comp) {
		componentes[comp.getPrioridade()] = comp;
	}

	public void removeHeroi() {
		componentes[1] = null;
	}

	public void adicionaHeroi(Heroi heroi) {
		componentes[1] = heroi;
	}
	
	public int[] getPosicao() {
		int posicao[] = new int[2];
		posicao[0] = posicao_x;
		posicao[1] = posicao_y;
		return posicao;
	}

	public void removeWumpus() {
		componentes[0] = null;
	}

	public boolean checaBuraco() {
		return componentes[0].getNome() == "B";
	}

	public boolean checaWumpus() {
		return componentes[0].getNome() == "W";
	}
}
