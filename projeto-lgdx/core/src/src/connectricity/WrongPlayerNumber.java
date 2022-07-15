package src.connectricity;

public class WrongPlayerNumber extends InvalidMapException {
/**
 * Classe que representa a exception de numero errado de players no arquivo de mapa
 */
    public WrongPlayerNumber() {
        super();
    }

    public WrongPlayerNumber(String message) {
        super(message);
    }

}