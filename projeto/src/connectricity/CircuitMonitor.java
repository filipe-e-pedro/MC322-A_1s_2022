package connectricity;
import java.util.*;

public class CircuitMonitor {
    private Map map;
    String model[][];
    int size[];
    int generatorPotential = 3;
    List<String> conductors =  Arrays.asList("W", "G", "B");  

    public CircuitMonitor(Map map) {
        this.map = map;
        this.size = map.getSize();
    }

    private void samePotential(int xIndex, int yIndex, String potential){
        model[yIndex][xIndex] = potential;
        if (yIndex > 0 && conductors.contains(model[yIndex - 1][xIndex])) {
            samePotential(xIndex, yIndex - 1, potential);
        }
        if (yIndex < size[1] - 1 && conductors.contains(model[yIndex + 1][xIndex])) {
            samePotential(xIndex, yIndex + 1, potential);
        }
        if (xIndex > 0 && conductors.contains(model[yIndex][xIndex - 1])) {
            samePotential(xIndex - 1, yIndex, potential);
        }
        if (xIndex < size[0] - 1 && conductors.contains(model[yIndex][xIndex + 1])) {
            samePotential(xIndex + 1, yIndex, potential);
        }
    } 

    private int determinatePotentials() {
        this.model = map.getCircuit();
        int potentialIndex = 0;
        for (int yIndex = 0; yIndex < size[1]; yIndex++) {
            for (int xIndex = 0; xIndex < size[0]; xIndex++) {
                if (conductors.contains(model[yIndex][xIndex])) {
                    samePotential(xIndex, yIndex, Integer.toString(potentialIndex));
                    potentialIndex ++;
                }
            }
        }
        return potentialIndex;
    }

