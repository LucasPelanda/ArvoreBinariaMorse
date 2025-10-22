public class Main {
    public static void main(String[] args) {
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();
        arvore.inicializar();


        // Testes como no enunciado 
        System.out.println(arvore.buscar("..."));         // Saída esperada: S
        System.out.println(arvore.buscar("---"));         // Saída esperada: O
        System.out.println(arvore.buscar("... --- ..."));   // Saída esperada: SOS
        
        // Teste de remoção (agora imprimindo o retorno)
        System.out.println("\n--- Teste de Remocao ---");
        System.out.println("Buscando '---' (O): " + arvore.buscar("---"));
        arvore.remover("---");
        System.out.println("Removido '---' (O)");
        System.out.println("Buscando '---' (vazio): " + arvore.buscar("---"));

        System.out.println(arvore.traduzir(arvore.raiz, "A"));
    }
}