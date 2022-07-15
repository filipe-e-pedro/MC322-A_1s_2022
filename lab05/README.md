# Lab05 - Mundo de Wumpus

# Destaque codigo

```java
public void setComponente(Wumpus wumpus) { 
    this.wumpus = wumpus; 
    componentesNome[0] = "W"; 
} 
public void setComponente(Ouro ouro) { 
    this.ouro = ouro; 
    componentesNome[0] = "O"; 
} 
```

Neste lab a Sala foi implementada de maneira a facilitar a criação de novos componentes. Para isso, criamos atributos na Sala que representam cada componente e implementamos o método setComponente por sobrecarga, de maneira que independente do componente passado, ele automaticamente conecta a sala ao componente de maneira correta.

## Arquivos Java sobre Mundo de Wumpus
[Arquivos Java](https://github.com/filipe-e-pedro/MC322-A_1s_2022/tree/main/lab05/src/pt/c40task/l05wumpus)
