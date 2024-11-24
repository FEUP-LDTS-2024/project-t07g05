import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import controller.GameController;
import gui.LanternaGUI;
import model.Board;
import model.MatchHandler;
import viewer.GameViewer;

import java.io.IOException;
import java.net.URISyntaxException;


public class Game {
    private final LanternaGUI gui;
    GameViewer gameViewer;
    GameController gameController;
    MatchHandler matchHandler;

    public Game(LanternaGUI gui, GameViewer gameViewer, GameController gameController, MatchHandler matchHandler) {
        try {
            this.gui = gui;
            this.gameViewer = gameViewer;
            this.gameController = gameController;
            this.matchHandler = matchHandler;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        int width = 120; int height = 40;
        LanternaGUI gui = new LanternaGUI(width, height);
        Board board = new Board(8, 8, (width-30), height, 4, 4);
        GameViewer gameViewer = new GameViewer(board);
        GameController gameController = new GameController(board);
        MatchHandler matchHandler = new MatchHandler(board);

        new Game(gui, gameViewer, gameController, matchHandler).start();
    }


    private void start() throws IOException {
        try {
            gameViewer.draw(gui);
            // TODO: Create GameController to handle user inputs and key processing
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
