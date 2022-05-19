package pt.c40task.l05wumpus;

public class ControleJogo {
    private Heroi heroi;
    private Caverna mapa;
    private Toolkit tk;
    private String player = "Sting";
    private int score = 0;
    private boolean continua = true;

    public ControleJogo(Heroi heroi, Caverna mapa, Toolkit tk){
        this.heroi = heroi;
        this.mapa = mapa;
        this.tk = tk;
    }

    public void setPlayer(String player){
        this.player = player;
    }

    public void recebeComando(String tecla){
        int posicao_x = heroi.getPosicao()[0];
        int posicao_y = heroi.getPosicao()[1];

        Sala salaAtual = mapa.getSala(posicao_x, posicao_y);

        if(tecla.equalsIgnoreCase("a") && posicao_x > 0){
            move(posicao_x-1, posicao_y, salaAtual);
        }
        else if(tecla.equalsIgnoreCase("d") && posicao_x < 3){
            move(posicao_x+1, posicao_y, salaAtual);
        }
        else if(tecla.equalsIgnoreCase("s") && posicao_y < 3){
            move(posicao_x, posicao_y+1, salaAtual);
        }
        else if(tecla.equalsIgnoreCase("w") && posicao_y > 0){
            move(posicao_x, posicao_y-1, salaAtual);
        }
        else if(tecla.equalsIgnoreCase("k")){
            if(!heroi.getPossuiFlecha()){
            	imprimeMensagem("Sem flechas. Digite outro comando");
            }
            else{
            	if(heroi.getFlechaEquipada()){
                    imprimeMensagem("Sua flecha ja esta equipada. Digite outro comando");
                }
            	else {
	                heroi.equiparFlecha();
	                imprimeMensagem("Voce equipou a flecha.");
            	}
            }
        }
        else if(tecla.equalsIgnoreCase("c")){
            if(mapa.getSala(posicao_x, posicao_y).compMaisImportante() == "O"){
                heroi.pegaOuro();
                salaAtual.removeOuro();
                imprimeMensagem("Voce pegou o ouro.");
            }
            else
                imprimeMensagem("Sem ouro na sala. Digite outro comando.");
        }
        else if(tecla.equalsIgnoreCase("q")){
            sai();
        }
        else{
            System.out.println("Comando invalido. Por favor digite outro comando.");
        }
    }

    public void move(int destino_x, int destino_y, Sala salaAtual){
        Sala destino = mapa.getSala(destino_x, destino_y);
        score -= 15;

        if(heroi.getFlechaEquipada()){
            score += heroi.atiraFlecha(destino);
        }

        if(destino.checaBuraco() || destino.checaWumpus()){
            score -= 1000;
            salaAtual.removeHeroi();
            destino.revelaSala();
            perde();
        }

        else{
            salaAtual.removeHeroi();
            heroi.setPosicao(destino_x, destino_y);
            destino.adicionaHeroi(heroi);
            destino.revelaSala();
            if(condicaoGanhar()){
                vence();
            }
            else{
                imprimeCaverna(player, score);
                tk.writeBoard(mapa.getCaverna(), score, 'x');
            }
            if (continua)
                imprimeMensagem(destino.mensagemAuxilio());
        }
    }

    public void imprimeCaverna(String player, int score){
        String[][] matriz = mapa.getCaverna();
        for (int i = 0; i < 4; i++){
            System.out.print("\t");
            for (int j = 0; j < 4; j++){
                System.out.print(" " + matriz[i][j]);
            }
            System.out.print("\n");
        }
        System.out.println("Player: " + player);
        System.out.println("Score: " + score);
    }

    private void imprimeMensagem(String mensagem){
        System.out.println(mensagem);
    }

    public void perde(){
        imprimeCaverna(player, score);
        imprimeMensagem("Voce perdeu =(...");
        tk.writeBoard(mapa.getCaverna(), score, 'n');
        continua = false;
    }

    public void sai(){
        imprimeMensagem("Volte sempre !");
        continua = false;
    }

    public void vence(){
        score += 1000;
        imprimeCaverna(player, score);
        imprimeMensagem("Voce ganhou =D !!!");
        tk.writeBoard(mapa.getCaverna(), score, 'w');
        continua = false;
    }

    public boolean condicaoGanhar(){
        if(heroi.getPosicao()[0] == 0 && heroi.getPosicao()[1] == 0 && heroi.getOuro()){
            return true;
        }
        return false;
    }

    public boolean getContinua(){
        return continua;
    }
}