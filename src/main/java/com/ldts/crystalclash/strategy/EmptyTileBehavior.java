package com.ldts.crystalclash.strategy;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;

import java.util.Set;

public class EmptyTileBehavior implements TileBehavior {
    @Override
    public void popOff(Tile tile, Board board, Set<Tile> toRemove) {
        // Empty tiles are not popped in matches, only substituted by new tiles
    }

    @Override
    public int calculatePoints(Tile tile, Board board) {
        return 0;
    }
}
