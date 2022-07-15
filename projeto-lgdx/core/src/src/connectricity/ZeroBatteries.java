package src.connectricity;

public class ZeroBatteries extends InvalidMapException {
/**
 * Classe que representa a exception de quando nao tem baterias no arquivo de mapa
 */
    public ZeroBatteries() {
        super();
    }

    public ZeroBatteries(String message) {
        super(message);
    }
}