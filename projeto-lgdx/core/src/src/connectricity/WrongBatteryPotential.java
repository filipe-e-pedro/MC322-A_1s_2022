package src.connectricity;

public class WrongBatteryPotential extends InvalidMapException{
    
    public WrongBatteryPotential() {
        super();
    }

    public WrongBatteryPotential(String message) {
        super(message);
    }
}
