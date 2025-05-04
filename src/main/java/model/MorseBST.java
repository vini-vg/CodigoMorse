package main.java.model;

import java.awt.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class MorseBST {
    private Node root;

    public MorseBST(){
        // inicia a arvore com a raiz vazia
        this.root = null;
    }

    public void insert(char letter, String morseCode){
        root = insertRecursive(root,letter,morseCode, 0);
    }

    private Node insertRecursive(Node current, char letter, String morseCode, int index) {
        if (current == null) {
            if (index < morseCode.length()) {
                current = new Node(' '); // Nó intermediário
            } else {
                return new Node(letter); // Nó folha com a letra
            }
        }

        if (index < morseCode.length()){
            char code = morseCode.charAt(index);
            if (code == '.'){
                current.left = insertRecursive(current.left, letter, morseCode,index + 1);
            } else if (code == '-'){
                current.right = insertRecursive(current.right, letter, morseCode, index + 1);
            }
        }

        return current;

    }

    public char decodeCharacter(String morseCode){
        Node current = root;
        for(int i = 0; i < morseCode.length(); i++){
            char code = morseCode.charAt(i);
            if (code == '.'){
                current = current.left;
            } else if (code == '-'){
                current = current.right;
            }
            if (current == null){
                return '?'; // não achou caracter
            }
        }
        return current.letter;
    }

    public String decodeWord(String morseWord){
        String[] morseChars = morseWord.split("");
        StringBuilder result = new StringBuilder();

        for (String morseChar : morseChars){
            result.append(decodeCharacter(morseChar));
        }
        return result.toString();
    }

    public int getHeight(){
        return  getHeight(root);
    }

    private int getHeight(Node node){
        if(node == null){
            return 0;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private void drawTree(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        gc.setStrokek(Color.BLACK);
        gc.setLineWidth(2);

        drawTree(gc, root, canvas,getWidth() / 2, 40, canvas,getWidth() / 4,1);
    }

    private void drawNode(GraphicsContext gc, Node node, double x, double y, double xOffset, int level){
        if(node == null){
            return;
        }

        gc.strokeOval(x - 15, y - 15, 30, 30);

        gc.strokeText(String.valueOf(node.letter == ' '?' ':node.letter), x - 5, y + 5 );

        // Desenha a subárvore esquerda (pontos)
        if (node.left != null){
            double newX = x - xOffset;
            double newY = y - 120; // Espaçamento vertical
            gc.strokeLine(x, y + 15, newX, newY - 15); // Linha de conexão
            drawNode(gc, node.left, newX, newY, xOffset / 2, level + 1);
        }

        // Desenha a subárvore direita (traços)
        if (node.right != null) {
            double newX = x + xOffset;
            double newY = y + 120;  // Espaçamento vertical
            gc.strokeLine(x, y + 15, newX, newY - 15);  // Linha de conexão
            drawNode(gc, node.right, newX, newY, xOffset / 2, level + 1);
        }
    }
}
