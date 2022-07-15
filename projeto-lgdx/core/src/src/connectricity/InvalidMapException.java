package src.connectricity;

public class InvalidMapException extends Exception{
/**
 * Classe de exception que eh chamada quando
 * o mapa que esta tentando ser criado eh invalido
 */
    public InvalidMapException() {
        super();
    }

    public InvalidMapException(String message) {
        super(message);
    }
}

