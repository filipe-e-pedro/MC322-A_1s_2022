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
		Componente compMaiorPrioridade;
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(salas[i][j].foiVisitada()) {
					compMaiorPrioridade = salas[i][j].compMaisImportante();
					if (compMaiorPrioridade == null)
						matriz[i][j] = "#";
					else
						matriz[i][j] = compMaiorPrioridade.getNome();
				}
				else
					matriz[i][j] = "-";
			}
		}
		return matriz;
	}
}
