package com.sideproject.wordleclone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordleClone {
    private Scanner userInput = new Scanner(System.in);
    private List<String> wordList;
    public String answerWord;
    private Map<Character, Letter> alphabet = new HashMap<>();
    private Map<Integer, Character> numberToAlphaMap = new HashMap<>();
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String PURPLE = "\u001B[45m";  // PURPLE BACKGROUND
    public static final String DEFAULT = "\\u001B[37m";


    public WordleClone(){
        this.wordList = new ArrayList<>();
        generateAlphabet();
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
        printAlphabet();

        System.out.println();
        System.out.println();
        printBoard("*****");
        System.out.println();
        for (int i = 0; i < 7; i++) {
            String validGuess = userGuess();
            printBoard(validGuess);
            printAlphabet();
        }
    }

    private String userGuess(){
        String guess = "";
        boolean isValid = false;
        while (!isValid){
            System.out.print("Enter 5 letter word to guess: ");
            guess = userInput.nextLine();{
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

    // takes in a string and checks each letter to update their fields
    private void processWord(String word){
        char[] letters = word.toCharArray();
        int position = 0;
        for (char value: letters){
            Letter currentLetter = alphabet.get(value);
            if (currentLetter.isInAnswer()){
                if (currentLetter.getSingleLocation(position) == 1){
                    currentLetter.setColorCode(Letter.ColorCode.GREEN);
                } else {
                    currentLetter.setColorCode(Letter.ColorCode.YELLOW);
                }
            }
            if (!currentLetter.isHasBeenGuessed()){
                currentLetter.setHasBeenGuessed(true);
                currentLetter.setColorCode(Letter.ColorCode.GREY);
            }
        }
        position++;
        //Letter letter = alphabet.get("A");
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

    //create Map of Letter class as alphabet to initialize new word
    private void generateAlphabet(){
        int count = 0;
        char[] allChars = {'Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M'};
        for(char value: allChars){
            Letter newLetter = new Letter(value);
            numberToAlphaMap.put(count, value);
            alphabet.put(value, newLetter);
            count++;
        }
    }

    //print available letters color coded to not used/ in word / in position
    //print before every guess
    private void printAlphabet(){
        System.out.println("Available letters: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(numberToAlphaMap.get(i)+"  ");
        }
        System.out.println();
        for (int i = 10; i < 19; i++) {
            System.out.print(" "+numberToAlphaMap.get(i)+" ");
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 19; i < 26; i++) {
            System.out.print("  "+numberToAlphaMap.get(i));
        }
    }


    // picks random location in string list to be the word to guess
    private String randomWord(){
        Random random = new Random();
        answerWord = wordList.get(random.nextInt(wordList.size()));
        answerWord = answerWord.toUpperCase();
        char[] letters = answerWord.toCharArray();
//        for (int i = 0; i < 6; i++) {
//            char tempChar = numberToAlphaMap.get(i);
//            Letter letter = alphabet.get(tempChar);
//            letter.setInAnswer(true);
//            System.out.println("Letter "+letter+" is in answer");
//        }
        int position = 0;
        for(char value: letters){
            Letter letter = alphabet.get(value);
            letter.setInAnswer(true);
            letter.setLetterLocations(position);
            System.out.println("Letter "+value+" is in answer");
            position++;
        }
        return answerWord;
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
        System.out.println("       +-+-+-+-+-+");
        System.out.printf("       |%c|%c|%c|%c|%c|\n", letter[0], letter[1], letter[2], letter[3], letter[4]);
        System.out.println("       +-+-+-+-+-+");
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
