package pt.c40task.l05wumpus;

public class Caverna {
	private Sala salas[][] = new Sala[4][4];
	
	public void setSala(int posicao_x, int posicao_y, Sala novaSala) {
		this.salas[posicao_x][posicao_y] = novaSala;
	}

	public Sala getSala(int posicao_x, int posicao_y) {
		return salas[posicao_x][posicao_y];
	}
	
	public String[][] getCaverna(){
		String[][] matriz = new String[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(salas[i][j].foiVisitada()) {
					matriz[i][j] = salas[i][j].compMaisImportante();
				}
				else
					matriz[i][j] = salas[i][j].compMaisImportante();
			}
		}
		return matriz;
	}
}
