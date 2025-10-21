public class ArvoreBinariaMorse {
    public Node raiz;

    public ArvoreBinariaMorse() {
        this.raiz = new Node(""); 
    }

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

    // Inserção iterativa
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
        atual.caractere = caractere;
    }

    // Busca iterativa
    public String buscar(String codigo) {
        Node atual = this.raiz;

        boolean ultimoEhEspaco = false;

        String frase = "";
        for (int i = 0; i < codigo.length(); i++) {
            char simbolo = codigo.charAt(i);

            //  System.out.println(simbolo);

            if(simbolo == ' '){
                if(!ultimoEhEspaco && atual != null && atual.caractere != null){
                    frase = frase + atual.caractere;
                }
                atual = this.raiz;
                ultimoEhEspaco = true;
                continue;
            }

            ultimoEhEspaco = false;
            if (simbolo == '.') {
                if (atual.esquerda == null) {
                    return "";
                }
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                if (atual.direita == null) {
                    return "";
                }
                atual = atual.direita;
            }
            
        }

        frase = frase + atual.caractere; // último caractere

        return frase; 
    }

    public void remover(String codigo) {
        Node atual = this.raiz;
        int i = 0;
        while (i < codigo.length()) {
            char simbolo = codigo.charAt(i);
            if (simbolo == '.') {
                if (atual.esquerda == null) {
                    return;
                }
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                if (atual.direita == null) {
                    return;
                }
                atual = atual.direita;
            }
            i = i + 1;
        }
        atual.caractere = "";
    }

    // Exibição hierárquica da árvore
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
        exibir(nodo.esquerda, nivel + 1);
        exibir(nodo.direita, nivel + 1);
    }
}