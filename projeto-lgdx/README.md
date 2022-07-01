# Connectricity

# Descrição

> Connectricity é um jogo celular com o objetivo de levar energia às baterias para abrir a saída da fase. Para isso, é necessário conectar os geradores às baterias por meio de fios, e uitlizar resistores para regular o potencial fornecido da maneira correta.
Esse é o projeto com interface gráfica, o arquivo .java main é o DesktopLauncher.java que pode ser encontrado nessa [pasta](https://github.com/filipe-e-pedro/MC322-A_1s_2022/tree/main/projeto-lgdx/desktop/src/src/connectricity).

# Equipe
* Filipe Franco Ferreira - 251027
* Pedro Henrique Peraçoli Pereira Ceccon - 247327

# Arquivo Executável do Jogo

Link para o .jar: [connectricity.jar](https://drive.google.com/drive/folders/1WL17o8a_5FCVKHCNIZUWu5nxxDBHZqtz?usp=sharing)

# Slides do Projeto

## Slides da Prévia
https://docs.google.com/presentation/d/1GOxuunC9XyMGQk7AxyWvs8kTky6LrSfkABKOKZ0tpHU/edit#slide=id.g11cae6dbe76_0_0

## Slides da Apresentação Final
https://docs.google.com/presentation/d/1YOW_dtf9_ErJ-F-U-c1zVL32a357mV5I6yyOJDY2s6o/edit#slide=id.g13acd6c184b_0_15

# Diagramas

## Diagrama Geral das classes do jogo

![Diagrama de classes](https://github.com/filipe-e-pedro/MC322-A_1s_2022/blob/main/imagens/diagrama_classes.png)

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