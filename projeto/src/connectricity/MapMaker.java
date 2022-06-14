package connectricity;

public class MapMaker {
    private Map level;
    //private int mapID;
    //private Toolkit tk;
    private String[][] levelInfo;
    private String[][] levelMatrix;

    public MapMaker(int mapID, Toolkit tk){
        levelInfo = tk.getMap(mapID);
    }

    public void makeMatrix(int limit_x, int limit_y){
        int y_maior = maxValue(0, limit_y);
        int x_maior = maxValue(1, limit_x);
        int y, x;
        levelMatrix = new String[y_maior][x_maior];
        for(y = 0; y < y_maior; y++){
            for(x = 0; x < x_maior; x++){
                levelMatrix[y][x] = "X";
            }
        }
        for(int i = 0; i < levelInfo.length; i++){
            y = Integer.parseInt(levelInfo[i][0]);
            x = Integer.parseInt(levelInfo[i][1]);
            if(y <= limit_y && x <= limit_x)
                levelMatrix[y-1][x-1] = levelInfo[i][2];
        }
    }

    private int maxValue(int pos, int limit){
        int max = 0;
        int valor;
        for (int i = 0; i < levelInfo.length; i++){
            valor = Integer.parseInt(levelInfo[i][pos]);
            if(valor <= limit && valor > max)
                max = valor;
        }
        return max;
    }

    public void printLevelInfo(){
        for(int i = 0; i < levelInfo.length; i++){
            for(int j = 0; j < levelInfo[i].length; j++){
                System.out.print(levelInfo[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public void printLevelMatrix(){
        for(int i = 0; i < levelMatrix.length; i++){
            for(int j = 0; j < levelMatrix[i].length; j++){
                System.out.print(levelMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
