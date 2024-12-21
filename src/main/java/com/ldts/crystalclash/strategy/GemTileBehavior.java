package com.ldts.crystalclash.strategy;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;

import java.util.Set;

public class GemTileBehavior implements TileBehavior {
    @Override
    public void popOff(Tile tile, Board board, Set<Tile> toRemove) {
        toRemove.add(tile);
    }

    @Override
    public int calculatePoints(Tile tile, Board board) {
        return tile.getColorRarity();
    }
}
