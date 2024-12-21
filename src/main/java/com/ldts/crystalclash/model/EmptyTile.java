package com.ldts.crystalclash.model;

import com.ldts.crystalclash.strategy.EmptyTileBehavior;

public class EmptyTile extends Tile {
    public EmptyTile(Position screenPosition, Position gridCoordinates, Color color) {
        super(screenPosition, gridCoordinates, color);
        this.symbol = " ";
        setBehavior(new EmptyTileBehavior());
    }
}