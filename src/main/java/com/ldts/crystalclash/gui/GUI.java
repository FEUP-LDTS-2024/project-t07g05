package com.ldts.crystalclash.gui;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Tile;

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
