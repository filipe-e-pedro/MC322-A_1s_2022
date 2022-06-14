package connectricity;

public class App {

    public static void main(String[] args){
        App.executaJogo();
    }

    public static void executaJogo(){
        Toolkit tk = new Toolkit();
        MapMaker montador = new MapMaker(1, tk);
        montador.printLevelInfo();
        montador.makeMatrix(12, 12);
        montador.printLevelMatrix();
    }
}
