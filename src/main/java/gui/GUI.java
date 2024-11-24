package gui;

import model.Board;
import model.Position;
import model.Tile;

import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawTile(Tile tile);

    void drawBoard(Board board);

    void drawGameBackground(int width, int height);

    void drawText(Position position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, QUIT, SELECT_TILE, NONE}
}
