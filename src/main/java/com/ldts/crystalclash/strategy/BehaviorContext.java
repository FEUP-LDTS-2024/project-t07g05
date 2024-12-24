package com.ldts.crystalclash.strategy;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;

import java.util.Set;

public class BehaviorContext {
    private final Tile tile;
    private TileBehavior behavior;

    public BehaviorContext(Tile tile, TileBehavior initialBehavior) {
        this.tile = tile;
        this.behavior = initialBehavior;
    }

    public void setBehavior(TileBehavior behavior) {
        this.behavior = behavior;
    }

    public TileBehavior getBehavior() {
        return behavior;
    }

    public void popOff(Board board, Set<Tile> toRemove) {
        if (behavior != null) {
            behavior.popOff(tile, board, toRemove);
        }
    }

    public int calculatePoints(Board board) {
        return behavior != null ? behavior.calculatePoints(tile, board) : 0;
    }
}
