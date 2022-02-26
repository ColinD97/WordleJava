package com.sideproject.wordleclone;

public class Letter {

    private char letterChar;
    private boolean hasBeenGuessed;
    private boolean inAnswer;
    private char[] letterLocations = {0,0,0,0,0};

    public Letter(char letter){
        this.letterChar = letter;
        this.hasBeenGuessed = false;
        this.inAnswer = false;
    }

    public char getLetterChar() {
        return letterChar;
    }

    public void setLetterChar(char letterChar) {
        this.letterChar = letterChar;
    }

    public boolean isHasBeenGuessed() {
        return hasBeenGuessed;
    }

    public void setHasBeenGuessed(boolean hasBeenGuessed) {
        this.hasBeenGuessed = hasBeenGuessed;
    }

    public boolean isInAnswer() {
        return inAnswer;
    }

    public void setInAnswer(boolean inAnswer) {
        this.inAnswer = inAnswer;
    }

    public char[] getLetterLocations() {
        return letterLocations;
    }

    public void setLetterLocations(int position) {
        letterLocations[position] = 1;
        System.out.println("Position: " + position);
    }

    @Override
    public String toString() {
        return Character.toString(getLetterChar());
    }
}
