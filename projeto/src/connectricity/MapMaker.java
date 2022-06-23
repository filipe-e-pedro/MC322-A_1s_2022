package connectricity;

public class MapMaker {
    private Map level;
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

    public void printLevel() {
        String[][] matrix = level.getMap();
        for (int i = 0; i < ySize; i++){
            System.out.print("\t");
            for (int j = 0; j < xSize; j++){
                System.out.print(" " + matrix[i][j]);
            }
            System.out.print("\n");
        }
    }

    public void createMap() {
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
                Player player = new Player(xIndex, yIndex, level);
                newSquare.setEntity(player);
            }

            else if(levelInfo[i][2].equalsIgnoreCase("W")){
                Wire wire = new Wire(xIndex, yIndex, level);
                newSquare.setEntity(wire);
            }
            level.setSquare(xIndex, yIndex, newSquare);
        }
        // level.checkCircuits();
    }
}