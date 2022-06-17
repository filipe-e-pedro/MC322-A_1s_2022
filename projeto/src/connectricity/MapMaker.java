package connectricity;

public class MapMaker {
    //private Map level;
    //private int mapID;
    //private Toolkit tk;
    private String[][] levelInfo;

    public MapMaker(int mapID, Toolkit tk){
        levelInfo = tk.getMap(mapID);
    }

    public void printLevelInfo(){
        for(int i = 0; i < levelInfo.length; i++){
            for(int j = 0; j < levelInfo[i].length; j++){
                System.out.print(levelInfo[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
