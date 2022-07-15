package src.connectricity;

public class ZeroGenerators extends InvalidMapException {
/**
 * Classe que representa a exception de quando nao tem geradores do no aqrquivo de mapa
 */
    public ZeroGenerators() {
        super();
   }

    public ZeroGenerators(String message) {
        super(message);
    }

}