package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;

public class BoardViewer extends Viewer<Board> {

    public BoardViewer(Board model) {
        super(model);
    }

    @Override
    public void drawElements(GUI gui) {
        try {
            // Draw the board with tiles
            for (Tile[] row : getModel().getGrid()) {
                for (Tile cell : row) {
                    TileViewer tileViewer = new TileViewer(cell);
                    tileViewer.drawElements(gui);
                }
            }
        }catch (Exception e) {
                throw new RuntimeException(e);
            }

    }
}
