import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();
        arvore.inicializar();
        Scanner leitor = new Scanner(System.in);

        int opcao = 1;

        while(opcao != 0){
            System.out.println("[0] Sair\n[1] Traduzir frase para morse\n[2] Traduzir morse para caracter\n[3] Inserir caracter\n[4] Remover morse\n[5] Exibir Arvore");

            System.out.print("Digite sua escola: ");
            opcao = leitor.nextInt();
            leitor.nextLine();

            if(opcao == 1){
                System.out.print("Digite o caracter: ");
                String frase = leitor.nextLine();

                System.out.println(arvore.traduzirFrase(frase));
            }
            else if(opcao == 2){
                System.out.print("Digite o codigo morse: ");
                String entrada = leitor.nextLine();

                System.out.println(arvore.buscar(entrada));
            }

            else if(opcao == 3){
                System.out.print("Digite o codigo morse: ");
                String codigo = leitor.nextLine();

                System.out.print("\nDigite o caracter: ");
                String caracter = leitor.nextLine();

                arvore.inserir(codigo, caracter);
            }

            else if(opcao == 4){
                System.out.print("Digite o codigo: ");
                String codigo = leitor.nextLine();

                arvore.remover(codigo);
            }

            else if(opcao == 5){
                arvore.exibir(arvore.raiz, 0);
            }
        }

    }
}