    public void contabilizeResistor(int[][] resistanceGraph, List<Integer> resistor) {

        for (int potentialIndex = 0; potentialIndex < resistanceGraph.length; potentialIndex++) {
            if (resistor.contains(potentialIndex)) {
                for (int i = 0; i < 4; i++) {
                    if (resistor.get(i) >= 0 && resistor.get(i) != potentialIndex) {
                        resistanceGraph[potentialIndex][resistor.get(i)] += 1;
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            resistor.set(i, -1);
        }
    }

    private int[][] resistorConnections(int numberOfRegions, ArrayList<String> potentials) {
        int size[] = map.getSize();
        int[][] resistanceGraph = new int[numberOfRegions][numberOfRegions];

        List<Integer> curResistor = Arrays.asList(-1, -1, -1, -1);
        int conectedPotential = -1;
        for (int yIndex = 0; yIndex < size[1]; yIndex++) {
            for (int xIndex = 0; xIndex < size[0]; xIndex++) {
                if (model[yIndex][xIndex].equals("R")) {
                    if(yIndex > 0 && potentials.contains(model[yIndex - 1][xIndex])) {
                        conectedPotential = Integer.parseInt(model[yIndex - 1][xIndex]);
                        if (!curResistor.contains(conectedPotential)) {
                            curResistor.set(0, conectedPotential);
                        }
                    }
                    if(yIndex < size[1] - 1 && potentials.contains(model[yIndex + 1][xIndex])) {
                        conectedPotential = Integer.parseInt(model[yIndex + 1][xIndex]);
                        if (!curResistor.contains(conectedPotential)) {
                            curResistor.set(1, conectedPotential);
                        }
                    }
                    if(xIndex > 0 && potentials.contains(model[yIndex][xIndex - 1])) {
                        conectedPotential = Integer.parseInt(model[yIndex][xIndex - 1]);
                        if (!curResistor.contains(conectedPotential)) {
                            curResistor.set(2, conectedPotential);
                        }
                    }
                    if(xIndex < size[0] - 1 && potentials.contains(model[yIndex][xIndex + 1])) {
                        conectedPotential = Integer.parseInt(model[yIndex][xIndex + 1]);
                        if (!curResistor.contains(conectedPotential)) {
                            curResistor.set(3, conectedPotential);
                        }
                    }
                    contabilizeResistor(resistanceGraph, curResistor);
                }
            }
        }
        return resistanceGraph;
    }

    private void countResistorPath(Queue<Integer> queue, int[] queued, int[] resistorPath, int[][] connections, int currentPotential) {
        for (int i = 0; i < connections[currentPotential].length; i++) {
            if (connections[currentPotential][i] > 0 && queued[i] == 0){
                resistorPath[i] = resistorPath[currentPotential] + 1;
                queue.add(i);
                queued[i] = 1;
            }
        }
        if (queue.isEmpty()) {
            return;
        }
        int nextPotential = queue.remove();
        countResistorPath(queue, queued, resistorPath, connections, nextPotential);
    }


    private int[] determinePotentials(int numberOfRegions, int[][] connections, int[] generatorPosition) {
        int generatorPotentialIndex = Integer.parseInt(model[generatorPosition[1]][generatorPosition[0]]);
        int[] queued = new int[numberOfRegions];
        int[] resistorPath = new int[numberOfRegions];
        int[] potentialValues = new int[numberOfRegions];
        Queue<Integer> queue = new LinkedList<Integer>(); 

        for (int i = 0; i < numberOfRegions; i++) {
            queued[i] = 0;
            resistorPath[i] = -1;
            potentialValues[i] = 0;
        }

        queued[generatorPotentialIndex] = 1;
        resistorPath[generatorPotentialIndex] = 0;
        countResistorPath(queue, queued, resistorPath, connections, generatorPotentialIndex);

        for (int i = 0; i < numberOfRegions; i++) {
            if (resistorPath[i] != -1) {
                potentialValues[i] = 3 - resistorPath[i];
            }
        } 
        return potentialValues;
    }

    private int[] determineFinalPotentials(int numberOfRegions,  int[][] connections) {
        int[] partialPotentials = new int[numberOfRegions];
        int[] finalPotentials = new int[numberOfRegions];
        ArrayList<int[]> generatorPositions = map.getGeneratorPositions();

        for (int i = 0; i < numberOfRegions; i++) {
            finalPotentials[i] = 0;
        }
        
        for (int i = 0; i < generatorPositions.size(); i++) {
            partialPotentials = determinePotentials(numberOfRegions, connections, generatorPositions.get(i));
            for (int j = 0; j < numberOfRegions; j++) {
                if (partialPotentials[j] > finalPotentials[j]) {
                    finalPotentials[j] = partialPotentials[j];
                }
            }
        }
        return finalPotentials;

    }

    private int[][] createPotentialMatrix(int numberOfRegions, ArrayList<String> potentials,  int[][] connections) {
        int[][] potentialMatrix = new int[size[1]][size[0]];
        int[] finalPotentials = determineFinalPotentials(numberOfRegions, connections);
        
        for (int yIndex = 0; yIndex < size[1]; yIndex++) {
            for (int xIndex = 0; xIndex < size[0]; xIndex++) {
                if (potentials.contains(model[yIndex][xIndex])) {
                    potentialMatrix[yIndex][xIndex] = finalPotentials[Integer.parseInt(model[yIndex][xIndex])];
                }
                else {
                    potentialMatrix[yIndex][xIndex] = -1;
                }
            }
        }
        return potentialMatrix;    
    }

    private ArrayList<String> createPotentialsString(int numberOfRegions) {
        ArrayList<String> potentials = new ArrayList<String>();
        for (int i = 0; i < numberOfRegions; i++) {
            potentials.add(Integer.toString(i));
        }
        return potentials;
    }

    public void setPotentals() {
        int numberOfRegions = determinatePotentials();
        ArrayList<String> potentials = createPotentialsString(numberOfRegions);
        int[][] connections = resistorConnections(numberOfRegions, potentials);
        int[][] potentialMatrix = createPotentialMatrix(numberOfRegions, potentials, connections);
        
        for (int yIndex = 0; yIndex < size[1]; yIndex++) {
            for (int xIndex = 0; xIndex < size[0]; xIndex++) {
                if (potentialMatrix[yIndex][xIndex] >= 0) {
                    map.getSquare(xIndex, yIndex).getConductor().setPotentialLevel(potentialMatrix[yIndex][xIndex]);
                }
            }
        }
    }

    public void printarMatriz(int[][] matriz){
        for(int i = 0; i < matriz.length; i++) {
            System.out.print("\t");
            for (int j = 0; j < matriz[0].length; j++){
                System.out.print(" " + matriz[i][j]);
            }
            System.out.print("\n");
        }
    }

    public String printarLista(int[] lista) {
        String l = "";
        for(int i = 0; i < lista.length; i++) {
            l += lista[i] + " ";
        }
        return l;
    }
  
}
