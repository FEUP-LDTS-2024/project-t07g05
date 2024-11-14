import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    Screen screen;
    Board board;
    private final int width;
    private final int height;


    public Game() {
        try {
            this.width = 120;
            this.height = 40;
            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            this.screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void drawGameBackground() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2e4045"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');
    }

    public void draw() {
        try {
            screen.clear();
            drawGameBackground();
            board.draw(screen.newTextGraphics());
            screen.refresh();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        // Starts a new game match
        board = new Board(8, 8, (width-30), height, 9, 4);
        draw();
    }
}
