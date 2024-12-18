package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Score;

public class ScoreViewer extends Viewer<Score> {
    public ScoreViewer(Score model) {
        super(model);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawTextInGame(new Position(100, 5),
                "SCORE:",
                "#FFFFFF");
        gui.drawTextInGame(new Position(100, 7), "" + getModel().getScore(), "#FFFFFF");
    }
}
