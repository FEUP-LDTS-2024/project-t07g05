package com.ldts.crystalclash.controller;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;

import java.io.IOException;

public class TileController extends GameController {
    public TileController(Board board) {
        super(board);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (action == null) {
            return;
        }

        Board board = getModel();
        Tile newTile = null;

        switch (action) {
            case LEFT:
                newTile = board.getTileToTheLeft(board.getCurrentTile());
                break;
            case RIGHT:
                newTile = board.getTileToTheRight(board.getCurrentTile());
                break;
            case UP:
                newTile = board.getTileOnTop(board.getCurrentTile());
                break;
            case DOWN:
                newTile = board.getTileOnBottom(board.getCurrentTile());
                break;
            case SELECT:
                board.getCurrentTile().setCursorOn(true);
                return;
            default:
                break;
        }

        if (newTile != null) {
            board.getCurrentTile().setCursorOn(false);

            board.setCurrentTile(newTile);

            newTile.setCursorOn(true);
        }
    }
}
