package com.sideproject.wordleclone;

public class Letter {

    private char letterChar;
    private boolean hasBeenGuessed;
    private boolean inAnswer;
    private int[] letterLocations = {0,0,0,0,0};
    enum ColorCode { DEFAULT, GREY, YELLOW, GREEN}
    private ColorCode colorCode;

    public Letter(char letter){
        this.letterChar = letter;
        this.hasBeenGuessed = false;
        this.inAnswer = false;
        colorCode = ColorCode.DEFAULT;
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

    public int[] getLetterLocations() {
        return letterLocations;
    }

    public int getSingleLocation(int postion){
        return letterLocations[postion];
    }

    public void setLetterLocations(int position) {
        letterLocations[position] = 1;
    }

    public ColorCode getColorCode() {
        return colorCode;
    }

    public void setColorCode(ColorCode colorCode) {
        this.colorCode = colorCode;
    }

    @Override
    public String toString() {
        return Character.toString(getLetterChar());
    }
}
