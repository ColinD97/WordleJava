package com.sideproject.wordleclone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordleClone {
    private Scanner userInput = new Scanner(System.in);
    private GameLogic gameLogic;
    public static final String GREEN = "\033[0;102m";  // GREEN
    public static final String YELLOW = "\033[0;103m"; // YELLOW
    public static final String GREY = "\033[0;105m";  // PURPLE BACKGROUND \033[0;35m    [45m
    public static final String ANSI_RESET = "\u001B[0m";


    public WordleClone(){
       gameLogic = new GameLogic();
    }

    public static void main(String[] args) {
        WordleClone application = new WordleClone();
        application.run();
    }

    private void run(){
        displayApplicationBanner();
        displayMainMenu();
        userInput.close();
    }

    //game logic loop
    private void playGame(){
        System.out.println();
        System.out.println();
        String[] pastGuesses = gameLogic.getPastGuesses();
        printBoard(pastGuesses);
        printAlphabet();
        System.out.println();
        for (int i = 0; i < 6; i++) {
            System.out.println();
            //String validGuess = userGuess();
            pastGuesses[i] = userGuess();
            if (gameLogic.processWord(pastGuesses[i])){
                System.out.println("YOU WIN!");
                break;
            }
            printBoard(pastGuesses);
            printAlphabet();
        }
    }

    private String userGuess(){
        String guess = "";
        boolean isValid = false;
        while (!isValid){
            System.out.print("Enter 5 letter word to guess: ");
            guess = userInput.nextLine();{
                for (String word : gameLogic.getWordList()) {
                    if (word.equalsIgnoreCase(guess)) {
                        isValid = true;
                        break;
                    }
                }
            }
        }
        return guess;
    }





    //print available letters color coded to not used/ in word / in position
    //print before every guess
    private void printAlphabet(){
        System.out.println("Available letters: ");
        Map<Character, Letter> alphabet = gameLogic.getAlphabet();
        Map<Integer, Character> numberToAlphaMap = gameLogic.getNumberToAlphaMap();
        for (int i = 0; i < 10; i++) {
            switch (alphabet.get(numberToAlphaMap.get(i)).getColorCode() ){
                case DEFAULT:
                    System.out.print(ANSI_RESET + numberToAlphaMap.get(i)+"  ");
                    break;
                case GREY:
                    System.out.print(GREY + numberToAlphaMap.get(i)+ANSI_RESET+"  " );
                    break;
                case YELLOW:
                    System.out.print(YELLOW + numberToAlphaMap.get(i)+ANSI_RESET+"  " );
                    break;
                case GREEN:
                    System.out.print(GREEN +numberToAlphaMap.get(i)+ANSI_RESET+"  " );
            }
            //System.out.print(numberToAlphaMap.get(i)+"  ");
            //System.out.print(ANSI_RESET);
        }
        System.out.println();
        for (int i = 10; i < 19; i++) {
            switch (alphabet.get(numberToAlphaMap.get(i)).getColorCode() ){
                case DEFAULT:
                    System.out.print(" "+ANSI_RESET + numberToAlphaMap.get(i)+" ");
                    break;
                case GREY:
                    System.out.print(" "+GREY + numberToAlphaMap.get(i)+ANSI_RESET+" " );
                    break;
                case YELLOW:
                    System.out.print(" "+YELLOW + numberToAlphaMap.get(i)+ANSI_RESET+" " );
                    break;
                case GREEN:
                    System.out.print(" "+GREEN +numberToAlphaMap.get(i)+ANSI_RESET+" " );
            }
            //System.out.print(" "+numberToAlphaMap.get(i)+" ");
            //System.out.print(ANSI_RESET);
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 19; i < 26; i++) {
            switch (alphabet.get(numberToAlphaMap.get(i)).getColorCode() ){
                case DEFAULT:
                    System.out.print("  "+ANSI_RESET + numberToAlphaMap.get(i));
                    break;
                case GREY:
                    System.out.print("  "+GREY + numberToAlphaMap.get(i)+ANSI_RESET);
                    break;
                case YELLOW:
                    System.out.print("  "+YELLOW + numberToAlphaMap.get(i)+ANSI_RESET);
                    break;
                case GREEN:
                    System.out.print("  "+GREEN +numberToAlphaMap.get(i)+ANSI_RESET);
            }
            //System.out.print("  "+numberToAlphaMap.get(i));
            //System.out.print(ANSI_RESET);
        }
        System.out.println(ANSI_RESET);
    }




    private void displayApplicationBanner(){
        System.out.println();
        System.out.println(" __      __                .___.__           _________ .__");
        System.out.println("/  \\    /  \\___________  __| _/|  |   ____   \\_   ___ \\|  |   ____   ____   ____");
        System.out.println("\\   \\/\\/   /  _ \\_  __ \\/ __ | |  | _/ __ \\  /    \\  \\/|  |  /  _ \\ /    \\_/ __ \\");
        System.out.println(" \\        (  <_> )  | \\/ /_/ | |  |_\\  ___/  \\     \\___|  |_(  <_> )   |  \\  ___/");
        System.out.println("  \\__/\\  / \\____/|__|  \\____ | |____/\\___  >  \\______  /____/\\____/|___|  /\\___  >");
        System.out.println("       \\/                   \\/           \\/          \\/                 \\/     \\/");
        System.out.println();
    }

    private void displayMainMenu(){
        System.out.println("1. Play Game");
        System.out.println("2. Check stats");
        System.out.println("3. Exit");
        boolean isInputCorrect = false;
        while (!isInputCorrect){
            String userChoice = userInput.nextLine();
            switch (userChoice){
                case "1":
                    isInputCorrect = true;
                    playGame();
                    break;
                case "2":
                    isInputCorrect = true;
                    System.out.println("*** Numbers ***");
                    break;
                case "3":
                    System.out.println("*** Quit ***");
                    isInputCorrect = true;
                    break;
                default:
                    System.out.println("Input not valid");
            }

        }
        }

        private void printBoard(String[] words){
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
        Map<Character, Letter> alphabet = gameLogic.getAlphabet();
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
