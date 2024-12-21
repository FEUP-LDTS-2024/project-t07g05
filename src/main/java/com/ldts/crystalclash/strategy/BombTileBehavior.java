package com.ldts.crystalclash.strategy;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.EmptyTile;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Tile;

import java.util.Set;

public class BombTileBehavior implements TileBehavior {
    @Override
    public void popOff(Tile tile, Board board, Set<Tile> toRemove) {
        Position pos = tile.getGridCoordinates();

        // Only consider adjacent tiles (no diagonals)
        int[][] directions = {
                {-1, 0}, // Up
                {1, 0},  // Down
                {0, -1}, // Left
                {0, 1}   // Right
        };

        for (int[] direction : directions) {
            int newX = pos.getX() + direction[0];
            int newY = pos.getY() + direction[1];

            if (board.isValidPosition(newX, newY)) {
                Tile adjacentTile = board.getTile(newX, newY);
                if (adjacentTile != null && !(adjacentTile instanceof EmptyTile)) {
                    toRemove.add(adjacentTile);
                }
            }
        }
    }

    @Override
    public int calculatePoints(Tile tile, Board board) {
        int points = 0;
        Position pos = tile.getGridCoordinates();

        // Only consider adjacent tiles
        int[][] directions = {
                {-1, 0}, // Up
                {1, 0},  // Down
                {0, -1}, // Left
                {0, 1}   // Right
        };

        for (int[] direction : directions) {
            int newX = pos.getX() + direction[0];
            int newY = pos.getY() + direction[1];

            if (board.isValidPosition(newX, newY)) {
                Tile affectedTile = board.getTile(newX, newY);
                if (affectedTile != null && !(affectedTile instanceof EmptyTile)) {
                    points += affectedTile.getColorRarity(); // Add rarity value of affected tile
                }
            }
        }

        return points;
    }
}
