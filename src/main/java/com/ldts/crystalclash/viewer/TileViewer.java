package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Tile;

public class TileViewer extends Viewer<Tile> {
    public TileViewer(Tile model) {
        super(model);
    }

    @Override
    public void draw(LanternaGUI gui) {
        Tile model = getModel();
        gui.drawTile(model);
    }
}
