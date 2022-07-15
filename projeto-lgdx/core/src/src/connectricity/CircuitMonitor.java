package src.connectricity;
import java.util.*;

public class CircuitMonitor {
    /**
     * Classe responsavel por determinar e dar set no potencial de cada componente do circuito
     */
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
    /**
     * Recebe:
     * a posicao x que esta sendo analizada
     * a posicao y que esta sendo analizada
     * o index de potencial determinado para aquela regiao
     * 
     * Recursivamente preenche uma regiao de condutores adjacentes do model com o mesmo index de potencial
     */
        
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
    /**
     * Retorna o numero de posicoes de mesmo potencial
     * 
     * Percorre o model do circuito a procura de condutores
     * Quando um condutor sem um index de potencial determidado eh encontrado, eh chamado o metodo samePotential()
     * Esse condutor tera seu index potencial determinado, assim como todos os condutores adjacentes a ele
     * Assim, ao final do metodo, o model tera varias regioes de condutores conectados com mesmo index de potencial
     * Cada regiao separada possui um index diferente
     */
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
    /**
     * Recebe uma lista de inteiros que representa os index de potenciais adjacentes a um resistor
     * A partir disso, atualiza a matriz que representa o grafo de resistores conectando as regioes de mesmo potencial
     */

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
    /**
     * Recebe o numero de regioes de condutores com mesmo potencial e uma lista de strings com os index de potencial
     * Retorna a matriz com o numero de resistores entre cada regiao
     * 
     * Cria uma matriz de inteiros, que faz o papel de grafo por matriz de incidencia
     * Cada linha diz respeito a um index de potencial, ou seja, uma regiao de condutores
     * Os index sao os mesmos do numero da linha que diz respeito a eles
     * A partir disso, cada posicao nessa linha representa o numero de resistores que conectam a regiao da linha a regiao da posicao
     * 
     * Isso eh feito percorrendo o modelo a procura de resistores
     * Quando um resistor eh encontrado eh criada uma lista de inteiros representando os index das regioes adjacentes a ele
     * Apos o resistor ser preenchido com todas as regioes adjacentes, ele eh contabilizado na matriz
     * Quando o model eh percorrido todos os resistores sao contabilizados e a matriz eh retornada
     */
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
   /**
     * Recebe:
     * uma fila com os index de potencial a serem examinados, 
     * um array que representa se os index de potenciais ja foram adicionados a fila
     * um array com o menor numero de resistores entre o gerador examinado e cada index de potencial
     * a matriz de conexoes com o numero de resistores entre cada regiao
     * um inteiro com o index potencial que esta sendo analizado
     * 
     * Recursivamente conta o menor numero de resistores entre o gerador examinado e cada regiao, atualizando o resistorPath
     * Funciona de maneira similar a um breadth first search
     */
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
    /**
     * Recebe:
     * um inteiro com o numero de regioes
     * a matriz de conexoes com o numero de resistores entre cada regiao
     * um array de tamanho 2 com as coordenadas do gerador que esta sendo analizado
     * 
     * Retorna um array de inteiros com o potencial de cada regiao de acordo que este gerador
     * Cada posicao no array eh o index atribuido aquela regiao e o valor da posicao eh o valor do potencial dela
     * 
     * Chama o metodo countResistorPath() para determinar o menor numero de resistores entre o gerador e cada regiao
     * Determina o potencial de cada regiao como 3 - numero minimo de resistores no caminho
     */
        int generatorPotentialIndex = Integer.parseInt(model[generatorPosition[1]][generatorPosition[0]]);
        int[] queued = new int[numberOfRegions]; // representa se os index de potenciais ja foram adicionados a fila
        int[] resistorPath = new int[numberOfRegions]; // o menor numero de resistores entre o gerador examinado e cada index de potencial
        int[] potentialValues = new int[numberOfRegions]; // o potencial de cada regiao, ordenado pelo index
        Queue<Integer> queue = new LinkedList<Integer>(); //fila com os index de potencial a serem visitados

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
    /**
     * Recebe:
     * um inteiro com o numero de regioes
     * a matriz de conexoes com o numero de resistores entre cada regiao
     * 
     * Retorna um array com o potencial final de cada regiao de condutores
     * 
     * Determina o potencial parcial de cada regiao a partir de cada gerador
     * Determina que o potencial final de cada regiao eh o maior potencial dado a ela por qualquer gerador
     */
        int[] partialPotentials = new int[numberOfRegions];
        int[] finalPotentials = new int[numberOfRegions];
        ArrayList<int[]> generatorPositions = map.getGeneratorPositions(); // lista com as posicoes de todos os geradores

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
    /**
     * Recebe:
     * um inteiro com o numero de regioes
     * uma lista de Strings com os index de potenciais
     * a matriz de conexoes com o numero de resistores entre cada regiao
     * 
     * Retorna uma matriz de inteiros com o potencial de cada condutor
     * Caso nao haja condutores na posicao, seu potencial eh dado como -1
     */
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
    /**
     * Recebe o numero de regioes de condutores
     * Retorna lista de Strings com todos os index de potencial usados
     */
        ArrayList<String> potentials = new ArrayList<String>();
        for (int i = 0; i < numberOfRegions; i++) {
            potentials.add(Integer.toString(i));
        }
        return potentials;
    }

    public void setPotentials() {
    /**
     * Gera a matriz de potenciais finais de cada condutor e chama o metodos setPotentialLevel para cada um deles
     */
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
    /**
     * Faz print de uma matriz de inteiros para facilitar debugging
     */
        for(int i = 0; i < matriz.length; i++) {
            System.out.print("\t");
            for (int j = 0; j < matriz[0].length; j++){
                System.out.print(" " + matriz[i][j]);
            }
            System.out.print("\n");
        }
    }

    public String printarLista(int[] lista) {
    /**
     * Faz print de uma lista para facilitar debugging
     */
        String l = "";
        for(int i = 0; i < lista.length; i++) {
            l += lista[i] + " ";
        }
        return l;
    }

}
