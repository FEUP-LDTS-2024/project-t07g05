package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Tile;

public class TileViewer extends Viewer<Tile> {
    public TileViewer(Tile model) {
        super(model);
    }

    @Override
    public void draw(LanternaGUI gui) {
        try {
            gui.clear();
            Tile model = getModel();
            gui.drawTile(model);
            gui.refresh();

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
