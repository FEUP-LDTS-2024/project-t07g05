package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Tile;

public class TileViewer extends Viewer<Tile> {
    public TileViewer(Tile model) {
        super(model);
    }

    @Override
    public void drawElements(GUI gui) {
        try {
            Tile model = getModel();
            gui.drawTile(model);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
