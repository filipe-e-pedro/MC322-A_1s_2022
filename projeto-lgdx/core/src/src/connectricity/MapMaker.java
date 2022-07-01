package src.connectricity;

public class MapMaker {
    private Map level;
    Player player;
    int xSize, ySize;
    private String[][] levelInfo;

    public MapMaker(String[][] levelInfo){
        this.levelInfo = levelInfo;
        ySize = Integer.parseInt(levelInfo[levelInfo.length -1][0]);
        xSize = Integer.parseInt(levelInfo[levelInfo.length -1][1]);
        level = new Map(xSize, ySize);
    }

    public void printLevelInfo(){
        for(int i = 0; i < levelInfo.length; i++){
            for(int j = 0; j < levelInfo[i].length; j++){
                System.out.print(levelInfo[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public void createMap() {
        int xIndex, yIndex;
        Square newSquare;
        int batteryCount = 0;
    
        for(int i = 0; i < levelInfo.length; i++) {
            yIndex = Integer.parseInt(levelInfo[i][0]) - 1;
            xIndex = Integer.parseInt(levelInfo[i][1]) - 1;
            newSquare = new Square(xIndex, yIndex);

            if(levelInfo[i][2].equalsIgnoreCase("B")){
                Battery battery = new Battery(xIndex, yIndex, level, Integer.parseInt(levelInfo[i][3]));
                battery.setID(batteryCount);
                getMap().setNeededCharge(batteryCount, Integer.parseInt(levelInfo[i][3]));
                batteryCount++;
                newSquare.setEntity(battery);
            }

            else if(levelInfo[i][2].equalsIgnoreCase("E")){
                Exit exit = new Exit(xIndex, yIndex, level);
                newSquare.setEntity(exit);
            }

            else if(levelInfo[i][2].equalsIgnoreCase("G")){
                Generator generator = new Generator(xIndex, yIndex, level);
                newSquare.setEntity(generator);
            }

            else if(levelInfo[i][2].equalsIgnoreCase("O")){
                Obstacle obstacle = new Obstacle(xIndex, yIndex, level);
                newSquare.setEntity(obstacle);
            }

            else if(levelInfo[i][2].equalsIgnoreCase("P")){
                player = new  Player(xIndex, yIndex, level, Integer.parseInt(levelInfo[i][3]), Integer.parseInt(levelInfo[i][4]));
                newSquare.setEntity(player);
            }

            else if(levelInfo[i][2].equalsIgnoreCase("W")){
                Wire wire = new Wire(xIndex, yIndex, level);
                newSquare.setEntity(wire);
            }

            else if(levelInfo[i][2].equalsIgnoreCase("R")){
                Resistor resistor = new Resistor(xIndex, yIndex, level);
                newSquare.setEntity(resistor);
            }
            level.setSquare(xIndex, yIndex, newSquare);
        }
    }

    public Map  getMap(){
		return level;
	}

    public Player getPlayer(){
		return player;
	}

    public void invalidMap() throws InvalidMapException {
        int generatorCount = 0, playerCount = 0, batteryCount = 0, exitsCount = 0;

        for(int i = 0; i < levelInfo.length; i++) {

            if(levelInfo[i][2].equalsIgnoreCase("B")){
                batteryCount++;
                try {
                    int batPot = Integer.parseInt(levelInfo[i][3]);
                    if (batPot > 3 || batPot < 0) {
                        throw new Exception();
                    }
                } catch (Exception exception) {
                    throw new WrongBatteryPotential("Potencial desejado na bateria especificado incorretamente");
                }
            }

            if(levelInfo[i][2].equalsIgnoreCase("G")){
                generatorCount++;
            }

            if(levelInfo[i][2].equalsIgnoreCase("P")){
                playerCount++;
                try {
                    int initialWireNumber = Integer.parseInt(levelInfo[i][3]);
                    int initialResistorNumber = Integer.parseInt(levelInfo[i][4]);
                    if (initialWireNumber > 60 || initialWireNumber < 0 || initialResistorNumber > 60 || initialResistorNumber < 0) {
                        throw new Exception();
                    }
                } catch (Exception exception) {
                    throw new WrongPlayerInventory("Numero de fios ou resistores do jogador especificado incorretamente");
                }
            }

            if(levelInfo[i][2].equalsIgnoreCase("E")){
                exitsCount++;
            }
        }

        if (batteryCount == 0) {
            throw new ZeroBatteries("Nao há baterias no mapa");
        }
    
        if (playerCount != 1) {
            throw new WrongPlayerNumber("Número de players diferente de 1");
        }
    
        if (generatorCount == 0) {
            throw new ZeroGenerators("Não há geradores no mapa");
        }

        if (exitsCount == 0) {
            throw new ZeroExits("Não há saídas no mapa");
        }
    }
}