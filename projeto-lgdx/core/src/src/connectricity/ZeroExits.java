package src.connectricity;

public class ZeroExits extends InvalidMapException{
/**
 * Classe que representa a exception de quando nao tem saida do no aqrquivo de mapa
 */
    public ZeroExits() {
        super();
    }

    public ZeroExits(String message) {
        super(message);
    }

}
