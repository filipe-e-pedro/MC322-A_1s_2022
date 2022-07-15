package src.connectricity;

public class WrongBatteryPotential extends InvalidMapException{
/**
 * Classe que representa a exception de numero errado do potencial necessario
 * de uma bateria
 */
    
    public WrongBatteryPotential() {
        super();
    }

    public WrongBatteryPotential(String message) {
        super(message);
    }
}
