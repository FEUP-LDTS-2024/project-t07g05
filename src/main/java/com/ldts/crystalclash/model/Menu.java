package com.ldts.crystalclash.model;

import java.util.Arrays;
import java.util.List;

public class Menu {
    private final List<String> options;
    private int selectedOption = 0;

    public Menu() {this.options = Arrays.asList("PLAY", "SCORES", "EXIT");}


    public void selectNext() {
        selectedOption = (selectedOption == options.size() - 1) ? 0 : selectedOption + 1;
    }

    public void selectPrevious() {
        selectedOption = (selectedOption == 0) ? options.size() - 1 : selectedOption - 1;
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

    public boolean isSelectedStart() {
        return isSelected(0);
    }


}
