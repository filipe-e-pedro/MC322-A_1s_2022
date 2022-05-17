package pt.c40task.l05wumpus;

public class ControleJogo {
    private Heroi heroi;
    private Caverna mapa;
    private Toolkit tk;
}

public void recebeComando(String tecla){
    int posicao_x = heroi.getPosicao[0];
    int posicao_y = heroi.getPosicao[1];
    if(tecla.equalsIgnoreCase("w") && mapa.checaSala(posicao_x, posicao_y+1)){
        move(posicao_x, posicao_y+1);
    }
    else if(tecla.equalsIgnoreCase("s") && mapa.checaSala(posicao_x, posicao_y-1)){
        move(posicao_x, posicao_y-1);
    }
    else if(tecla.equalsIgnoreCase("d") && mapa.checaSala(posicao_x+1, posicao_y)){
        move(posicao_x+1, posicao_y);
    }
    else if(tecla.equalsIgnoreCase("a") && mapa.checaSala(posicao_x-1, posicao_y)){
        move(posicao_x-1, posicao_y);
    }
}

public void move(int destino_x, int destino_y){
    int inicio_x = heroi.getPosicao[0];
    int inicio_y = heroi.getPosicao[1];
    Sala atual = mapa.getSala(inicio_x, inicio_y);
    Sala destino = mapa.getSala(destino_x, destino_y)
    if(heroi.flechaEquipada()){
        atiraFlecha(destino);
    }
    if(checaBuraco || checaWumpus){
        morre();
    }
    else{
        atual.removeHeroi();
        heroi.setPosicao(destino_x, destino_y);
        destino.adicionaHeroi(heroi);
        destino.revelaSala();
    }
}