package connectricity;
import java.util.Scanner;

public class App {

    public static void main(String[] args){
        App.executeGame();
    }

    public static void executeGame(){
        Toolkit tk = new Toolkit();
        MapMaker maker = new MapMaker(2, tk);

        try {
            maker.invalidMap();
        } catch (InvalidMapException exception) {
            System.err.println(exception.getMessage());
            return;
        }

        Scanner keyboard = new Scanner(System.in);

        maker.createMap();
        
        Map map = maker.getMap();
        Player player = maker.getPlayer();
        Controller ctrl = new Controller(player, map, tk);
        String tecla;

        ctrl.printMap();

        while(ctrl.getContinuing()){
            tecla = keyboard.nextLine();
            ctrl.receiveCommand(tecla);
        }
        
        keyboard.close();
    }
}