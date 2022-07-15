# Connectricity

# Descrição

> Connectricity é um jogo celular com o objetivo de levar energia às baterias para abrir a saída da fase. Para isso, é necessário conectar os geradores às baterias por meio de fios, e uitlizar resistores para regular o potencial fornecido da maneira correta.
Esse é o projeto com interface gráfica, o arquivo .java main é o DesktopLauncher.java que pode ser encontrado nessa [pasta](https://github.com/filipe-e-pedro/MC322-A_1s_2022/tree/main/projeto-lgdx/desktop/src/src/connectricity). A versão sem interface gráfica se encontra [aqui](https://github.com/filipe-e-pedro/MC322-A_1s_2022/tree/main/projeto).

# Equipe
* Filipe Franco Ferreira - 251027
* Pedro Henrique Peraçoli Pereira Ceccon - 247327

# Arquivo Executável do Jogo

Link para o .jar: [connectricity.jar](https://github.com/filipe-e-pedro/MC322-A_1s_2022/blob/main/projeto-lgdx/desktop/build/libs/connectricity.jar)

# Estrutura de Arquivos e Pastas

~~~
├─── README.md                                         <- apresentação do projeto
│
├─── assets                                            <- mídias usadas no projeto
|     └── maps_folder                                  <- Arquivos .csv contendo as fases do jogo
|
├─── core/src           
|     ├── build/classes/java/main/src/connectricity    <- arquivos em bytecode (.class)
|     └── src/src/connectricity                        <- arquivos-fonte do projeto (.java)
|
└─── desktop/src                              
        ├── build/classes/java/main/src/connectricity  <- arquivo do main em bytecode (.class)
        └── src/src/connectricity                      <- arquivo-fonte do main do projeto (.java)
~~~


## [maps_folder](https://github.com/filipe-e-pedro/MC322-A_1s_2022/tree/main/projeto-lgdx/assets/maps_folder)

Arquivos .csv contendo as fases do jogo.

## [src](https://github.com/filipe-e-pedro/MC322-A_1s_2022/tree/main/projeto-lgdx)

Projeto principal do jogo em java.

## [assets](https://github.com/filipe-e-pedro/MC322-A_1s_2022/tree/main/projeto-lgdx/assets)

Qualquer mídia usada no seu projeto: vídeo, imagens, animações, slides etc.


# Slides do Projeto

## Slides da Prévia
https://docs.google.com/presentation/d/1GOxuunC9XyMGQk7AxyWvs8kTky6LrSfkABKOKZ0tpHU/edit#slide=id.g11cae6dbe76_0_0

## Slides da Apresentação Final
https://docs.google.com/presentation/d/1YOW_dtf9_ErJ-F-U-c1zVL32a357mV5I6yyOJDY2s6o/edit#slide=id.g13acd6c184b_0_15

## Relatório de Evolução

Primeiro tentamos encontrar algum estilo de jogo que fosse do gosto de nós dois, e chegamos a conclusão que queríamos fazer algum jogo de puzzle. Concluímos que queríamos fazer algo relacionado a circuitos elétricos, que é relacionado ao nosso curso.

A ideia principal se manteve a mesma ao longo de todo o projeto: o objetivo principal do jogo seria alimentar o circuito de uma forma que abrisse a saída da sala. Na ideia original do jogo o controle dos circuitos seria feito considerando tensão e corrente, os componentes que poderiam interagir com o circuito seriam: fio, resistor, gerador, bateria, lâmpada e isolante. A ideia era existir um sistema de iluminação, que parte do mapa ficasse oculta até que uma lâmpada fosse acesa através do circuito. A ideia era que o jogador poderia se machucar, ou até morrer, caso uma corrente muito alta passasse pelo seu corpo. A ideia era ter um menu que você poderia selecionar a fase que deseja jogar, além de acessar as regras pelo menu.
	
A primeira ideia que descartamos foi o dano ao jogador, decidimos que a ideia do jogo era resolver o puzzle, não queríamos um jogo que punisse o jogador por decisões erradas. Depois descartamos a ideia do isolante, o propósito inicial principal dele seria fornecer alguma segurança ao jogador, mas como retiramos o potencial do jogador levar dano/morrer não teria muito sentido em manter ele no jogo. Aqui foi feita a primeira versão do jogo, que rodava no terminal, e basicamente era um personagem que andava em um espaço celular e podia interagir com os resistores e fios, mas não tinha a parte de conferir o circuito então não dava para fazer muita coisa.
	
Depois descartamos a ideia de que o controle fosse feito considerando a tensão e corrente do circuito, percebemos que seria uma lógica muito difícil de implementar e decidimos trabalhar apenas com potencial, que ainda se mostrou bem desafiador visto que circuitos poderiam ser montados de várias formas e, apesar de já termos descartado uma parte da concordância com a física de circuitos, ainda queríamos manter alguma semelhança com circuitos reais.A partir disso fizemos a segunda versão, que também rodava no terminal, e agora os componentes comunicavam entre si e era possível exercer a condição de vitória montando os circuitos corretamente.
	
A partir daqui percebemos que realmente estávamos ficando sem tempo, tínhamos bastante coisa para fazer ainda, mas com o prazo apertando e um dos membros doente decidimos que teríamos que descartar mais coisas. Decidimos não implementar a mecânica de luz e, com ela, as lâmpadas. Também percebemos que não teríamos tempo para implementar um menu. Daí veio a versão final do jogo que implementamos interface gráfica na versão anterior.

# Destaques de Código
Essa função cria uma matriz que representa todo o mapa e coloca o potencial certo em todos os condutores.
~~~java
private int[][] createPotentialMatrix(int numberOfRegions, ArrayList<String> potentials,  int[][] connections) {
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
~~~

Essa função conta quantos resistores tem em um determinado caminho do circuito para determinar o potencial dos condutores.

~~~java
private void countResistorPath(Queue<Integer> queue, int[] queued, int[] resistorPath, int[][] connections, int currentPotential) {
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
~~~

# Destaques de Orientação a Objetos
Foi utilizado polimorfismo por meio de uma classe abstrata “Conductor”.
Os geradores, baterias e fios são seus herdeiros.
Isso é importante para o uso dos métodos getConductor() e setPotentialLevel() em todos estes objetos ao fim do cálculo dos potenciais.

## Código do Destaque OO
~~~java
public abstract class Conductor extends Entity{

    protected int potentialLevel = 0;
    protected int maxPotential = 3;

    public Conductor (int xIndex, int yIndex, Map map){
		super(xIndex, yIndex, map);
	}
    
    public void setPotentialLevel(int level) {
        if (level >= 0 && level <= maxPotential) {
            potentialLevel = level;
        }
    }
}
~~~~

Código do CircuitMonitor utilizando polimorfismo para modificar os potenciais de cada condutor:
~~~java
public void setPotentials() {
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
~~~

# Destaques de Pattern
A classe CircuitMonitor serve como um observer, que sempre que um comando que modifica o circuito é acionado ele calcula os potenciais corretos para todos os condutores e se comunica indiretamente com cada um para mudar o valor de seu potencial.

## Código do Pattern
Codigo do Controller acionando o circuit monitor sempre que um comando que modifica o circuito é acionado:
~~~java
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
~~~

Código do método setPotentials() do CircuitMonitor, que modifica o potencial de cada condutor:
~~~java
public void setPotentials() {
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
~~~

Isso permite a fácil modificação do potencial de todos os condutores simultaneamente para o potencial correto.

# Conclusões e Trabalhos Futuros

Sentimos dificuldade em organizar a quantidade de arquivos que um projeto java acaba precisando, principalmente ao se trabalhar com o GitHub, que pode dar problemas de versões e conflitos. Gostaríamos de ter tido mais tempo e se organizado melhor com o tempo que tínhamos para conseguir fazer tudo o que queríamos.

Sentimos que aprendemos a utilidade prática dos conteúdos passados em aula, como com o polimorfismo, por exemplo, para definir os condutores, o que acabou facilitando muito no monitoramento dos circuitos e deixa mais fácil para implementar novos componentes caso seja do nosso interesse.

Apesar de não termos organizado nosso tempo de forma que conseguimos implementar tudo o que queríamos, ficamos satisfeitos com o resultado. Achamos que ficou um jogo que alguém pode se divertir jogando, e que roda de forma bem fluída.

Para possíveis futuras implementações queremos adicionar um menu principal, no qual seria possível ver as regras, selecionar o modo de jogo e selecionar qual mapa o usuário gostaria de jogar; queremos adicionar mais componentes, como a lâmpada, e a mecânica de iluminação; queremos adicionar um modo que o usuário cria o próprio mapa e o programa gere um arquivo .csv equivalente; e, principalmente, queremos fazer mais fases,  mais desafiadoras e divertidas.

# Diagramas

## Diagrama Geral das classes do jogo

![Diagrama de classes](https://github.com/filipe-e-pedro/MC322-A_1s_2022/blob/main/imagens/diagrama_classes.png)

O DesktopLauncher, localizado [aqui](https://github.com/filipe-e-pedro/MC322-A_1s_2022/tree/main/projeto-lgdx/desktop/src/src/connectricity) serve como main que ativa o App.

O arquivo csv contendo as informações do nível é lido pelo App.

O app aciona o MapMaker que irá, a partir das informações do csv, criar todas as entidades, os Squares, e o Map, além de conecta-los.

Quando o jogador envia um comando, o app o manda para o Controller, que irá executa-lo. 

Quando um comando que modifica o circuito (adicionar ou tirar resistores e fios) é executado pelo Controller, ele aciona o CircuitMonitor.

O CircuitMonitor então calcula o potencial de todos os condutores presentes no mapa e se comunica com o Map para indiretamente dar set nos potenciais de todos eles.


# Tutorial de como rodar o programa no Eclipse:
[![Tutorial Eclipse](https://github.com/filipe-e-pedro/MC322-A_1s_2022/blob/main/imagens/tutorial.PNG)](https://drive.google.com/drive/folders/1PGl_afuXQMtUjWaVt8GY6R_hzqNhJoDg?usp=sharing)

# Plano de Exceções

## Diagrama da hierarquia de exceções
> Diagrama das exceptions

![Hierarquia Exceções](https://github.com/filipe-e-pedro/MC322-A_1s_2022/blob/main/imagens/Exceptions.PNG)

## Descrição das classes de exceção

> Tabela das exceptions:

Classe | Descrição
----- | -----
InvalidMapException | Engloba todas as exceções que deixam o arquivo de mapa inválido.
WrongBatteryPotential | Indica que tem bateria com um potencial necessário inválido
WrongPlayerInventory | Indica que o inventário do jogador foi indicado de maneira inválida.
WrongPlayerNumber | Indica que o número de jogadores indicado no arquivo de mapa é inválido.
ZeroBatteries | Indica que não tem baterias representadas no arquivo de mapa.
ZeroExits | Indica que não tem saídas representadas no arquivo de mapa.
ZeroGenerators | Indica que não tem geradores representados no arquivo de mapa.
