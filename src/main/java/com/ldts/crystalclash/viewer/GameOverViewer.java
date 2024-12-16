package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.model.Position;

public class GameOverViewer extends Viewer<Menu> {

    public GameOverViewer(Menu model) {
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
                                                    //adicionar funcao do ultimo score
            gui.drawText(new Position(45, 5), "LAST SCORE:" , "#eaff00");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}