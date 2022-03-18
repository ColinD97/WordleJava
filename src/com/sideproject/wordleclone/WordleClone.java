package com.sideproject.wordleclone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordleClone {
    private Scanner userInput = new Scanner(System.in);
    private GameLogicAlternate gameLogicAlternate;
    private DisplayBoard displayBoard;
    public static final String GREEN = "\033[0;102m";  // GREEN
    public static final String YELLOW = "\033[0;103m"; // YELLOW
    public static final String GREY = "\033[0;105m";  // PURPLE BACKGROUND \033[0;35m
    public static final String ANSI_RESET = "\u001B[0m";
    public static int gamesPlayed = 0;
    public static int gamesWon = 0;
    public static double avgNumGuesses = 0;


    public static void main(String[] args) {
        WordleClone application = new WordleClone();
        application.run();
    }

    private void run() {
        displayApplicationBanner();
        boolean keepPlaying = true;
        while(keepPlaying){
            keepPlaying = displayMainMenu();
        }
        userInput.close();
    }


    private void playGame(){
        gameLogicAlternate = new GameLogicAlternate();
        displayBoard = new DisplayBoard(gameLogicAlternate.getAlphabet());
        System.out.println("*** "+gameLogicAlternate.getAnswerWord()+" ***");
        System.out.println();
        System.out.println();
        List<List<Letter>> listOfGuesses = gameLogicAlternate.getListOfPastGuesses();
        displayBoard.sendListOfWordsToPrint(listOfGuesses);
        printAlphabet();
        System.out.println();
        boolean win = false;
        int guessCount = 0;
        for (int i = 0; i < 6; i++) {
            guessCount++;
            System.out.println();
            List<Letter> userWord = userGuess();
            gameLogicAlternate.setGuessOnList(i, userWord);
            displayBoard.sendListOfWordsToPrint(listOfGuesses);
            printAlphabet();
            String stringUserWord = gameLogicAlternate.convertListWordToString(userWord);
            if (stringUserWord.equalsIgnoreCase(gameLogicAlternate.getAnswerWord())){
                System.out.println();
                win = true;
                break;
            }
        }
        if (win){
            System.out.println("\n\n   *** YOU WIN!!! ***\n\n");
            gamesWon++;
            gamesPlayed++;
            avgNumGuesses += guessCount;
        } else {
            System.out.println("\n\n   *** YOU LOSE! ***");
            System.out.println("The correct word was: "+ gameLogicAlternate.getAnswerWord());
            System.out.println();
            gamesPlayed++;
            avgNumGuesses += guessCount;
        }

    }

    // takes user input and check for valid word that exists in loaded dictionary
    private List<Letter> userGuess(){
        List<Letter> userWord = new ArrayList<>();
        String guess = "";
        boolean isValid = false;
        while (!isValid) {
            System.out.print("Enter 5 letter word to guess: ");
            guess = userInput.nextLine();
            {
                for (String word : gameLogicAlternate.getWordList()) {
                    if (word.equalsIgnoreCase(guess)) {
                        isValid = true;
                        break;
                    }
                }
            }
        }
        guess = guess.toUpperCase();
        for (int i = 0; i < guess.length(); i++) {
            Letter letter = new Letter(guess.charAt(i));
            userWord.add(letter);
        }
        return userWord;
    }


    //print available letters color coded to not used/ in word / in position
    //print before every guess
    private void printAlphabet() {
        System.out.println("Available letters: ");
        Map<Character, Letter> alphabet = gameLogicAlternate.getAlphabet();
        Map<Integer, Character> numberToAlphaMap = gameLogicAlternate.getNumberToAlphaMap();
        for (int i = 0; i < 10; i++) {
            switch (alphabet.get(numberToAlphaMap.get(i)).getColorCode()) {
                case DEFAULT:
                    System.out.print(ANSI_RESET + numberToAlphaMap.get(i) + "  ");
                    break;
                case GREY:
                    System.out.print(GREY + numberToAlphaMap.get(i) + ANSI_RESET + "  ");
                    break;
                case YELLOW:
                    System.out.print(YELLOW + numberToAlphaMap.get(i) + ANSI_RESET + "  ");
                    break;
                case GREEN:
                    System.out.print(GREEN + numberToAlphaMap.get(i) + ANSI_RESET + "  ");
            }
        }
        System.out.println();
        for (int i = 10; i < 19; i++) {
            switch (alphabet.get(numberToAlphaMap.get(i)).getColorCode()) {
                case DEFAULT:
                    System.out.print(" " + ANSI_RESET + numberToAlphaMap.get(i) + " ");
                    break;
                case GREY:
                    System.out.print(" " + GREY + numberToAlphaMap.get(i) + ANSI_RESET + " ");
                    break;
                case YELLOW:
                    System.out.print(" " + YELLOW + numberToAlphaMap.get(i) + ANSI_RESET + " ");
                    break;
                case GREEN:
                    System.out.print(" " + GREEN + numberToAlphaMap.get(i) + ANSI_RESET + " ");
            }
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 19; i < 26; i++) {
            switch (alphabet.get(numberToAlphaMap.get(i)).getColorCode()) {
                case DEFAULT:
                    System.out.print("  " + ANSI_RESET + numberToAlphaMap.get(i));
                    break;
                case GREY:
                    System.out.print("  " + GREY + numberToAlphaMap.get(i) + ANSI_RESET);
                    break;
                case YELLOW:
                    System.out.print("  " + YELLOW + numberToAlphaMap.get(i) + ANSI_RESET);
                    break;
                case GREEN:
                    System.out.print("  " + GREEN + numberToAlphaMap.get(i) + ANSI_RESET);
            }
        }
        System.out.println(ANSI_RESET);
    }


    private boolean displayMainMenu() {
        System.out.println("1. Play Game");
        System.out.println("2. Display Statistics");
        System.out.println("3. Exit");
        boolean isInputCorrect = false;
        while (!isInputCorrect) {
            String userChoice = userInput.nextLine();
            switch (userChoice) {
                case "1":
                    isInputCorrect = true;
                    playGame();
                    return true;
                case "2":
                    isInputCorrect = true;
                    displayStatistics();
                    break;
                case "3":
                    System.out.println();
                    System.out.println("*** Quit ***");
                    System.out.println();
                    isInputCorrect = true;
                    return false;
                default:
                    System.out.println("Input not valid");
            }

        }
        return true;
    }

    private void displayStatistics(){
        System.out.println("\nNumber of games: "+ gamesPlayed);
        System.out.println("Number of games won: "+ gamesWon);
        System.out.println("Total number of guesses: "+avgNumGuesses);
        System.out.println("Average number of guesses: "+ String.format("%.2f",(avgNumGuesses/gamesPlayed)));
        System.out.println();
    }


    private void displayApplicationBanner() {
        System.out.println();
        System.out.println(" __      __                .___.__           _________ .__");
        System.out.println("/  \\    /  \\___________  __| _/|  |   ____   \\_   ___ \\|  |   ____   ____   ____");
        System.out.println("\\   \\/\\/   /  _ \\_  __ \\/ __ | |  | _/ __ \\  /    \\  \\/|  |  /  _ \\ /    \\_/ __ \\");
        System.out.println(" \\        (  <_> )  | \\/ /_/ | |  |_\\  ___/  \\     \\___|  |_(  <_> )   |  \\  ___/");
        System.out.println("  \\__/\\  / \\____/|__|  \\____ | |____/\\___  >  \\______  /____/\\____/|___|  /\\___  >");
        System.out.println("       \\/                   \\/           \\/          \\/                 \\/     \\/");
        System.out.println();
    }

}


