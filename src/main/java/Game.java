import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import controller.GameController;
import model.Board;
import model.MatchHandler;
import viewer.GameViewer;

import java.io.IOException;


public class Game {
    Screen screen;
    GameViewer gameViewer;
    GameController gameController;
    MatchHandler matchHandler;

    public Game(Screen screen, GameViewer gameViewer, GameController gameController, MatchHandler matchHandler) {
        try {
            this.screen = screen;
            this.gameViewer = gameViewer;
            this.gameController = gameController;
            this.matchHandler = matchHandler;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Screen setScreen(int width, int height) {
        try {
            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            Screen screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            return screen;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        int width = 120; int height = 40;
        Screen screen = setScreen(width, height);
        Board board = new Board(8, 8, (width-30), height, 4, 4);
        GameViewer gameViewer = new GameViewer(board);
        gameViewer.setScreen(screen);
        GameController gameController = new GameController(board);
        MatchHandler matchHandler = new MatchHandler(board);

        new Game(screen, gameViewer, gameController, matchHandler).start();
    }

    private void start() throws IOException {
        try {
            gameController.step();
            // TODO: Create GameController to handle user inputs and key processing
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
