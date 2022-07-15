package src.connectricity;

public class WrongPlayerNumber extends InvalidMapException {

    public WrongPlayerNumber() {
        super();
    }

    public WrongPlayerNumber(String message) {
        super(message);
    }

}