package src.connectricity;

public class Square {
    private Battery battery = null;
    private Exit exit = null;
    private Generator generator = null;
    private Obstacle obstacle = null;
    private Player player = null;
    private Wire wire = null;
    private Resistor resistor = null;
    private final int xIndex;
    private final int yIndex;
    private boolean light = true;

    public Square (int xIndex, int yIndex){
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }

    public int[] getPosition() {
        int[] position = new int[2];
        position[0] = xIndex;
        position[1] = yIndex;
        return position;
    }

    public Battery getBattery(){
        return battery;
    }

    public Exit getExit(){
        return exit;
    }

    public Generator getGenerator(){
        return generator;
    }

    public Obstacle getObstacle(){
        return obstacle;
    }

    public Player getPlayer() {
        return player;
    }

    public Wire getWire() {
        return wire;
    }

    public Resistor getResistor() {
        return resistor;
    }

    public Conductor getConductor() {
        if (checkGenerator()) {
            return generator;
        }
        if (checkBattery()) {
            return battery;
        }
        if (checkWire()) {
            return wire;
        }
        return null;
    }

    public boolean getLight() {
        return light;
    }

    public void setEntity(Battery battery) {
        this.battery = battery;
    }

    public void setEntity(Exit exit) {
        this.exit = exit;
    }

    public void setEntity(Generator generator) {
        this.generator = generator;
    }

    public void setEntity(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public void setEntity(Player player) {
        this.player = player;
    }

    public void removePlayer() {
        this.player = null;
    }

    public void setEntity(Wire wire) {
        this.wire = wire;
    }

    public Wire removeWire() {
        Wire returnWire = wire;
        wire = null;
        return returnWire;
    }

    public void setEntity(Resistor resistor) {
        this.resistor = resistor;
    }

    public Resistor removeResistor() {
        Resistor returnResistor = resistor;
        resistor = null;
        return returnResistor;
    }

    public void setLight(boolean light) {
        this.light = light;
    }

    public boolean checkBattery(){
        return battery != null;
    }

    public boolean checkExit(){
        return exit != null;
    }

    public boolean checkGenerator(){
        return generator != null;
    }

    public boolean checkObstacle(){
        return obstacle != null;
    }

    public boolean checkPlayer(){
        return player != null;
    }

    public boolean checkWire(){
        return wire != null;
    }

    public boolean checkResistor(){
        return resistor != null;
    }

    public boolean emptySquare() {
        return !(checkBattery() || checkExit() || checkGenerator() || checkObstacle() || checkWire() || checkResistor());
    }

    public String mostRelevantEntity() {
        // GAMBIARRA, MUDAR DEPOIS
        if (checkObstacle()) {
            return obstacle.getName();
        }

        if (checkExit()) {
            return exit.getName();
        }

        if (checkPlayer()) {
            return player.getName();
        }

        if (checkGenerator()) {
            return generator.getName();
        }

        if (checkBattery()) {
            return battery.getName();
        }

        if (checkWire()) {
            return wire.getPotential();
        }

        if(checkResistor()) {
            return resistor.getName();
        }

        return "-";
    }

    public String circuitPart() {
        // GAMBIARRA, MUDAR DEPOIS

        if (checkGenerator()) {
            return generator.getName();
        }

        if (checkBattery()) {
            return battery.getName();
        }

        if (checkWire()) {
            return wire.getName();
        }

        if(checkResistor()) {
            return resistor.getName();
        }
        return "-";
    }
}
