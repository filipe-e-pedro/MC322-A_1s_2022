package src.connectricity;

import java.util.*;

public class Map {
    int xSize, ySize;
    private Square squares[][];
	private boolean exitOpen = false;

	private int[] batteryCharge;

	private int[] chargesNeeded;

    public Map (int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.squares = new Square[ySize][xSize];
		batteryCharge = new int[]{0, 0, 0, 0};
		chargesNeeded = new int[]{0, 0, 0, 0};
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
		String[][] matrix = new String[ySize][xSize];
		for(int yIndex = 0; yIndex < ySize; yIndex++) {
			for(int xIndex = 0; xIndex < xSize; xIndex++) {
				matrix[yIndex][xIndex] = squares[yIndex][xIndex].mostRelevantEntity();
			}
		}
		return matrix;
	}

	public String[][] getCircuit(){
		String[][] matriz = new String[ySize][xSize];
		for(int yIndex = 0; yIndex < ySize; yIndex++) {
			for(int xIndex = 0; xIndex < xSize; xIndex++) {
				matriz[yIndex][xIndex] = squares[yIndex][xIndex].circuitPart();
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
		
		for(int yIndex = 0; yIndex < ySize; yIndex++) {
			for(int xIndex = 0; xIndex < xSize; xIndex++) {
				if(squares[yIndex][xIndex].checkGenerator()) {
					int[] position = new int[2];
					position[0] = xIndex;
					position[1] = yIndex;
					generatorPositions.add(position);
				}
			}
		}
		return generatorPositions;
	}

	public boolean batteriesSatisfied() {
		boolean satisfied = true;
		for(int yIndex = 0; yIndex < ySize; yIndex++) {
			for(int xIndex = 0; xIndex < xSize; xIndex++) {
				if(squares[yIndex][xIndex].checkBattery()) {
					setBatteryCharge(squares[yIndex][xIndex].getBattery().getID(), squares[yIndex][xIndex].getBattery().getPotentialLevel());
					if(!squares[yIndex][xIndex].getBattery().rightPotential())
						satisfied = false;
				}
			}
		}
		return satisfied;
	}

	public boolean getExitState(){
		return exitOpen;
	}

	public void setBatteryCharge(int battery, int charge){
		this.batteryCharge[battery] = charge;
	}

	public int getBatteryCharge(int battery){
		return this.batteryCharge[battery];
	}

	public void setNeededCharge(int battery, int chargeNeeded){
		this.chargesNeeded[battery] = chargeNeeded;
	}

	public int getNeededCharge(int battery){
		return chargesNeeded[battery];
	}

	public void manageExits() {
		boolean batteriesState = batteriesSatisfied();
		for(int yIndex = 0; yIndex < ySize; yIndex++) {
			for(int xIndex = 0; xIndex < xSize; xIndex++) {
				if(squares[yIndex][xIndex].checkExit()) {
					squares[yIndex][xIndex].getExit().setOpen(batteriesState);
					exitOpen = batteriesState;
				}
			}
		}
	}
}
