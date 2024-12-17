package com.ldts.crystalclash.model;

import java.util.Arrays;
import java.util.List;

public class GameOver {
    final int score;
    private final List<String> options;
    private int selectedOption = 0;

    public GameOver(int score) {
        this.options = Arrays.asList("PLAY AGAIN", "SCORES", "EXIT");
        this.score = score;
    }

    public void selectNext() {
        selectedOption++;
        if (selectedOption > this.options.size() - 1)
            selectedOption = 0;
    }
    public void selectPrevious() {
        selectedOption--;
        if (selectedOption < 0)
            selectedOption = this.options.size() - 1;
    }
    public String getSelectedOption(int i) {
        return options.get(i);
    }

    public boolean isSelected(int i) {
        return selectedOption == i;
    }

    public boolean isSelectedExit() {
        return isSelected(2);
    }

    public boolean isSelectedScores() {
        return isSelected(1);
    }

    public boolean isSelectedPlayAgain() {
        return isSelected(0);
    }

    public int getNumberOptions() {
        return this.options.size();
    }

    public int getScore() {
        return score;
    }
}
