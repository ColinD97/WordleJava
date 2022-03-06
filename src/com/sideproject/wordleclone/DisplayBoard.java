package com.sideproject.wordleclone;

import java.util.HashMap;
import java.util.Map;

public class DisplayBoard {
    public static final String GREEN = "\033[0;102m";  // GREEN
    public static final String YELLOW = "\033[0;103m"; // YELLOW
    public static final String GREY = "\033[0;105m";  // PURPLE BACKGROUND \033[0;35m    [45m
    public static final String ANSI_RESET = "\u001B[0m";
    private Map<Character, Letter> alphabet = new HashMap<>();



    public DisplayBoard(Map<Character, Letter> alphabet) {
        this.alphabet = alphabet;
    }

    public void printBoard(String[] words){
        for (int i = 0; i < words.length; i++) {
            printBoardRow(words[i]);
        }
    }

    private void printBoardRow(String word){
        word = word.toUpperCase();
        char[] letter = word.toCharArray();
        System.out.println("       +-+-+-+-+-+");
        //System.out.printf("       |%c|%c|%c|%c|%c|\n", printColoredLetter(letter[0]), letter[1], letter[2], letter[3], letter[4]);
        System.out.print("       |");
        printColoredLetter(letter[0]);
        System.out.print("|");
        printColoredLetter(letter[1]);
        System.out.print("|");
        printColoredLetter(letter[2]);
        System.out.print("|");
        printColoredLetter(letter[3]);
        System.out.print("|");
        printColoredLetter(letter[4]);
        System.out.println("|");

        System.out.println("       +-+-+-+-+-+");
    }


    private void printColoredLetter(char letter){
        //Map<Character, Letter> alphabet = gameLogic.getAlphabet();
        switch (alphabet.get(letter).getColorCode() ){
            case DEFAULT:
                System.out.print(ANSI_RESET + alphabet.get(letter));
                break;
            case GREY:
                System.out.print(GREY + alphabet.get(letter) + ANSI_RESET);
                break;
            case YELLOW:
                System.out.print(YELLOW + alphabet.get(letter) + ANSI_RESET);
                break;
            case GREEN:
                System.out.print(GREEN + alphabet.get(letter) + ANSI_RESET);
        }
    }

}

