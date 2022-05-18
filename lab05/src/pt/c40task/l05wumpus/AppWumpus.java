package pt.c40task.l05wumpus;

import java.util.Scanner;

public class AppWumpus {

   public static void main(String[] args) {
      AppWumpus.executaJogo(
            (args.length > 0) ? args[0] : null,
            (args.length > 1) ? args[1] : null,
            (args.length > 2) ? args[2] : null);
   }
   
   public static void executaJogo(String arquivoCaverna, String arquivoSaida, String arquivoMovimentos) {
      Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);
      Scanner keyboard = new Scanner(System.in);

      MontadorCaverna montador = new MontadorCaverna(tk);
      Caverna mapa = montador.geraMapa();
      Heroi heroi = montador.getHeroi();
      ControleJogo ctrl = new ControleJogo(heroi, mapa, tk);

      String movements = null;
      String tecla;

      boolean endMovements = false;

      if (tk.getMoveStr() != null){
         movements = tk.retrieveMovements();
      }
      
      while(ctrl.getContinua() && !endMovements){
         if(movements != null){
            tecla = Character.toString(movements.charAt(0));

            if(movements.length() == 1)
               endMovements = true;
            
            if(movements.length() > 1)
               movements = movements.substring(1);
         }
         else{
            tecla = keyboard.nextLine();
         }

         ctrl.recebeComando(tecla);
      }
      
      keyboard.close();
      tk.stop();
   }

}
