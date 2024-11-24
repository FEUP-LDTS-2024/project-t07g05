package controller;

import com.googlecode.lanterna.screen.Screen;
import model.Board;
import com.googlecode.lanterna.input.KeyStroke;
import viewer.GameViewer;

import java.io.IOException;

public class GameController extends Controller<Board> {
    Screen screen;
    GameViewer gameViewer;

    public GameController(Board board) {
        super(board);
        this.gameViewer = new GameViewer(board);
    }

    private void processUserInput(KeyStroke key) {

    }

    @Override
    public void step() throws IOException {
        gameViewer.draw(screen.newTextGraphics());
        KeyStroke key = screen.readInput();
    };
}
