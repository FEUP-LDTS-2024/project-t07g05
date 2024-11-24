package viewer;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import gui.LanternaGUI;
import model.Board;
import model.Tile;

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
                gui.drawTile(cell);
            }
        }
    }
}
