package connectricity;

public class MapMaker {
    private Map level;
    Player player = new Player(0, 0, level, 30, 30);
    int xSize, ySize;
    //private int mapID;
    //private Toolkit tk;
    private String[][] levelInfo;

    public MapMaker(int mapID, Toolkit tk){
        levelInfo = tk.getMap(mapID);
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

    public boolean createMap() {
        int xIndex, yIndex;
        Square newSquare;

        for(int i = 0; i < levelInfo.length; i++) {
            yIndex = Integer.parseInt(levelInfo[i][0]) - 1;
            xIndex = Integer.parseInt(levelInfo[i][1]) - 1;
            newSquare = new Square(xIndex, yIndex);

            if(levelInfo[i][2].equalsIgnoreCase("B")){
                Battery battery = new Battery(xIndex, yIndex, level);
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
                player.setPosition(xIndex, yIndex);
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
        // level.checkCircuits();
        return true;
    }

    public Map getMap(){
		return level;
	}

    public Player getPlayer(){
		return player;
	}
}