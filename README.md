# Morse-Teste

## Como rodar:

- Clone o repositório
- Abra com um editor de código que preferir
- Compile o Main.java
  ```java
  javac Main.java
  ```
- Rode o Main.java
    ```java
  java Main.java
  ```

## Explicação do código

### Main.java

```java

import java.util.Scanner;

// Programa principal, interface, para manipular a Árvore Binária de Código Morse
// mostra menu, lê entradas do usuário
// e chama os métodos da classe ArvoreBinariaMorse (inicializar, traduzir, buscar, inserir, remover)
public class Main {
    public static void main(String[] args) {
        // Cria a estrutura de dados e prepara os nós iniciais
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();
        arvore.inicializar();

        // Leitor simples de entrada padrão 
        Scanner leitor = new Scanner(System.in);

        // Controla o laço do menu (0 = sair)
        int opcao = 1;

        // Loop principal: exibe o menu enquanto o usuário não escolher 0
        while (opcao != 0) {
            // Mostra as operações disponíveis
            System.out.println("[0] Sair");
            System.out.println("[1] Traduzir caracter para morse");
            System.out.println("[2] Traduzir morse para caracter");
            System.out.println("[3] Inserir caracter");
            System.out.println("[4] Remover morse");

            System.out.print("Digite sua escola: ");
            // Lê um número para a opção escolhida
            opcao = leitor.nextInt();
            // IMPORTANTE: nextInt() NÃO consome o '\n' do Enter.
            // A linha abaixo consome essa quebra de linha pendente,
            // evitando que o próximo nextLine() retorne string vazia.
            leitor.nextLine();

            // [1] Dado um caractere (ex.: 'S'), imprime seu código morse (ex.: "...")
            if (opcao == 1) {
                System.out.print("Digite o caracter: ");
                String caracter = leitor.nextLine();

                // traduzir(raiz, caracter): percorre a árvore para encontrar o caminho (pontos/traços)
                // correspondente ao caractere informado.
                System.out.println(arvore.traduzir(arvore.raiz, caracter));
            }
            // [2] Dado um código morse (ex.: "..."), imprime o caractere correspondente (ex.: 'S')
            else if (opcao == 2) {
                System.out.print("Digite o codigo morse: ");
                String entrada = leitor.nextLine();

                // buscar(codigo): percorre esquerda para '.' e direita para '-'
                // e retorna o símbolo armazenado no nó alcançado.
                System.out.println(arvore.buscar(entrada));
            }
            // [3] Insere um novo mapeamento (código morse -> caractere) na árvore
            else if (opcao == 3) {
                System.out.print("Digite o codigo morse: ");
                String codigo = leitor.nextLine();

                System.out.print("\nDigite o caracter: ");
                String caracter = leitor.nextLine();

                // inserir(codigo, caracter): caminha conforme '.' e '-' criando nós se necessário
                // e grava o caractere no nó alvo.
                arvore.inserir(codigo, caracter);
            }
            // [4] Remove o símbolo associado a um determinado código morse
            else if (opcao == 4) {
                System.out.print("Digite o codigo: ");
                String codigo = leitor.nextLine();

                // remover(codigo): percorre até o nó indicado e elimina o caractere daquele nó
                // (a estrutura do caminho permanece).
                arvore.remover(codigo);
            }
            // Qualquer valor diferente de 0–4 apenas reexibe o menu no próximo ciclo
        }

        // Libera o scanner e encerra o programa
        leitor.close();
    }
}

```


### Node.java

```java


// Node da Árvore Binária de Código Morse
// Cada nó guarda um caractere (símbolo traduzido) e duas referências:
//  - esquerda  -> caminho para PONTO ('.')
//  - direita   -> caminho para TRAÇO  ('-')
// A árvore é percorrida usando '.' para ir à esquerda e '-' para ir à direita.

public class Node {
    // Símbolo armazenado neste nó (ex.: "S", "O", ou string vazia quando ainda não definido).
    public String caractere;

    // Filho da esquerda: representa seguir um PONTO ('.') a partir deste nó.
    public Node esquerda; // '.'

    // Filho da direita: representa seguir um TRAÇO ('-') a partir deste nó.
    public Node direita;  // '-'

    // Construtor: cria um nó com o caractere informado e sem filhos inicialmente.
    public Node(String c) {
        this.caractere = c;
        this.esquerda = null;
        this.direita = null;
    }
}


```


### ArvoreBinariaMorse.java

