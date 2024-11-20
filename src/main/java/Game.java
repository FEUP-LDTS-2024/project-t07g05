import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    Screen screen;
    Board board;
    MatchHandler matchhandler;
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
        try {
            board = new Board(8, 8, (width - 30), height, 4, 4);
            matchhandler = new MatchHandler(board);

            boolean game_loop = true;
            while (game_loop) {
                draw();
                KeyStroke key = screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.EOF) {
                    game_loop = false;
                }
                matchhandler.findMatches();
                System.out.println(matchhandler.matches);
                matchhandler.popMatches();
                matchhandler.shiftTilesDown();
            }
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processKey(KeyStroke key) throws IOException {
        switch (key.getKeyType()) {
            case ArrowRight:
                board.moveCurrentTile(board.currentTile.getGridCoordinates().getX(),
                        board.currentTile.getGridCoordinates().getY() + 1);
                break;
            case ArrowLeft:
                board.moveCurrentTile(board.currentTile.getGridCoordinates().getX(),
                        board.currentTile.getGridCoordinates().getY() - 1);
                break;
            case ArrowUp:
                board.moveCurrentTile(board.currentTile.getGridCoordinates().getX() - 1,
                        board.currentTile.getGridCoordinates().getY());
                break;
            case ArrowDown:
                board.moveCurrentTile(board.currentTile.getGridCoordinates().getX() + 1,
                        board.currentTile.getGridCoordinates().getY());
                break;
            case Character:
                if (key.getCharacter() == 'q') {
                    screen.stopScreen();
                    break;
                }
                if (key.getCharacter() == ' ') {
                    KeyStroke swapkey = screen.readInput();
                    processSwapKey(swapkey);
                }
        }
    }

    private void processSwapKey(KeyStroke swapkey) throws IOException {
        switch (swapkey.getKeyType()) {
            case ArrowRight:
                board.swapTiles(board.currentTile, board.getTile(
                        board.currentTile.getGridCoordinates().getX(),
                        board.currentTile.getGridCoordinates().getY()+1));
                board.draw(screen.newTextGraphics());
                break;
            case ArrowLeft:
                board.swapTiles(board.currentTile, board.getTile(
                        board.currentTile.getGridCoordinates().getX(),
                        board.currentTile.getGridCoordinates().getY()-1));
                board.draw(screen.newTextGraphics());
                break;
            case ArrowUp:
                board.swapTiles(board.currentTile, board.getTile(
                        board.currentTile.getGridCoordinates().getX()-1,
                        board.currentTile.getGridCoordinates().getY()));
                board.draw(screen.newTextGraphics());
                break;
            case ArrowDown:
                board.swapTiles(board.currentTile, board.getTile(
                        board.currentTile.getGridCoordinates().getX()+1,
                        board.currentTile.getGridCoordinates().getY()));
                board.draw(screen.newTextGraphics());
                break;
        }
    }

}
