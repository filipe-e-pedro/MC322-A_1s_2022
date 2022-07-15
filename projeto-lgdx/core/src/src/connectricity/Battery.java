package src.connectricity;

public class Battery extends Conductor{
/**
 * Classe que representa o objeto Battery, filha da classe abstrata Conductor
 */
    private int targetPotential;
    private int ID;

    public Battery (int xIndex, int yIndex, Map map, int targetPotential){
    /**
     * Recebe dois inteiros que representam a posicao que esse objeto esta, o mapa
     * e o potencial necessario para a condicao de vitoria
     *
     * Coloca valor no atributo do potencial necessario para vencer e chama o
     * construtor da classe Conductor
     */
		super(xIndex, yIndex, map);
        this.targetPotential = targetPotential;
	}

    public String getName() {
        return "B";
    }

    public void setID(int ID){ this.ID = ID; }

    public int getID(){ return ID; }

    public boolean rightPotential() {
        return targetPotential == potentialLevel;
    }
}