package main.java.controller;

import main.java.util.MorseCodeMap;

public class MorseController {
    private model.MorseBST morseTree;

    public MorseController() {
        morseTree = new model.MorseBST();
        initializeTree();
    }

    private void initializeTree() {
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

    public model.MorseBST getMorseTree() {
        return morseTree;
    }
}