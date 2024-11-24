package viewer;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Tile;

public class TileViewer extends Viewer<Tile> {
    public TileViewer(Tile model) {
        super(model);
    }

    @Override
    public void draw(TextGraphics graphics) {
        Tile model = getModel();

        graphics.setForegroundColor(TextColor.Factory.fromString(model.getColor()));
        if (model.isCursorOn()) {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        } else {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#2e4045"));
        }
        graphics.putString(new TerminalPosition(model.getScreenPosition().getX(), model.getScreenPosition().getY()), model.getSymbol());
    }
}
