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
        Tile[][] grid = getModel().getGrid();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                Tile tile = grid[row][col];
                TileViewer tileViewer = new TileViewer(tile);  // Instantiate TileViewer for each tile
                tileViewer.drawElements(gui);  // Call drawElements on the TileViewer
            }
        }
    }

}
