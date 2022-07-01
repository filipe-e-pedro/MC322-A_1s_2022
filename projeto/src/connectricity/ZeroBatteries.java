package connectricity;

public class ZeroBatteries extends InvalidMapException {

    public ZeroBatteries() {
        super();
    }

    public ZeroBatteries(String message) {
        super(message);
    }
}