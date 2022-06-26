package connectricity;
import java.util.Scanner;

public class App {

    public static void main(String[] args){
        App.executeGame();
    }

    public static void executeGame(){
        Scanner keyboard = new Scanner(System.in);
        Toolkit tk = new Toolkit();
        MapMaker maker = new MapMaker(2, tk);

        if (!maker.createMap()){
            System.out.println("Invalid level file");
        }
        else{
            Map map = maker.getMap();
            Player player = maker.getPlayer();
            Controller ctrl = new Controller(player, map, tk);
            String tecla;

            ctrl.printMap();

            while(ctrl.getContinuing()){
                tecla = keyboard.nextLine();
                ctrl.receiveCommand(tecla);
            }
        }
        keyboard.close();
    }
}