package src.connectricity;

public class WrongPlayerInventory  extends InvalidMapException{
/**
 * Classe que representa a exception de inventorio do jogador descrito errado
 * no arquivo de mapa
 */
    public WrongPlayerInventory() {
        super();
    }

    public WrongPlayerInventory(String message) {
        super(message);
    }
}

