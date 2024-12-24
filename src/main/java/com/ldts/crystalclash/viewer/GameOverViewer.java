package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.GameOver;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Score;

public class GameOverViewer extends Viewer<GameOver> {

    public GameOverViewer(GameOver model) {
        super(model);
    }

    public void drawElements(GUI gui) {
        try {
            gui.clear();


            gui.drawGameBackground(120, 40);
            for (int i = 0; i < getModel().getNumberOptions(); i++)
                gui.drawText(
                        new Position(52, 20 + (i+1)),
                        getModel().getSelectedOption(i),
                        getModel().isSelected(i) ? "#00FFFF" : "#FFFFFF");


            gui.drawText(new Position(49, 5), "G A M E  O V E R:" , "#eaff00");
            gui.drawText(new Position(51, 10), "YOUR SCORE:" + getModel().getScore(), "#00e348");




        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}