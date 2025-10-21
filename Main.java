public class Main {
    public static void main(String[] args) {
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();
        arvore.inicializar();

        // Inserção de exemplos
        arvore.inserir("...", "S");
        arvore.inserir("---", "O");

        // Testes como no enunciado
        //  arvore.buscar("...");         // Saída: S
        //  arvore.buscar("---");         // Saída: O
        arvore.buscar("... --- ... --- ...");   // Saída: SOSOS

        // Testando remoção
        // System.out.println("Buscar '---' -> '" + arvore.buscar("---") + "'");
        // System.out.println("\nRemovendo 'O' (---):");
        // arvore.remover("---");
        // System.out.println("Buscar '---' -> '" + arvore.buscar("---") + "'");

    }
}