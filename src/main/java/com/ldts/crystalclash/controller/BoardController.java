package com.ldts.crystalclash.controller;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.factories.TileFactory;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.*;
import com.ldts.crystalclash.model.Board;

import java.io.IOException;

public class BoardController extends GameController {
    // TODO: algorithm must identify if it is a gemtile or a bombtile before verifying matches
    public TileMatcher tileMatcher;
    public TileFactory tileFactory;

    public BoardController(Board board) {
        super(board);
        tileMatcher = new TileMatcher(board);
        tileFactory = new TileFactory();
    }

    public void swapTiles(Tile t1, Tile t2) {
        System.out.println("Swapping tiles: " + t1 + " and " + t2);
        // Swaps tile t1 with tile t2
        if (t1 == null || t2 == null) {
            return;
        }
        Position t1GridCoord = t1.getGridCoordinates();
        Position t2GridCoord = t2.getGridCoordinates();

        int t1Row = t1GridCoord.getX();
        int t1Col = t1GridCoord.getY();
        int t2Row = t2GridCoord.getX();
        int t2Col = t2GridCoord.getY();

        Tile temp = getModel().getTile(t1Row, t1Col);
        getModel().setTile(t1Row, t1Col, getModel().getTile(t2Row, t2Col));
        getModel().setTile(t2Row, t2Col, temp);

        // Updating grid coordinates and screen positions for each tile
        t1.setGridCoordinates(t2GridCoord);
        t2.setGridCoordinates(t1GridCoord);

        Position t1ScreenPos = getModel().calculateScreenPosition(t2GridCoord);
        Position t2ScreenPos = getModel().calculateScreenPosition(t1GridCoord);
        t1.setScreenPosition(t1ScreenPos);
        t2.setScreenPosition(t2ScreenPos);
        System.out.println("Tile positions before swap: t1: " + t1.getGridCoordinates() + " and t2: " + t2.getGridCoordinates());
    }

    public void moveCurrentTile(int row, int column) {
        Tile prev = getModel().getCurrentTile();

        if (!getModel().isValidPosition(row, column)) {return;}

        Tile curr = getModel().getTile(row, column);
        if (curr == null) {return;}

        prev.setCursorOn(false);
        getModel().setCurrentTile(curr);
        curr.setCursorOn(true);
    }


    public void shiftTilesDown() {
        for (int col = 0; col < getModel().getColumns(); col++) {
            for (int row = getModel().getRows()-1; row >= 0; row--) {
                if (getModel().getTile(row, col) instanceof EmptyTile) {
                    int currentRow = row;
                    while ((currentRow > 0) && (getModel().getTile(currentRow-1, col) instanceof EmptyTile)) {
                        currentRow--;
                    }

                    if (currentRow > 0) {
                        swapTiles(
                                getModel().getTile(row, col),
                                getModel().getTile(currentRow-1, col)
                        );
                    }
                }
            }
        }
    }

    // TODO: Refactor it when FactoryTile is created
    public void refillBoard() {
        for (int col = 0; col < getModel().getColumns(); col++) {
            for (int row = 0; row < getModel().getRows(); row++) {
                if (getModel().getTile(row, col) instanceof EmptyTile) {
                    Tile prev = getModel().getTile(row, col);
                    Position screenpos = prev.getScreenPosition();
                    Position gridco = prev.getGridCoordinates();
                    Tile tile = tileFactory.createRandomTile(screenpos, gridco);
                    getModel().setTile(row, col, tile);
                }
            }
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {

        if (action == null) {
            System.out.println("Received invalid action: null");
            return;
        }

        Board board = getModel();
        switch (action) {
            case UP:
                moveCurrentTile(board.getCurrentTile().getGridCoordinates().getX() - 1,
                        board.getCurrentTile().getGridCoordinates().getY());
                break;
            case RIGHT:
                moveCurrentTile(board.getCurrentTile().getGridCoordinates().getX(),
                        board.getCurrentTile().getGridCoordinates().getY() + 1);
                break;
            case DOWN:
                moveCurrentTile(board.getCurrentTile().getGridCoordinates().getX() + 1,
                        board.getCurrentTile().getGridCoordinates().getY());
                break;
            case LEFT:
                moveCurrentTile(board.getCurrentTile().getGridCoordinates().getX(),
                        board.getCurrentTile().getGridCoordinates().getY() - 1);
                break;
            case SELECT_TILE:
                GUI.ACTION actionSwap = game.gui.waitsNextAction();
                System.out.println(actionSwap);
                switch (actionSwap) {
                    case UP:
                        System.out.println("WARNING: Up pressed");
                        swapTiles(board.getCurrentTile(), board.getTileOnTop(board.getCurrentTile()));
                        break;
                    case DOWN:
                        swapTiles(board.getCurrentTile(), board.getTileOnBottom(board.getCurrentTile()));
                        break;
                    case LEFT:
                        swapTiles(board.getCurrentTile(), board.getTileToTheLeft(board.getCurrentTile()));
                        break;
                    case RIGHT:
                        swapTiles(board.getCurrentTile(), board.getTileToTheRight(board.getCurrentTile()));
                        break;
                }
                break;
        }
        tileMatcher.findMatches();
        getModel().getScore().addScore(tileMatcher.calculateScore());
        tileMatcher.popMatches();
        shiftTilesDown();
        refillBoard();
    }

}
