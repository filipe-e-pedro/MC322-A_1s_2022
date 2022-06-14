package connectricity;

public class Map {
    //private Cell[][] space;
    private String[][] levelMatrix;

    public Map(String[][] levelMatrix, int ySize, int xSize){
        this.levelMatrix = levelMatrix;
    //    space = new Cell[ySize][xSize];
    }

    // public Cell getCell(int xIndex, int yIndex){
    //     return space[yIndex][xIndex];
    // }

    // public void setCell(Cell xx, int xIndex, int yIndex){
    //     space[yIndex][xIndex] = xx;
    // }


}
