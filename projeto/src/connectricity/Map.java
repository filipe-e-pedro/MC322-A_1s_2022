package connectricity;

import javax.imageio.ImageIO;

public class Map {
    int xSize, ySize;
    private Square squares[][];

    public Map (int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.squares = new Square[ySize][xSize];
    }
	
	public void setSquare(int xIndex, int yIndex, Square newSquare) {
		squares[yIndex][xIndex] = newSquare;
	}

	public Square getSquare(int xIndex, int yIndex) {
		return squares[yIndex][xIndex];
	}

	public int[] getSize() {
		int[] size = new int[2];
		size[0] = xSize;
		size[1] = ySize;
		return size;
	}
	
	public String[][] getMatrix(){
		String[][] matriz = new String[ySize][xSize];
		for(int i = 0; i < ySize; i++) {
			for(int j = 0; j < xSize; j++) {
				if(squares[i][j].getLight()) {
					matriz[i][j] = squares[i][j].mostRelevantEntity();
				}
				else
					matriz[i][j] = "#";
			}
		}
		return matriz;
	}
	
	public boolean invalidMove(int xIndex, int yIndex) {
		if (xIndex < 0 || xIndex >= xSize || yIndex < 0 || yIndex >= ySize) {
			return true;
		}
		if (getSquare(xIndex, yIndex).checkObstacle()) {
			return true;
		}
		return false;
	}
}
