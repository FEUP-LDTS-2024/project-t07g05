package viewer;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.Board;

public class GameViewer extends Viewer<Board> {
    Screen screen;
    private final int width = 120;
    private final int height = 40;
    BoardViewer boardViewer;

    public GameViewer(Board model) {
        super(model);
        this.boardViewer = new BoardViewer(model);
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    private void drawGameBackground() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2e4045"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');
    }

    @Override
    public void draw(TextGraphics graphics) {
        try {
            screen.clear();
            drawGameBackground();
            boardViewer.draw(graphics);
            screen.refresh();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
