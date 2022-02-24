package com.sideproject.wordleclone;

public class WordleClone {


    public static void main(String[] args) {
        WordleClone application = new WordleClone();
        application.run();
    }

    private void run(){
    displayApplicationBanner();
    displayMainMenu();
    String guess = "wordle";
    printBoard(guess);
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
}
