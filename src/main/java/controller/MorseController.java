package main.java.controller;

import main.java.model.MorseBST;
import main.java.util.MorseCodeMap;

public class MorseController {
    private MorseBST morseTree;

    public MorseController() {
        morseTree = new MorseBST();
        initializeTree();
    }

    private void initializeTree() {
        // Inserir apenas letras de A-Z na árvore
        for (char c = 'A'; c <= 'Z'; c++) {
            morseTree.insert(c, MorseCodeMap.getMorseCode(c));
        }
    }

    public String encode(String text) {
        StringBuilder morse = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            if (c == ' ') {
                morse.append(" ");
            } else {
                String code = MorseCodeMap.getMorseCode(c);
                if (code != null) {
                    morse.append(code).append(" ");
                } else {
                    morse.append("? "); // Caractere não suportado
                }
            }
        }
        return morse.toString().trim();
    }

    public String decode(String morseCode) {
        return morseTree.decodeWord(morseCode);
    }

    public MorseBST getMorseTree() {
        return morseTree;
    }
}