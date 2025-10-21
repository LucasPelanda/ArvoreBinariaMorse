public class Node {
    public String caractere;
    public Node esquerda; // p.
    public Node direita;  // -

    public Node(String c) {
        this.caractere = c;
        this.esquerda = null;
        this.direita = null;
    }
}