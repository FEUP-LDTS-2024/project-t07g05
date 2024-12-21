package com.ldts.crystalclash.strategy;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;

import java.util.Set;

public interface TileBehavior {
    void popOff(Tile tile, Board board, Set<Tile> toRemove);
    int calculatePoints(Tile tile, Board board);
}
