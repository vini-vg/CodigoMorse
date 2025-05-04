package main.java.model;

//configura a nó(lembre sempre que a esquerda e a direita são nulos)
public class Node {
    public char letter;
    public Node left;
    public Node right;

    public Node(char letter){
        this.letter = letter;
        this.left = null;
        this.right = null;

    }
}
