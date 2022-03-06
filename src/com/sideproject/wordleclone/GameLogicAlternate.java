package com.sideproject.wordleclone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GameLogicAlternate {

    private String answerWord;
    private List<String> pastGuesses;
    private Map<Character, Letter> alphabet = new HashMap<>();
    private Map<Integer, Character> numberToAlphaMap = new HashMap<>();
    private final List<String> wordList;
    String inputFileLocation = "database\\word-list.txt";

    public GameLogicAlternate() {
        wordList = new ArrayList<>();
        File inputFile = new File(inputFileLocation);
        setWordList(inputFile);
        answerWord = randomWord();
        setAlphabet();
    }

    private void setWordList(File input) {
        try (Scanner textFile = new Scanner(input)){
            int i = 0;
            while (textFile.hasNextLine()){
                wordList.add(textFile.nextLine());
                i++;
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    // picks random location in string list to be the word to guess
    public String randomWord(){
        Random random = new Random();
        answerWord = wordList.get(random.nextInt(wordList.size()));
        answerWord = answerWord.toUpperCase();
        char[] letters = answerWord.toCharArray();
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

    private void setAlphabet() {
        int count = 0;
        char[] allChars = {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', ' '};
        for (char value : allChars) {
            Letter newLetter = new Letter(value);
            numberToAlphaMap.put(count, value);
            alphabet.put(value, newLetter);
            count++;
        }
    }


}
