package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Board;

public class BoardViewer extends Viewer<Board> {

    public BoardViewer(Board model) {
        super(model);
    }

    @Override
    public void draw(LanternaGUI gui) {
        Board model = getModel();
        gui.drawBoard(model);

        // Fill the board with tiles
        for (Tile[] row : model.getGrid()) {
            for (Tile cell : row) {
                TileViewer tileViewer = new TileViewer(cell);
                tileViewer.draw(gui);
            }
        }
    }
}
