package com.sideproject.wordleclone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordleClone {
    private Scanner userInput = new Scanner(System.in);
    private List<String> wordList;
    private String answerWord;

    public WordleClone(){
        this.wordList = new ArrayList<>();
        String inputFileLocation = "database\\word-list.txt";
        File inputFile = new File(inputFileLocation);
        loadWords(inputFile);
        this.answerWord = randomWord();
    }

    public static void main(String[] args) {
        WordleClone application = new WordleClone();
        application.run();

    }

    private void run(){

        displayApplicationBanner();
        displayMainMenu();
        //getFileInfo(inputFile);

        userInput.close();
    }

    //game logic loop
    private void playGame(){
        System.out.println();
        System.out.println();
        printBoard("*****");
        System.out.println();
        for (int i = 0; i < 7; i++) {
            String validGuess = userGuess();
            printBoard(validGuess);
        }
    }

    private String userGuess(){
        String guess = "";
        boolean isValid = false;
        while (!isValid){
            System.out.print("Enter 5 letter word to guess: ");
            guess = userInput.nextLine();
            if (guess.equalsIgnoreCase(answerWord)){
                System.out.println("YOU WIN!");
                isValid = true;
            } else {
                for (String word : wordList) {
                    if (word.equalsIgnoreCase(guess)) {
                        isValid = true;
                        break;
                    }
                }
            }
        }
        return guess;
    }

    // takes inputFile and loads words into wordList List
    // wordList is used in game as library of possible words to use and guess
    private void loadWords(File inputFile){
        try (Scanner textFile = new Scanner(inputFile)){
            int i = 0;
            while (textFile.hasNextLine()){
                wordList.add(textFile.nextLine());
                //System.out.println(wordList.get(i));
                //System.out.println(i);
                i++;
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    // picks random location in string list to be the word to guess
    private String randomWord(){
        Random random = new Random();
        return wordList.get(random.nextInt(wordList.size()));
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

    private void printBoard(String word){
        word = word.toUpperCase();
        char[] letter = word.toCharArray();
        System.out.println(" +-+-+-+-+-+");
        System.out.printf(" |%c|%c|%c|%c|%c|\n", letter[0], letter[1], letter[2], letter[3], letter[4]);
        System.out.println(" +-+-+-+-+-+");
    }

    // troubleshooting method to check input file information and validity
    public void getFileInfo(File inputFile) {
        if (inputFile.exists()) {
            System.out.println("File name: " + inputFile.getName());
            System.out.println("Absolute path: " + inputFile.getAbsolutePath());
            System.out.println("Writeable: " + inputFile.canWrite());
            System.out.println("Readable " + inputFile.canRead());
            System.out.println("File size in bytes " + inputFile.length());
        } else {
            System.out.println("The file does not exist.");
        }
    }

}
