package main.java.controller;

import main.java.model.MorseBST;

public class MorseController {
    private MorseBST morseTree;

    public MorseController() {
        morseTree = new MorseBST();
    }

    public String encode(String text) {
        return morseTree.encode(text);
    }

    public String decode(String morseCode) {
        return morseTree.decodeWord(morseCode);
    }

    public MorseBST getMorseTree() {
        return morseTree;
    }
}