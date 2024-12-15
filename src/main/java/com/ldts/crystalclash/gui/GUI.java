package com.ldts.crystalclash.gui;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Tile;

import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawTile(Tile tile);

    void drawBoard(Board board);

    void drawLine(int x1, int y1, int x2, int y2, String text, String color);

    void drawGameBackground(int width, int height);

    void drawText(Position position, String text, String color);

    void drawLogo(int startX, int startY, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, QUIT, SELECT, SELECT_TILE, NONE}
}
