package com.sideproject.wordleclone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GameLogicAlternate {

    private String answerWord;
    private Map<Character, Letter> alphabet = new HashMap<>();
    private Map<Integer, Character> numberToAlphaMap = new HashMap<>();
    private final List<String> wordList;
    String inputFileLocation = "database\\word-list.txt";
    private List<Letter> wordSlot0 = new ArrayList<>();
    private List<Letter> wordSlot1 = new ArrayList<>();
    private List<Letter> wordSlot2 = new ArrayList<>();
    private List<Letter> wordSlot3 = new ArrayList<>();
    private List<Letter> wordSlot4 = new ArrayList<>();
    private List<Letter> wordSlot5 = new ArrayList<>();
    private List<List<Letter>> listOfPastGuesses = new ArrayList<>();


    public GameLogicAlternate() {
        wordList = new ArrayList<>();
        File inputFile = new File(inputFileLocation);
        setWordList(inputFile);
        setAlphabet();
        answerWord = randomWord();
        wordSlot0 = setBlanks(wordSlot0);
        wordSlot1 = setBlanks(wordSlot1);
        wordSlot2 = setBlanks(wordSlot2);
        wordSlot3 = setBlanks(wordSlot3);
        wordSlot4 = setBlanks(wordSlot4);
        wordSlot5 = setBlanks(wordSlot5);
        listOfPastGuesses.addAll((Arrays.asList(wordSlot0, wordSlot1, wordSlot2, wordSlot3, wordSlot4, wordSlot5)));

    }

    public String getAnswerWord() {
        return answerWord;
    }

    // reads words from txt file to List<String>
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
            position++;
        }
        return answerWord;
    }

    // currently sets order of alphabet to QWERTY to aid display
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

    private List<Letter> setBlanks(List<Letter> word){
        for (int i = 0; i < 5; i++) {
            word.add(new Letter(' '));
        }
        return word;
    }

    public List<List<Letter>> getListOfPastGuesses() {
        return listOfPastGuesses;
    }

    public void setGuessOnList(int i, List<Letter> word) {
        processWordLetters(word);
        listOfPastGuesses.set(i, word);
    }

    // takes in a list of Letters, compares each letter to correct answer,
    // and sets the color code accordingly
    public void processWordLetters(List<Letter> rowSlotWord) {
        for (int i = 0; i < 5; i++) {
            Letter currentLetter = rowSlotWord.get(i);
            char currentChar = currentLetter.getLetterChar();
            Letter alphabetLetter = alphabet.get(currentChar);
            if (alphabetLetter.isInAnswer()){
                if(alphabetLetter.getSingleLocation(i) == 1) {
                    currentLetter.setColorCode(Letter.ColorCode.GREEN);
                    alphabetLetter.setColorCode(Letter.ColorCode.GREEN);
                } else {
                    currentLetter.setColorCode(Letter.ColorCode.YELLOW);
                    if (!alphabetLetter.getColorCode().equals(Letter.ColorCode.GREEN)){
                        alphabetLetter.setColorCode(Letter.ColorCode.YELLOW);
                    }
                }
            } else {
                currentLetter.setColorCode(Letter.ColorCode.GREY);
                alphabetLetter.setColorCode(Letter.ColorCode.GREY);
            }
        }
    }

    public Map<Character, Letter> getAlphabet() {
        return alphabet;
    }

    public Map<Integer, Character> getNumberToAlphaMap() {
        return numberToAlphaMap;
    }

    public List<String> getWordList() {
        return wordList;
    }

    public String convertListWordToString(List<Letter> listWord){
        String stringWord = "";
        for (Letter letter: listWord){
            stringWord += letter.getLetterChar();
        }
        return stringWord;
    }


}