``` java

// Estrutura de Árvore Binária para Código Morse
// Convenção usada:
//  - '.' (ponto)  => segue para a ESQUERDA
//  - '-' (traço)  => segue para a DIREITA
// Cada nó guarda um "caractere" (String) que representa a letra/número naquele ponto do caminho.

public class ArvoreBinariaMorse {
    public Node raiz; // nó raiz da árvore (vazio; apenas ponto de partida)

    public ArvoreBinariaMorse() {
        this.raiz = new Node(""); // raiz sem caractere associado
    }

    // Pré-carrega a árvore com o alfabeto A–Z e dígitos 0–9
    // Para cada código, caminha criando nós e grava o caractere no nó final
    public void inicializar() {
        // Alfabeto
        inserir(".-", "A");
        inserir("-...", "B");
        inserir("-.-.", "C");
        inserir("-..", "D");
        inserir(".", "E");
        inserir("..-.", "F");
        inserir("--.", "G");
        inserir("....", "H");
        inserir("..", "I");
        inserir(".---", "J");
        inserir("-.-", "K");
        inserir(".-..", "L");
        inserir("--", "M");
        inserir("-.", "N");
        inserir("---", "O");
        inserir(".--.", "P");
        inserir("--.-", "Q");
        inserir(".-.", "R");
        inserir("...", "S");
        inserir("-", "T");
        inserir("..-", "U");
        inserir("...-", "V");
        inserir(".--", "W");
        inserir("-..-", "X");
        inserir("-.--", "Y");
        inserir("--..", "Z");

        // Números
        inserir("-----", "0");
        inserir(".----", "1");
        inserir("..---", "2");
        inserir("...--", "3");
        inserir("....-", "4");
        inserir(".....", "5");
        inserir("-....", "6");
        inserir("--...", "7");
        inserir("---..", "8");
        inserir("----.", "9");
    }

    // Inserção iterativa: percorre o caminho dado por "codigo" a partir da raiz
    // Para '.' vai para a esquerda; para '-' vai para a direita
    // Se o elo (filho) não existir ainda, cria um Node vazio ("")
    // Ao final, grava "caractere" no nó alcançado
    public void inserir(String codigo, String caractere) {
        Node atual = this.raiz;
        int i = 0;
        while (i < codigo.length()) {
            char simbolo = codigo.charAt(i);
            if (simbolo == '.') {
                if (atual.esquerda == null) {
                    atual.esquerda = new Node("");
                }
                atual = atual.esquerda;
            } 
            else if (simbolo == '-') {
                if (atual.direita == null) {
                    atual.direita = new Node("");
                }
                atual = atual.direita;
            }
            i = i + 1;
        }
        // No fim do caminho, associa o caractere ao nó.
        atual.caractere = caractere;
    }

    // Busca/decodificação iterativa: recebe uma string com um ou mais códigos Morse.
    // Regras:
    //  - '.' caminha para a esquerda; '-' caminha para a direita
    //  - ' ' (espaço) separa letras: ao encontrar espaço, anexa o caractere acumulado e volta para a raiz
    // Retorna a frase resultante (letras concatenadas).
    public String buscar(String codigo) {
        Node atual = this.raiz;
        boolean ultimoEhEspaco = false; // evita duplicar quando há múltiplos espaços
        String frase = "";

        for (int i = 0; i < codigo.length(); i++) {
            char simbolo = codigo.charAt(i);

            // Quando encontra espaço, fecha a letra atual (se houver) e reseta na raiz
            if (simbolo == ' ') {
                if (!ultimoEhEspaco && atual != null && atual.caractere != null) {
                    frase = frase + atual.caractere;
                }
                atual = this.raiz;
                ultimoEhEspaco = true;
                continue;
            }

            // Para próximo símbolo útil, marca que não estamos em espaço
            ultimoEhEspaco = false;

            // Caminho pelo código: '.' (esquerda) e '-' (direita)
            if (simbolo == '.') {
                if (atual.esquerda == null) {
                    return ""; // caminho inválido → não encontrado
                }
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                if (atual.direita == null) {
                    return ""; // caminho inválido → não encontrado
                }
                atual = atual.direita;
            }
        }

        // Ao final do laço, ainda falta anexar a última letra (pois não termina com espaço)
        frase = frase + atual.caractere;

        return frase; 
    }

    // Tradução recursiva (caractere → código Morse):
    // Procura "alvo" na árvore; quando encontra, retorna "" e vai empilhando
    // os símbolos ao "subir" da recursão:
    //  - Se veio da esquerda, prefixa '.'
    //  - Se veio da direita, prefixa '-'
    // Se não encontrar no ramo, retorna null.
    public String traduzir(Node raiz, String alvo) {
        if (raiz == null) return null;

        // Achou o caractere neste nó: ponto de parada
        if (raiz.caractere.equals(alvo)) return "";

        // Tenta na esquerda (ponto)
        String caminhoEsq = traduzir(raiz.esquerda, alvo);
        if (caminhoEsq != null) return "." + caminhoEsq;

        // Tenta na direita (traço)
        String caminhoDir = traduzir(raiz.direita, alvo);
        if (caminhoDir != null) return "-" + caminhoDir;

        // Não achou neste sub-árvore
        return null;
    }

    // Remoção iterativa: percorre o caminho do código e,
    // se o nó existir, "apaga" o caractere (deixa o nó estruturalmente lá).
    public void remover(String codigo) {
        Node atual = this.raiz;
        int i = 0;
        while (i < codigo.length()) {
            char simbolo = codigo.charAt(i);
            if (simbolo == '.') {
                if (atual.esquerda == null) {
                    return; // caminho inexistente → nada a remover
                }
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                if (atual.direita == null) {
                    return; // caminho inexistente → nada a remover
                }
                atual = atual.direita;
            }
            i = i + 1;
        }
        // "Remove" apenas o conteúdo do nó (mantém estrutura para outros códigos)
        atual.caractere = "";
    }

    // Exibição hierárquica (pré-ordem) para depuração/visualização.
    // "nivel" controla indentação. Mostra o caractere do nó e então seus filhos.
    public void exibir(Node nodo, int nivel) {
        if (nodo == null) {
            return;
        }
        String espacos = "";
        int i = 0;
        while (i < nivel) {
            espacos = espacos + "  ";
            i = i + 1;
        }
        System.out.println(espacos + "(" + nodo.caractere + ")");
        exibir(nodo.esquerda, nivel + 1); // ramo do ponto '.'
        exibir(nodo.direita, nivel + 1);  // ramo do traço '-'
    }
}


```
