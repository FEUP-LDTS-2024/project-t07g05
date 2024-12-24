package com.ldts.crystalclash.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TileMatcher {
    private final Board board;
    public List<Tile> matches;

    public TileMatcher(Board board) {
        this.board = board;
        this.matches = new ArrayList<>();
    }

    public void findMatches() {
        List<Tile> newMatches = new ArrayList<>();

        newMatches.addAll(findHorizontalMatches());
        newMatches.addAll(findVerticalMatches());

        this.matches = newMatches;
    }

    private List<Tile> findHorizontalMatches() {
        List<Tile> horizontalMatches = new ArrayList<>();

        for (int row = 0; row < board.getRows(); row++) {
            Tile prevTile = board.getTile(row, 0);
            int matchLength = 1;

            for (int col = 1; col < board.getColumns(); col++) {
                Tile currTile = board.getTile(row, col);

                if (prevTile != null && currTile != null && prevTile.getColor().equals(currTile.getColor())) {
                    matchLength++;
                } else {
                    if (matchLength >= 3) {
                        addMatchTilesToList(horizontalMatches, row, col - matchLength, matchLength, true);
                    }
                    matchLength = 1;
                }
                prevTile = currTile; // Update prevTile even if currTile is null
            }

            if (matchLength >= 3) {
                addMatchTilesToList(horizontalMatches, row, board.getColumns() - matchLength, matchLength, true);
            }
        }

        return horizontalMatches;
    }


    private List<Tile> findVerticalMatches() {
        List<Tile> verticalMatches = new ArrayList<>();

        for (int col = 0; col < board.getColumns(); col++) {
            Tile prevTile = board.getTile(0, col);
            int matchLength = 1;

            for (int row = 1; row < board.getRows(); row++) {
                Tile currTile = board.getTile(row, col);

                if (prevTile != null && currTile != null && prevTile.getColor().equals(currTile.getColor())) {
                    matchLength++;
                } else {
                    if (matchLength >= 3) {
                        addMatchTilesToList(verticalMatches, row - matchLength, col, matchLength, false);
                    }
                    matchLength = 1;
                }
                prevTile = currTile; // Update prevTile even if currTile is null
            }

            if (matchLength >= 3) {
                addMatchTilesToList(verticalMatches, board.getRows() - matchLength, col, matchLength, false);
            }
        }

        return verticalMatches;
    }


    private void addMatchTilesToList(List<Tile> matchList, int startRow, int startCol, int length, boolean isHorizontal) {
        for (int i = 0; i < length; i++) {
            // Calculate the current row and column based on the match direction
            int row = startRow + (isHorizontal ? 0 : i); // Same row for horizontal, different row for vertical
            int col = startCol + (isHorizontal ? i : 0); // Different column for horizontal, same column for vertical

            if (row >= 0 && row < board.getRows() && col >= 0 && col < board.getColumns()) {
                Tile tile = board.getTile(row, col);
                if (tile != null && !(tile instanceof EmptyTile)) {
                    matchList.add(tile);
                } else {
                    System.out.println("WARNING: Encountered null tile at [" + row + ", " + col + "] during match detection.");
                }
            } else {
                System.out.println("WARNING: Invalid tile position at [" + row + ", " + col + "] during match detection.");
            }
        }
    }

    public void popMatches() {
        Set<Tile> toRemove = new HashSet<>();

        if (matches != null) {
            toRemove.addAll(matches);

            // Logic for bomb explosions
            for (Tile tile : matches) {
                tile.getBehaviorContext().popOff(board, toRemove);
            }

            for (Tile tile : toRemove) {
                Position pos = tile.getGridCoordinates();
                board.setTile(pos.getX(), pos.getY(), new EmptyTile(tile.getScreenPosition(), pos, Color.DEFAULT)); // Set to empty tile
            }

            matches.clear();
        }
    }

    public int calculateScore() {
        int points = 0;

        for (Tile tile : matches) {
            points += tile.getBehaviorContext().calculatePoints(board);
        }

        return points;
    }
}
