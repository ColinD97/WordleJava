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

    public WordleClone(){
        this.wordList = null;
    }

    public static void main(String[] args) {
        WordleClone application = new WordleClone();
        application.run();

    }

    private void run(){
        //String inputFileLocation = "\\database\\word-list.txt";
        String inputFileLocation = "word-list.txt";

        File inputFile = new File(inputFileLocation);
        //loadWords(inputFile);
        displayApplicationBanner();
        displayMainMenu();
        String guess = "wordle";
        printBoard(guess);
        getFileInfo();



        userInput.close();
    }

    private void loadWords(File inputFile){
        //List<String> wordList = new ArrayList<>();
        //wordList
        try (Scanner textFile = new Scanner(inputFile)){
            int i = 0;
            while (textFile.hasNextLine()){
                wordList.add(textFile.nextLine());
                System.out.println(wordList.get(i));
                System.out.println(i);
                i++;
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        //return wordList;
    }

    private String randomWord(){
        Random random = new Random();
        //String solutionWord = wordList[random.nextInt(wordList.size())];
        String solutionWord = wordList.get(random.nextInt(wordList.size()));

        return solutionWord;
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
        }

    private void printBoard(String word){
        word = word.toUpperCase();
        char[] letter = word.toCharArray();
        //char letter = ' ';
        System.out.println(" +-+-+-+-+-+-+");
        System.out.printf(" |%c|%c|%c|%c|%c|%c|\n", letter[0], letter[1], letter[2], letter[3], letter[4], letter[5]);
        System.out.println(" +-+-+-+-+-+-+");

        //System.out.println("|*| ");
        //System.out.println("__");

    }

    public void getFileInfo() {
        File myObj = new File("word-list.txt");
        if (myObj.exists()) {
            System.out.println("File name: " + myObj.getName());
            System.out.println("Absolute path: " + myObj.getAbsolutePath());
            System.out.println("Writeable: " + myObj.canWrite());
            System.out.println("Readable " + myObj.canRead());
            System.out.println("File size in bytes " + myObj.length());
        } else {
            System.out.println("The file does not exist.");
        }
    }

}
