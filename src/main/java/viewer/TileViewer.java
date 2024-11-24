package viewer;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import gui.LanternaGUI;
import model.Tile;

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
