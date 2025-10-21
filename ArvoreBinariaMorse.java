public class ArvoreBinariaMorse {
    public Node raiz;

    public ArvoreBinariaMorse() {
        this.raiz = new Node(""); 
    }

    public void inicializar() { }

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
    public void buscar(String codigo) {
        Node atual = this.raiz;
        int j = 0;

        boolean ultimoEhEspaco = false;

        String[] frase = new String[codigo.length()];
        for (int i = 0; i < codigo.length(); i++) {
            char simbolo = codigo.charAt(i);

            //  System.out.println(simbolo);

            if(simbolo == ' '){
                if(!ultimoEhEspaco && atual != null && atual.caractere != null){
                    frase[j] = atual.caractere;
                }
                j++;
                atual = this.raiz;
                ultimoEhEspaco = true;
                continue;
            }

            ultimoEhEspaco = false;
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
            
        }

        frase[j] = atual.caractere; // último caractere

        for(int o = 0; o <= j; o++){
            System.out.println(frase[o]);
        }
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