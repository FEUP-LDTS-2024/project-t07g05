package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.ScoreEntry;
import com.ldts.crystalclash.model.ScoresMenu;

public class ScoresMenuViewer extends Viewer<ScoresMenu> {
    public ScoresMenuViewer(ScoresMenu model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) {
        gui.drawGameBackground(120, 40);
        if (getModel().getEntries().size() > 0) {
            int i = 0;
            for (ScoreEntry entry : getModel().getEntries()) {
                gui.drawText(new Position(10, 4 + i),
                        entry.getPoints(),
                        "#FFFFFF");
                gui.drawText(new Position(20, 4 + 1),
                        entry.getDate(),
                        "#FFFFFF");
                i += 2;
            }
        } else {
            gui.drawText(new Position(40, 20),
                    "NO SCORES AVAILABLE",
                    "#FFFFFF");
        }
    }
}
