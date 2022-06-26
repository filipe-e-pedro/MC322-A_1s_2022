package connectricity;
import java.util.*;

public class CircuitMonitor {
    private Map map;
    int generatorLocator[][];
    String model[][];
    int size[];
    // Vector<String[]> v = new Vector<String[]>();
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
        if (yIndex < size[1] && conductors.contains(model[yIndex + 1][xIndex])) {
            samePotential(xIndex, yIndex + 1, potential);
        }
        if (xIndex > 0 && conductors.contains(model[yIndex][xIndex - 1])) {
            samePotential(xIndex - 1, yIndex, potential);
        }
        if (yIndex < size[0] && conductors.contains(model[yIndex][xIndex + 1])) {
            samePotential(xIndex + 1, yIndex, potential);
        }
    } 

    private int determinatePotentials() {
        this.model = map.getMatrix();
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

    private int[][] resistorConnections(int numberOfRegeions) {
        int size[] = map.getSize();
        ArrayList<String> potentials = new ArrayList<String>();
        int[][] resistanceGraph = new int[numberOfRegeions][numberOfRegeions];

        for(int i = 0; i < numberOfRegeions; i++) {
            potentials.add(Integer.toString(i));
        }

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
                    if(yIndex < size[1] && potentials.contains(model[yIndex + 1][xIndex])) {
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
                    if(xIndex < size[0] && potentials.contains(model[yIndex][xIndex + 1])) {
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

    public List<Integer> determinePotentials(int numberOfRegeions, int[][] connections) {
        return null;
    }

    public void testMatrix() {
        int numberOfRegions = determinatePotentials();
        int[][] connections = resistorConnections(numberOfRegions);
        System.out.println("Conexoes");
        printarMatriz(connections);
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
    
}
