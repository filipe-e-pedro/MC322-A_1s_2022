package pt.c40task.l05wumpus;

public class ControleJogo {
    private Heroi heroi;
    private Caverna mapa;
    private Toolkit tk;
    private String player;
    private int score;

    public ControleJogo(Heroi heroi, Caverna mapa, Toolkit tk){
        this.heroi = heroi;
        this.mapa = mapa;
        this.tk = tk;
    }

    public void recebeComando(String tecla){
        int posicao_x = heroi.getPosicao()[0];
        int posicao_y = heroi.getPosicao()[1];

        Sala salaAtual = mapa.getSala(posicao_x, posicao_y);

        if(tecla.equalsIgnoreCase("w") && mapa.checaSala(posicao_x, posicao_y+1)){
            move(posicao_x, posicao_y+1, salaAtual);
        }
        else if(tecla.equalsIgnoreCase("s") && mapa.checaSala(posicao_x, posicao_y-1)){
            move(posicao_x, posicao_y-1, salaAtual);
        }
        else if(tecla.equalsIgnoreCase("d") && mapa.checaSala(posicao_x+1, posicao_y)){
            move(posicao_x+1, posicao_y, salaAtual);
        }
        else if(tecla.equalsIgnoreCase("a") && mapa.checaSala(posicao_x-1, posicao_y)){
            move(posicao_x-1, posicao_y, salaAtual);
        }
        else if(tecla.equalsIgnoreCase("k")){
            heroi.equiparFlecha();
            jogando();
            imprimeMensagem("Voce equipou a flecha.");
        }
        else if(tecla.equalsIgnoreCase("c")){
            if(mapa.getSala(posicao_x, posicao_y).compMaisImportante().getNome() == "O"){
                heroi.pegaOuro();
                salaAtual.removeOuro();
                jogando();
                imprimeMensagem("Voce pegou o ouro.");
            }
            else
                imprimeMensagem("Sem ouro na sala. Digite outro comando.");
        }
        else if(tecla.equalsIgnoreCase("q")){
            sai();
        }
    }

    public void move(int destino_x, int destino_y, Sala salaAtual){
        int inicio_x = heroi.getPosicao()[0];
        int inicio_y = heroi.getPosicao()[1];

        Sala destino = mapa.getSala(destino_x, destino_y);
        if(destino.checaBuraco() || destino.checaWumpus()){
            perde();
        }

        if(heroi.getFlechaEquipada()){
            heroi.atiraFlecha(destino);
        }

        else{
            salaAtual.removeHeroi();
            heroi.setPosicao(destino_x, destino_y);
            destino.adicionaHeroi(heroi);
            destino.revelaSala();
        }
    }

    private void imprimeCaverna(){
        String[][] matriz = mapa.getCaverna();
        for (int i = 0; i < 4; i++){
            System.out.print("\t");
            for (int j = 0; j < 4; ){
                System.out.print(" " + matriz[i][j]);
            }
            System.out.print("\n");
        }
    }

    private void imprimeMensagem(String mensagem){
        System.out.println(mensagem);
    }

    public void perde(){
        imprimeCaverna();
        imprimeMensagem("Voce perdeu =(...");
        tk.writeBoard(mapa.getCaverna(), score, 'L');
    }

    public void sai(){
        imprimeCaverna();
        imprimeMensagem("Volte sempre !");
        tk.writeBoard(mapa.getCaverna(), score, 'Q');
    }

    public void jogando(){
        imprimeCaverna();
        tk.writeBoard(mapa.getCaverna(), score, 'P');
    }

    public void vence(){
        imprimeCaverna();
        imprimeMensagem("Voce ganhou =D !!!");
        tk.writeBoard(mapa.getCaverna(), score, 'W');
    }
}