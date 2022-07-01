package src.connectricity;

public class WrongPlayerInventory  extends InvalidMapException{

    public WrongPlayerInventory() {
        super();
    }

    public WrongPlayerInventory(String message) {
        super(message);
    }
}

