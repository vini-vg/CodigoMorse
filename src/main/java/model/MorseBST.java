package main.java.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MorseBST {
    private Node root;

    public static class Node {
        public char letter;
        public Node left;
        public Node right;

        public Node(char letter) {
            this.letter = letter;
            this.left = null;
            this.right = null;
        }
    }

    public MorseBST() {
        this.root = null;
        initializeTree();
    }

    private void initializeTree() {
        // Construção manual da árvore Morse
        insert('E', ".");
        insert('T', "-");

        insert('I', "..");
        insert('A', ".-");
        insert('N', "-.");
        insert('M', "--");

        insert('S', "...");
        insert('U', "..-");
        insert('R', ".-.");
        insert('W', ".--");
        insert('D', "-..");
        insert('K', "-.-");
        insert('G', "--.");
        insert('O', "---");

        insert('H', "....");
        insert('V', "...-");
        insert('F', "..-.");
        insert('L', ".-..");
        insert('P', ".--.");
        insert('J', ".---");
        insert('B', "-...");
        insert('X', "-..-");
        insert('C', "-.-.");
        insert('Y', "-.--");
        insert('Z', "--..");
        insert('Q', "--.-");
    }

    public void insert(char letter, String morseCode) {
        root = insertRecursive(root, letter, morseCode, 0);
    }

    private Node insertRecursive(Node current, char letter, String morseCode, int index) {
        if (current == null) {
            current = new Node(' ');
        }

        if (index == morseCode.length()) {
            current.letter = letter;
            return current;
        }

        char code = morseCode.charAt(index);
        if (code == '.') {
            current.left = insertRecursive(current.left, letter, morseCode, index + 1);
        } else if (code == '-') {
            current.right = insertRecursive(current.right, letter, morseCode, index + 1);
        }

        return current;
    }

    public char decodeCharacter(String morseCode) {
        Node current = root;
        for (int i = 0; i < morseCode.length(); i++) {
            char code = morseCode.charAt(i);
            if (code == '.') {
                current = current.left;
            } else if (code == '-') {
                current = current.right;
            }
            if (current == null) {
                return '?';
            }
        }
        return current.letter;
    }

    public String decodeWord(String morseWord) {
        String[] morseChars = morseWord.trim().split(" ");
        StringBuilder result = new StringBuilder();

        for (String morseChar : morseChars) {
            result.append(decodeCharacter(morseChar));
        }
        return result.toString();
    }

    public String encodeCharacter(char character) {
        StringBuilder morseCode = new StringBuilder();
        if (findPath(root, Character.toUpperCase(character), morseCode)) {
            return morseCode.toString();
        }
        return "?";
    }

    public String encode(String text) {
        StringBuilder morse = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            if (c == ' ') {
                morse.append(" ");
            } else {
                String code = encodeCharacter(c);
                morse.append(code).append(" ");
            }
        }
        return morse.toString().trim();
    }

    private boolean findPath(Node node, char target, StringBuilder path) {
        if (node == null) return false;

        if (node.letter == target) return true;

        if (findPath(node.left, target, path)) {
            path.insert(0, ".");
            return true;
        }

        if (findPath(node.right, target, path)) {
            path.insert(0, "-");
            return true;
        }

        return false;
    }

    public void drawTree(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        drawNode(gc, root, canvas.getWidth() / 2, 40, canvas.getWidth() / 4);
    }

    private void drawNode(GraphicsContext gc, Node node, double x, double y, double xOffset) {
        if (node == null) return;

        gc.strokeOval(x - 15, y - 15, 30, 30);
        gc.strokeText(String.valueOf(node.letter == ' ' ? ' ' : node.letter), x - 5, y + 5);

        if (node.left != null) {
            double newX = x - xOffset;
            double newY = y + 80;
            gc.strokeLine(x, y + 15, newX, newY - 15);
            drawNode(gc, node.left, newX, newY, xOffset / 2);
        }

        if (node.right != null) {
            double newX = x + xOffset;
            double newY = y + 80;
            gc.strokeLine(x, y + 15, newX, newY - 15);
            drawNode(gc, node.right, newX, newY, xOffset / 2);
        }
    }
}