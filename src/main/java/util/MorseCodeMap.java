package main.java.util;

import java.util.HashMap;
import java.util.Map;

public class MorseCodeMap {
    private static final Map<Character, String> charToMorse = new HashMap<>();
    private static final Map<String, Character> morseToChar = new HashMap<>();

    static {
        // Apenas alfabeto de A-Z
        charToMorse.put('A', ".-");
        charToMorse.put('B', "-...");
        charToMorse.put('C', "-.-.");
        charToMorse.put('D', "-..");
        charToMorse.put('E', ".");
        charToMorse.put('F', "..-.");
        charToMorse.put('G', "--.");
        charToMorse.put('H', "....");
        charToMorse.put('I', "..");
        charToMorse.put('J', ".---");
        charToMorse.put('K', "-.-");
        charToMorse.put('L', ".-..");
        charToMorse.put('M', "--");
        charToMorse.put('N', "-.");
        charToMorse.put('O', "---");
        charToMorse.put('P', ".--.");
        charToMorse.put('Q', "--.-");
        charToMorse.put('R', ".-.");
        charToMorse.put('S', "...");
        charToMorse.put('T', "-");
        charToMorse.put('U', "..-");
        charToMorse.put('V', "...-");
        charToMorse.put('W', ".--");
        charToMorse.put('X', "-..-");
        charToMorse.put('Y', "-.--");
        charToMorse.put('Z', "--..");

        // Inverte o mapa para criar morseToChar
        for (Map.Entry<Character, String> entry : charToMorse.entrySet()) {
            morseToChar.put(entry.getValue(), entry.getKey());
        }
    }

    public static String getMorseCode(char character) {
        return charToMorse.get(Character.toUpperCase(character));
    }

    public static Character getCharacter(String morseCode) {
        return morseToChar.get(morseCode);
    }
}