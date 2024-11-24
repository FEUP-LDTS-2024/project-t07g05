package viewer;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Board;
import model.Tile;

public class BoardViewer extends Viewer<Board> {

    public BoardViewer(Board model) {
        super(model);
    }

    @Override
    public void draw(TextGraphics graphics) {
        Board model = getModel();

        graphics.setBackgroundColor(TextColor.Factory.fromString("#2e3440"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(model.getWidth(), model.getHeight()), ' ');

        // Fill the board with tiles
        for (Tile[] row : model.getGrid()) {
            for (Tile cell : row) {
                TileViewer tileViewer = new TileViewer(cell);
                tileViewer.draw(graphics);
            }
        }
    }
}
