package com.sideproject.wordleclone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GameLogic {

    private List<String> wordList;
    private String answerWord;
    private Map<Character, Letter> alphabet = new HashMap<>();
    private Map<Integer, Character> numberToAlphaMap = new HashMap<>();
    private String[] pastGuesses = {"     ", "     ", "     ", "     ", "     ", "     "};
//    private Letter[][] pastWordsArray = new Letter[][]{
//            new Letter[] {new Letter(' '), new Letter(' '), new Letter(' '), new Letter(' '), new Letter(' ')},
//            new Letter[] {new Letter(' '), new Letter(' '), new Letter(' '), new Letter(' '), new Letter(' ')},
//            new Letter[] {new Letter(' '), new Letter(' '), new Letter(' '), new Letter(' '), new Letter(' ')},
//            new Letter[] {new Letter(' '), new Letter(' '), new Letter(' '), new Letter(' '), new Letter(' ')},
//            new Letter[] {new Letter(' '), new Letter(' '), new Letter(' '), new Letter(' '), new Letter(' ')},
//            new Letter[] {new Letter(' '), new Letter(' '), new Letter(' '), new Letter(' '), new Letter(' ')},
//    };
    private List<List<Letter>> pastWords;
    String inputFileLocation = "database\\word-list.txt";




    public GameLogic() {
        wordList = new ArrayList<>();
        File inputFile = new File(inputFileLocation);
        setWordList(inputFile);
        this.answerWord = answerWord;
        setAlphabet();
        this.numberToAlphaMap = numberToAlphaMap;
        this.pastGuesses = pastGuesses;
        answerWord = randomWord();
        setPastWords();

        }

    





    public List<String> getWordList() {
        return wordList;
    }

    private void setWordList(File input) {
        try (Scanner textFile = new Scanner(input)){
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


    public String getAnswerWord() {
        return answerWord;
    }

    public void setAnswerWord(String answerWord) {
        this.answerWord = answerWord;
    }

    public Map<Character, Letter> getAlphabet() {
        return alphabet;
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

    public Map<Integer, Character> getNumberToAlphaMap() {
        return numberToAlphaMap;
    }

    private void setNumberToAlphaMap(Map<Integer, Character> numberToAlphaMap) {
        this.numberToAlphaMap = numberToAlphaMap;
    }

    public String[] getPastGuesses() {
        return pastGuesses;
    }

    public void setPastGuesses(String[] pastGuesses) {
        this.pastGuesses = pastGuesses;
    }

    public void setPastWords() {
        //this.pastWords = pastWords;
        pastWords.add(new ArrayList<>(6));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                pastWords.get(i).add(j, new Letter(' '));
            }
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


    // takes in a string and checks each letter to update their fields
    public boolean processWord(String word){
        word = word.toUpperCase();
        char[] letters = word.toCharArray();
        int position = 0;
        int greenCount = 0;
        for (char value: letters) {
            Letter currentLetter = alphabet.get(value);
            if (currentLetter.isInAnswer()) {
                if (currentLetter.getSingleLocation(position) == 1) {
                    currentLetter.setColorCode(Letter.ColorCode.GREEN);
                    greenCount++;
                } else {
                    currentLetter.setColorCode(Letter.ColorCode.YELLOW);
                }
            } else {
                if (!currentLetter.isHasBeenGuessed()) {
                    currentLetter.setHasBeenGuessed(true);
                    currentLetter.setColorCode(Letter.ColorCode.GREY);
                }
                position++;
            }
        }
        return greenCount == 5;
    }

}
