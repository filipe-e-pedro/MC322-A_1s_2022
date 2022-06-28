package connectricity;

import java.util.*;

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
				matriz[i][j] = squares[i][j].mostRelevantEntity();
			}
		}
		return matriz;
	}

	public String[][] getCircuit(){
		String[][] matriz = new String[ySize][xSize];
		for(int i = 0; i < ySize; i++) {
			for(int j = 0; j < xSize; j++) {
				matriz[i][j] = squares[i][j].circuitPart();
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

	public ArrayList<int[]> getGeneratorPositions() {
		ArrayList<int[]> generatorPositions = new ArrayList<int[]>();
		
		for(int i = 0; i < ySize; i++) {
			for(int j = 0; j < xSize; j++) {
				if(squares[i][j].checkGenerator()) {
					int[] position = new int[2];
					position[0] = i;
					position[1] = j;
					generatorPositions.add(position);
				}
			}
		}
		return generatorPositions;
	}

	public boolean batteriesSatisfied() {
		boolean satisfied = true;
		for(int i = 0; i < ySize; i++) {
			for(int j = 0; j < xSize; j++) {
				if(squares[i][j].checkBattery() && !squares[i][j].getBattery().rightPotential()) {
					satisfied = false;
				}
			}
		}
		return satisfied;
	}

	public void manageExits() {
		boolean batteriesState = batteriesSatisfied();
		for(int i = 0; i < ySize; i++) {
			for(int j = 0; j < xSize; j++) {
				if(squares[i][j].checkExit()) {
					squares[i][j].getExit().setOpen(batteriesState);
				}
			}
		}
	}
}
