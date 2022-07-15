package src.connectricity;

public class Controller {
    private Player player;
    private Map map;
    private CircuitMonitor cm;
    private boolean continuing = true;

    public Controller (Player player, Map map){
        this.player = player;
        this.map = map;
        cm = new CircuitMonitor(map);
    }

    public void receiveCommand(String key){
    /**
     * Recebe uma string com a tecla pressionada pelo jogador
     * Executa o método correto a partir do comando utilizado
     */
        int xIndex = player.getPosition()[0];
        int yIndex = player.getPosition()[1];

        Square curSquare = map.getSquare(xIndex, yIndex);

        if(key.equalsIgnoreCase("a")){
            move(xIndex-1, yIndex, curSquare);
        }
        else if(key.equalsIgnoreCase("d")){
            move(xIndex+1, yIndex, curSquare);
        }
        else if(key.equalsIgnoreCase("s")){
            move(xIndex, yIndex+1, curSquare);
        }
        else if(key.equalsIgnoreCase("w")){
            move(xIndex, yIndex-1, curSquare);
        }
        else if(key.equalsIgnoreCase("q")){
            quit();
        }
        else if(key.equalsIgnoreCase("f")){
            if (curSquare.checkWire()) {
                takeWire(curSquare);
            } else {
                placeWire(curSquare);
            }
            cm.setPotentials();
            map.manageExits();
        }
        else if(key.equalsIgnoreCase("r")){
            if (curSquare.checkResistor()) {
                takeResistor(curSquare);
            } else {
                placeResistor(curSquare);
            }
            cm.setPotentials();
            map.manageExits();
        }
        else{
            System.out.println("Comando invalido. Por favor digite outro comando.");
        }
    }

    public void move(int destSquare_x, int destSquare_y, Square curSquare){
      /**
     * Se possivel, realiza o movimento do jogador. 
     * Tambem checa se o jogador atingiu uma saida aberta para ganhar o jogo
     */

        if (map.invalidMove(destSquare_x, destSquare_y)) {
            System.out.println("MOVIMENTO INVALIDO!");
            return;
        }

        Square destSquare = map.getSquare(destSquare_x, destSquare_y);

        
        curSquare.removePlayer();
        player.setPosition(destSquare_x, destSquare_y);
        destSquare.setEntity(player);
        if(destSquare.checkExit() && destSquare.getExit().getOpen()){
            win();
        }
        else{
            printMap();
        }
    }

    public void takeWire(Square curSquare) {
    /**
     * Se possivel, retira um fio da posicao atual e o coloca no inventario do jogador.
     */
        if (curSquare.checkWire() && player.hasWireSpace()) {
            Wire wire = curSquare.removeWire();
            player.storeWire(wire);
        }
        else {
            System.out.println("Não há condutores ai ou seu inventario esta cheio");
        }
    }

    public void placeWire(Square curSquare) {
    /**
     * Se possivel, retira um fio do inventário do jogador e o coloca na posicao atual
     */
        if (curSquare.emptySquare()) {
            Wire wire = player.spendWire();
            if (wire != null) {
                curSquare.setEntity(wire);
            }
            else {
                System.out.println("Voce nao tem fios"); 
            }
        }
        else {
            System.out.println("Nao eh possivel colocar fio ai"); 
        }
    }

    public void takeResistor(Square curSquare) {
    /**
     * Se possivel, retira um resistor da posicao atual e o coloca no inventario do jogador.
     */
        if (curSquare.checkResistor() && player.hasResistorSpace()) {
            Resistor resistor = curSquare.removeResistor();
            player.storeResistor(resistor);
        }
        else {
            System.out.println("Não há resistores ai ou seu inventario esta cheio");
        }
    }

    public void placeResistor(Square curSquare) {
    /**
     * Se possivel, retira um resistor do inventário do jogador e o coloca na posicao atual
     */
        if (curSquare.emptySquare()) {
            Resistor resistor = player.spendResistor();
            if (resistor != null) {
                curSquare.setEntity(resistor);
            }
            else {
                System.out.println("Voce nao tem resistores"); 
            }
        }
        else {
            System.out.println("Nao eh possivel colocar resistor ai"); 
        }
    }

    public void printMap() {
        String[][] matrix = map.getMatrix();
        int[] size = map.getSize();
        int xSize = size[0];
        int ySize = size[1];
        for (int i = 0; i < ySize; i++){
            System.out.print("\t");
            for (int j = 0; j < xSize; j++){
                System.out.print(" " + matrix[i][j]);
            }
            System.out.print("\n");
        }
    }

    private void printMessage(String mesage){
        System.out.println(mesage);
    }

    public void quit(){
        printMessage("Volte sempre !");
        continuing = false;
    }

    public void win(){
        printMap();
        printMessage("Voce ganhou =D !!!");
        continuing = false;
    }

    public boolean getContinuing(){
        return continuing;
    }
}
