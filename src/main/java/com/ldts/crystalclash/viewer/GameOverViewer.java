package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.GameOver;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Score;

public class GameOverViewer extends Viewer<GameOver> {
    private String nickNameSpaces = "";

    public GameOverViewer(GameOver model) {
        super(model);
    }

    public void drawElements(GUI gui) {
        try {
            gui.clear();
            gui.drawGameBackground(120, 40);
            for (int i = 0; i < getModel().getNumberOptions(); i++)
                gui.drawText(
                        new Position(52, 20 + i),
                        getModel().getSelectedOption(i),
                        getModel().isSelected(i) ? "#00FFFF" : "#FFFFFF");


            gui.drawText(new Position(37, 5), "G A M E  O V E R:" , "#eaff00");
            gui.drawText(new Position(40, 10), "LAST SCORE:" + getModel().getScore(), "#FFFFFF");
            gui.drawText(new Position(45, 17), "Enter your name", "#FFFFFF");
            gui.drawText(new Position(45, 20), nickNameSpaces, "#FFFFFF");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}